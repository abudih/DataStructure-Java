import java.util.*;
/**
 * 
 * @author Andrew Budihardja
 *
 */

public class Encoder {
	
    private HuffmanTree ht;
    private String inputStr;
    private HuffmanFrequencyTable hft;


    public Encoder(String _inputStr){
    	
    		this.inputStr = _inputStr; 
    		this.hft = new HuffmanFrequencyTable(inputStr); 
    		this.buildTree();
    		HuffmanTreeNode root = ht.getRootItem();
    	
    		root.assignCodes();
    		root.readCodes("", hft);
    }


    public HuffmanFrequencyTable getFreqTable(){
    	
    		return hft;
    }
 
    public HuffmanTree getHuffmanTree(){
    	
    		return ht;
    }


    public String encodeString(){
    	
        String bitStr = "";
        
        for (int i=0; i < inputStr.length(); i++){
        	
            bitStr += hft.getCode(inputStr.charAt(i));
        }
        
        return bitStr;
    }

    private void buildTree(){
	
        ArrayList<HuffmanTree> forest = new ArrayList<>();
        
        //adding leaves
        for (int i=0; i < hft.size(); i++){
        	
            forest.add(new HuffmanTree(new HuffmanTreeNode(hft.getItem(i).getInputChar(), hft.getItem(i).getFreq())));
        }
        
        Collections.sort(forest, (a,b) -> a.getRootItem().compareTo(b.getRootItem()));
        
        //generating trees
        while(forest.size() > 1){
        	
        	
            Collections.sort(forest, (a,b) -> a.getRootItem().compareTo(b.getRootItem()));
            
            HuffmanTreeNode low1 = forest.get(0).getRootItem();
            HuffmanTreeNode low2 = forest.get(1).getRootItem();
            HuffmanTree newTree = new HuffmanTree(new HuffmanTreeNode('\0', low1.getFreq() + low2.getFreq()));
            
            newTree.attachLeftSubtree(forest.get(1));
            newTree.attachRightSubtree(forest.get(0));
            forest.remove(1);
            forest.remove(0);
            forest.add(newTree);       
        }
        
        ht = forest.get(0);   
    }
}

