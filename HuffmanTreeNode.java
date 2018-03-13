/**
 * 
 * @author Andrew Budihardja
 *
 */

public class HuffmanTreeNode  {
    
    private char inputChar;
    private int freq;
    private String code;
    private HuffmanTreeNode left;
    private HuffmanTreeNode right;
    
    
    public HuffmanTreeNode(char theChar, int theFreq){
    	
    		inputChar = theChar;
    		freq = theFreq;
        code = "";
    }

    public void attachRight(HuffmanTreeNode newRightNode) {
        this.right = newRightNode;
    }


    public void attachLeft(HuffmanTreeNode newLeftNode) {
        this.left = newLeftNode;
    }


    public HuffmanTreeNode getRight() {
        return right;
    }

    public HuffmanTreeNode getLeft() {
        return left;
    }
    
    public int compareTo(HuffmanTreeNode inputNode) {
        return freq - inputNode.freq;
    }
    
    public char getChar(){
    		return inputChar;
    }
    
    public int getFreq(){
    		return freq;
    }
    
    public void setChar(char theChar){
    		inputChar = theChar;
    }
    
    public void setFreq(int theFreq){
    		freq = theFreq;
    }
    
    public void setCode(String theCode){
    		code = theCode;
    }
    
    public String getCode(){
    		return code;
    }
    
    public Boolean isLeaf(){
    	
    	//if char=empty
        if (inputChar == '\0'){  
        	
            return false;
        }
        else { 
        		return true;
        }
    }
    
    public void assignCodes(){
    	
        left.setCode("0");
        
        if (!left.isLeaf()){
            left.assignCodes();
        }

        right.setCode("1"); 
        
        if (!right.isLeaf()){
            right.assignCodes();
        }
    }
    
    public void readCodes(String thisCode, HuffmanFrequencyTable hft){
    	
        thisCode = thisCode + code;
        
        if (this.isLeaf()){
            hft.setCode(inputChar, thisCode);
        }
        else {
            left.readCodes(thisCode, hft);
            right.readCodes(thisCode, hft);
        }
    }
}