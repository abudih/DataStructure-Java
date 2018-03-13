
public class HuffmanTree {
	
	HuffmanTreeNode rootnode = null;
    
    public HuffmanTree(HuffmanTreeNode theRoot){
    	
    		this.rootnode = theRoot;
    }

    public HuffmanTreeNode getRootItem(){
        return rootnode;
    }

    public void setRootItem(HuffmanTreeNode newRootItem){
    	
        rootnode = newRootItem;
    }

    public void attachLeft(HuffmanTreeNode newLeft){
    	
        rootnode.attachLeft(newLeft);
    }

    public void attachRight(HuffmanTreeNode newRight){
    	
        rootnode.attachRight(newRight);
    }


    public void attachLeftSubtree(HuffmanTree newLeft){
    	
        this.attachLeft(newLeft.getRootItem());
    }

    public void attachRightSubtree(HuffmanTree newRight){
        this.attachRight(newRight.getRootItem());
    }
}