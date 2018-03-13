
public class Decoder {
    HuffmanTree ht;
    String encodedbits;

    public Decoder(HuffmanTree theTree, String theEncodebits){
    	
    		ht = theTree;
    		encodedbits = theEncodebits;
    }

    public String decode(){
    	
    		HuffmanTreeNode node = ht.rootnode;
        int charIndex = 0;
        StringBuilder resultStr = new StringBuilder("");
        
        
        while (charIndex < encodedbits.length()){
        	
            String charBit = encodedbits.substring(charIndex, charIndex+1);
            
            if ("0".equals(charBit)){
                node = node.getLeft();
            }
            else if ("1".equals(charBit)){
                node = node.getRight();
            }
            
            if (node.isLeaf()){
                resultStr.append(node.getChar());
                node = ht.rootnode;
            }
            System.out.print(node.getChar());
            charIndex++;
        }  
        return resultStr.toString();
    } 
}

