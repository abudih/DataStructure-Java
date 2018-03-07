import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Andrew Budihardja
 */

public class TestFile {
	
	public static void main(String[]args) throws IOException{
		
		
		//test MyPriorityQueue
		
		MyPriorityQueue<Integer> myPq = new MyPriorityQueue<Integer>();
		System.out.println("Size of pq = "+myPq.getSize());
		System.out.println("isEmpty= "+myPq.isEmpty());
		
		myPq.add(30);
		myPq.add(40);
		myPq.add(1);
		myPq.add(15);
		myPq.add(23);
		myPq.add(50);
		
		System.out.println("Size of pq = "+myPq.getSize());
		System.out.println("isEmpty= "+myPq.isEmpty());
		
		System.out.println("Removing priorities until isEmpty = true");
		while(!myPq.isEmpty()){
			
			System.out.print(myPq.removeMin()+",");
		}
		
		System.out.println("removing priority when isEmpty should throw exception");
//		myPq.removeMin();
		System.out.println();
		System.out.println();
		
		MyPriorityQueue<Character> myPq2 = new MyPriorityQueue<Character>();
		
		System.out.println("Size of pq = "+myPq2.getSize());
		System.out.println("isEmpty= "+myPq2.isEmpty());
		
		
		myPq2.add('c');
		myPq2.add('d');
		myPq2.add('h');
		myPq2.add('a');
		myPq2.add('k');
		myPq2.add('r');
		
		System.out.println("Size of pq = "+myPq2.getSize());
		System.out.println("isEmpty= "+myPq2.isEmpty());
		
		System.out.println("Removing priorities until isEmpty = true");
		while(!myPq2.isEmpty()){
			
			System.out.print(myPq2.removeMin()+",");
		}
		
		System.out.println();
		System.out.println();
		
		String fileName = "silverchair.txt";
		String fileName2 = "compressed.txt";
		String fileName3 = "codes.txt";
		
		
		FileInputStream fileInput = new FileInputStream(fileName);
		FileOutputStream out = new FileOutputStream(new File(fileName2));
		FileWriter fw = new FileWriter(new File(fileName3));
		
		
		StringBuilder sb = new StringBuilder();
		
		int c;
	    while ((c = fileInput.read()) != -1) {
	    	char t = (char) c;
	        sb.append(t);
	         
	    }
	     
	     
	    long start = System.currentTimeMillis();
	    
	    CodingTree theTree = new CodingTree(sb.toString());
	    
	    byte[] compressed = new byte[theTree.getCharBits().size()];
	    
	    for(int i=0;i<compressed.length;i++){
	    	compressed[i]=theTree.getCharBits().get(i);
	    }
	    out.write(compressed);
	    
//	    System.out.println(compressed.length);
	    fw.write(theTree.getCharCoding().toString());
	    
	    long stop = System.currentTimeMillis();
	    
	    System.out.println("Uncompressed file size:" + sb.length());
	    System.out.println("Compressed file size:" + new File("Compressed.txt").length());
	    System.out.println("compression ratio (Bytes):  " + (sb.length()*100)/new File("Compressed.txt").length()+ "%");
	    System.out.println("Running Time:" + (stop-start));
	   
	    fileInput.close();
	    fw.close();
	    out.close();
	    	
	}
}
