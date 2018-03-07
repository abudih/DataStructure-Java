import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author Andrew Budihardja
 * This class helps the getEncoding method in the Huffman class
 * It creates a specific tree using huffman algorithm
 * to calculate the bits for the chars by frequencies.
 *
 */
public class HuffmanCode {

		
		//This will be the max size of the queue
	private int totalChars;
		
		//array containing the characters 
	private List<Character> charList;
		
		//array containing the frequency of each symbol charList
	private List<Integer> frequencyList;
		
		//array containing the huffman code of each symbol in charList
	private String[] code;

		//priority queue used for generating the prefix code tree
	private Queue<Node> priorityQueue;
		
		
	public HuffmanCode(){

	 frequencyList=new ArrayList<Integer>();
	 charList = new ArrayList<Character>();
	 
//	  Huffman a = new Huffman();
//	  total_letters=a.lengthOfFile;
//	  n=a.numOfChars;
	  	 
  }
  /**
   *  sets char and frequency lists
   * @param theMap
   */
  public void setCharandFreqList(Map<Character,Integer> theMap){
	  
	  for(char tempChar : theMap.keySet()){
		  
		  charList.add(tempChar);
	  }
	  
	  
	  for(char tempChar : theMap.keySet()){
		  
		  frequencyList.add(theMap.get(tempChar)); 
	  }
  }
  
  /**
   * sort char and frequency list 
   * ascending
   */
  public void sortCharandFreqList(){
	//sort ascending
	  char temp;
	  int temp2;
	  for (int i = 1; i < frequencyList.size(); i++) {
          for(int j = i ; j > 0 ; j--){
              if(frequencyList.get(j) < frequencyList.get(j-1)){
            	  temp=charList.get(j);
                  temp2 = frequencyList.get(j);
                  
                  frequencyList.set(j,frequencyList.get(j-1));
                  frequencyList.set(j-1, temp2);
                  
                  charList.set(j, charList.get(j-1));
                  charList.set(j-1, temp);
              }
          }
      }
  }
  
	/**
	 * set total chars.
	 * @param theNumber
	 */
  public void setTotalChars(int theNumber){
	  totalChars= theNumber;
	  
	  code = new String[totalChars];
		 for(int i = 0; i < totalChars; i++) {
				code[i] = "";
		}
  }
  
  /**
   * returns huffman encoding for each char
   * @return
   */
  public String[] getHuffmanEncoding(){
	  
	  return code;
  }
  
  /**
   * returns charList
   * @return
   */
  public List<Character> getCharList(){
	  
	  return charList;
  }
  
  /**
   * runs the program.
   */
  public void run(){
	  
//	  System.out.println("huffman code length = "+code.length);
//	  System.out.println("total letters="+total_letters);
	  System.out.println("Number of characters=" +totalChars);
	  System.out.println("chars=" + charList.toString());
	  System.out.println("frequency= "+ frequencyList.toString());
	  
		fillQueue();
		constructPrefixCodeTree();
		Node node = priorityQueue.remove();
		traverseDepthFirst(node, "");
		print();
  }
  
  /**
   * prints the data related to the char, frequency and huffman encoding
   */
  public void print(){
	  
	  StringBuilder outputString= new StringBuilder();
		outputString.append("Symbol\tFrequency\tCode\n\n");
		for(int i=0; i< totalChars; i++){
			
			outputString.append(charList.get(i)+"\t"+frequencyList.get(i)+"\t"+code[i]+"\t"+"\n");
		}
		
		System.out.println(outputString.toString());
	
  }
  
  /**
   * inner node class.
   */
    public class Node implements Comparable {
    	
      char element; // Stores the character for a leaf node
      int weight; // weight of the subtree rooted at this node
      int frequency;
      Node left; // Reference to the left subtree
      Node right; // Reference to the right subtree
     // String code = ""; // The code of this node from the root

      /** Create an empty node */
      public Node() {
    	  
      }
      public Node(char element){
    	  this.element=element;
          this.frequency=0;
          this.left=null;
          this.right=null;
      }
      
      /**
       *  Create a node with the specified weight and character 
       * 
       * 
       */
      public Node(char element,int frequency,Node left, Node right) {
        this.element = element;
        this.frequency=frequency;
        this.left=left;
        this.right=right;
      }
      
      public char getSymbol(){
    	  
    	  return element;
      }
      
      public Node getLeftChild(){
    	  return left;
      }
      
      public Node getRightChild(){
    	  return right;
      }
      
      public void setLeftChild(Node theNode){
    	  this.left=theNode;
      }
      
      public void setRightChild(Node theNode){
    	  this.right=theNode;
      }
      
      public int getFreq(){
    	  return frequency;
      }
      
      public void setFreq(int theFrequency){
    	  this.frequency=theFrequency;
      }

      public int compareTo(Object o) {
    	  // TODO Auto-generated method stub
    	  return 0;
      }  
    }
    
    /**
     * 
     * traverses the tree
     */
    private void traverseDepthFirst(Node node, String code) {
    	
		//only proceed if tree is not null
		if(node != null) {
			//compute the index of the symbol in S
			int index=0;
			
			for(int i=0;i<totalChars;i++){
				
				if(charList.get(i) == node.getSymbol()){
					index= i;
					break;
				}
			}
			// store the current code in the code array at the index of the symbol
			this.code[index] = code;
			//traverse left
			this.traverseDepthFirst(node.getLeftChild(), code + "1");
			//traverse right
			this.traverseDepthFirst(node.getRightChild(), code + "0");
		}
		
	}
	
	/**
	 * Generates the prefix code tree
	 */
	void constructPrefixCodeTree(){
		
		//construct binary tree with the symbols in S stored in PQ
		for(int i = 0; i < totalChars-1; i++) {
			//create the tree
			Node parent = new Node('|', 0, null, null);
			
			//extract the minimum from priority queue and make them it the rightchild
			Node rightChild = removeMin();
			parent.setRightChild(rightChild);
			
			//extract the new minimum from priority queue and make them it the leftchild
			Node leftChild = removeMin();
			parent.setLeftChild(leftChild);		
			
			//set the frequency to the combined frequency of leftChild and rightChild
			parent.setFreq(rightChild.getFreq() + leftChild.getFreq());
			priorityQueue.add(parent);
				
		}
	}
	
	/**
	 * Build the priority queue with the symbol with least 
	 * frequency on the first of the queue
	 */
	private void fillQueue(){	
		priorityQueue = new LinkedList<Node>();
		Node node;
		for(int i = 0; i < totalChars; i++) {
			node = new Node (charList.get(i),frequencyList.get(i) , null, null);
			priorityQueue.add(node);
			
		}
	}
	
	/**
	 * 
	 * removes the minimum frequency node from the queue.
	 */
	private Node removeMin(){
		
		ArrayList<Node>temp = new ArrayList<Node>();
		while(!priorityQueue.isEmpty()){
			
			temp.add(priorityQueue.remove());	
		}
		
		Node temp2;
		for (int i = 1; i < temp.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if((temp.get(i).getFreq() < temp.get(j-1).getFreq())){
                    temp2 = (Node) temp.get(j);
                    temp.set(j, temp.get(j-1));
                    temp.set(j-1, temp2);
                    
                }              
            }
        }
		
		for(int i=0;i<temp.size();i++){
			priorityQueue.add(temp.get(i));		
		}	
		return priorityQueue.remove();
	}    
  }  


