import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Andrew Budihardja
 */

public class CodingTree {
	
	private class Node implements Comparable<Node>{

		int weight;
		char nodeChar;
		Node leftChild;
		Node rightChild;
		
		public Node(char theChar, int theFreq){
			nodeChar=theChar;
			weight=theFreq;
			leftChild=null;
			rightChild=null;

		}
		
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return weight - o.weight;
		}
	}
	
	Map<Character, Integer> charFreq = new HashMap<Character, Integer>();
	
	Map<Character, String>codes = new HashMap<Character,String>();
	List<Byte> bits = new ArrayList<Byte>();
	
	public CodingTree(String message){
		
		countFrequency(message);
		Node tempRoot=createHuffmanTree();
		buildCharCodes(tempRoot,"");
		compressText(message);
	}
	
	public void countFrequency(String inputString){
		
		if(inputString==null)
			throw new IllegalArgumentException("String is empty");
		
		char charInput;
		for(int i=0;i<inputString.length();i++){
			
			charInput = inputString.charAt(i);
			if(charFreq.containsKey(charInput)){
				charFreq.put(charInput, charFreq.get(charInput) + 1);
			}else{
				charFreq.put(charInput, 1);
			}		
		}
	}
	
	public Node createHuffmanTree(){
		
//		Node myLeft,myRight, myRoot;
		if(charFreq.isEmpty())
			throw new IllegalArgumentException("charfreq is empty");
		
		MyPriorityQueue<Node> myPQ = new MyPriorityQueue<Node>();
		
		Iterator<Character> charFreqIterator =charFreq.keySet().iterator();

		for(int i=0;i<charFreq.keySet().size();i++){
			char charKey=charFreqIterator.next();
			
			myPQ.add(new Node(charKey,charFreq.get(charKey)));
		}
		
		while(myPQ.getSize()>1){
			
			Node myLeft=myPQ.removeMin();
			Node myRight = myPQ.removeMin();

			Node myRoot = new Node('\0',myLeft.weight + myRight.weight);
			myRoot.rightChild=myRight;
			
			myRoot.leftChild=myLeft;
			
			myPQ.add(myRoot);
		}
//		System.out.println(myPQ.toString());
		return myPQ.removeMin();	
	}
	
	public void buildCharCodes(Node theNode, String theChar){
		
		if (theNode == null) {
			return;
		}else if( theNode != null && (theNode.leftChild == null && theNode.rightChild == null)){
			
			codes.put(theNode.nodeChar, theChar);	
			return;	
		}
		buildCharCodes(theNode.leftChild,theChar+"0");
		buildCharCodes(theNode.rightChild,theChar+"1");	
	}
	
	public void compressText(String inputString){
		
//		System.out.println(inputString);
//		System.out.println(inputString.length());
		if(inputString==null){
			throw new IllegalArgumentException("empty text");
		}

		StringBuilder buff = new StringBuilder();
		
		for(int i = 0; i < inputString.length(); i++){
//			String str="null";
			
	
//			System.out.println(Integer.parseInt("84104101",2));
			buff.append(codes.get(inputString.charAt(i)));
//		}

//			System.out.println(buff);
//				
				while(buff.length() > 8){	
//					
					int theChar= Integer.parseInt((buff.substring(0, 8)),2);
//					buffer.append(chr);	
//					System.out.print(chr);
					bits.add((byte) theChar);
			
					buff.delete(0, 8);
//					
//				}
//			}					
		}	
//		System.out.println(buffer.toString());		
//		System.out.println(buffer);		
			while (buff.length() != 8) {
				buff.append('0');
			}
			
			int theChar= Integer.parseInt(buff.substring(0, 8),2);	
			bits.add((byte) theChar);	
		}	
//		System.out.println(bits);
		}
		
	public List<Byte> getCharBits(){
		return bits;
	}
	
	public Map<Character,String> getCharCoding(){
		return codes;
	}
}
