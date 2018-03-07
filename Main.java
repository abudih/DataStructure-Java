import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
/**
 * @author Andrew Budihardja
 */


public class Main {

//	@SuppressWarnings("resource")
	public static void main(String[]args) throws IOException{
		
	String fileName = "WarAndPeace.txt";
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
    
//    System.out.println(compressed.length);
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
