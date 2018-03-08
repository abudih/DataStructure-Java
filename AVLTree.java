/**
 * 
 * @author Andrew Budihardja
 *
 */
public class AVLTree<T extends Comparable<T>> {
	
	/**
	 */
	private static class Node<T> {
		public T item;
		public Node<T> parent;
		public Node<T> left;
		public Node<T> right;
		
		public int height;
		
		public Node(T item) {
			this(item, null);
			this.parent=null;
			this.height=0;
			this.left=null;
			this.right=null;
		}
		
		public Node(T item, Node<T> parent) {
			this.item = item;
			this.parent = parent;
			this.height=0;
			this.left=null;
			this.right=null;
		}

		public Node<T> getSuccessor() {
			if (right != null) {
				Node<T> successor = right;
				while (successor.left != null)
					successor = successor.left;
				return successor;
			} else {
				Node<T> successor = this;
				while (successor.parent != null && successor.parent.right == successor)
					successor = successor.parent;
				successor = successor.parent;
				return successor;
			}
		}
	}

	private int size;
	private Node<T> root;
	
	public AVLTree(){
		root=null;
	}
	
	/**
	 * 
	 * @param theNode
	 * @return
	 */
     private int height(Node<T> theNode ){
    	 if(theNode==null){
    		 return -1;
    	 }
    		 return theNode.height;
    	 

     }
 	/**
 	 * 
 	 * @param height1
 	 * @param height2
 	 * @return
 	 */
 	private int max(int height1, int height2){
 		if(height1>height2){
 			return height1;
 		}
 		
 		return height2;

    }
 	
 	/**
 	 * 
 	 * @return
 	 */
 	public boolean isEmpty(){
 	    return (root == null);
 	}
 	
 	/**
 	 * 
 	 * @return
 	 */
 	public T findMax( ){
 		 
 	        if( isEmpty( ) ){
 	        	return null;
 	        }
 	        return findMax( root ).item;
 	}
 	/**
 	 * 
 	 * @param t
 	 * @return
 	 */
 	 private Node<T> findMax( Node<T> t ){
 	        if( t == null )
 	            return t;

 	        while( t.right != null )
 	            t = t.right;
 	        
 	        return t;
 	 }
 	 
	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean add(T item) {
		if (item == null)
			throw new IllegalArgumentException();
			
			root = add(root,item);
			size++;
			
			return true;
	
	}
	
	/**
	 * 
	 * @param inputNode
	 * @param theItem
	 * @return
	 */
	private Node<T> add(Node<T> inputNode, T theItem) {

		 if (inputNode == null){
             inputNode = new Node<T>(theItem);
		 
		 }else if (inputNode.item.compareTo(theItem)>0){
			
				inputNode.left=add(inputNode.left,theItem);
//				System.out.println("height of left1"+inputNode.left.height);
//				System.out.println("height of right1"+height(inputNode.right));
				if( height( inputNode.left ) - height( inputNode.right ) == 2 ){
//					System.out.println("In1");
	            	
	                if( inputNode.left.item.compareTo(theItem)>0 )
	                    inputNode = rotateWithLeftChild( inputNode );
	                else
	                    inputNode = doubleWithLeftChild( inputNode );
	            }
				
        }else if(inputNode.item.compareTo(theItem)<0){
        	
        		inputNode.right=add(inputNode.right,theItem); 		
        		
        		if( height( inputNode.right ) - height( inputNode.left ) == 2 ){
        			
//        			System.out.println("In2");
                    if( inputNode.right.item.compareTo(theItem)<0)
                        inputNode = rotateWithRightChild( inputNode );
                    else
                        inputNode = doubleWithRightChild( inputNode );
                }
        			
        }else{
           
        }
        inputNode.height = max( height( inputNode.left ), height( inputNode.right ) ) + 1;
//        System.out.println("height = "+ t.height);
        
        return inputNode;
	}
	
	/**
	 * 
	 * @param theNode
	 * @return
	 */
	private Node<T> rotateWithLeftChild(Node<T> theNode){
		
        Node<T> temp1 = theNode.left;
        theNode.left = temp1.right;
        temp1.right = theNode;
        theNode.height = max( height( theNode.left ), height( theNode.right ) ) + 1;
        temp1.height = max( height( temp1.left ), theNode.height ) + 1;
        
        return temp1;
    }

    /**
     * Rotate binary tree node with right child 
     * 
     */
    private Node<T> rotateWithRightChild(Node<T> theNode){
    	
        Node<T> temp2 = theNode.right;
        theNode.right = temp2.left;
        temp2.left = theNode;
        theNode.height = max( height( theNode.left ), height( theNode.right ) ) + 1;
        temp2.height = max( height( temp2.right ), theNode.height ) + 1;
        
        return temp2;
    }
    
    /**
     * Double rotate binary tree node: first left child
     * with its right child 
     */
    private Node<T> doubleWithLeftChild(Node<T> node){
    	
        node.left = rotateWithRightChild( node.left );
        return rotateWithLeftChild( node );
    }
    
    /**
     * Double rotate binary tree node: first right child
     * with its left child      
     */
    private Node<T> doubleWithRightChild(Node<T> node) {
    	
        node.right = rotateWithLeftChild( node.right );
        return rotateWithRightChild( node );

    }    
    
	
    /**
     * 
     * @param item
     * @return
     */
	public boolean remove(T item) {
		if (item == null)
			throw new IllegalArgumentException();

		root=remove(root, item);
		size--;
		return true;
	}
	
	/**
	 * 
	 * @param inputNode
	 * @param theItem
	 * @return
	 */
	public Node<T> remove(Node<T> inputNode,T theItem){
		
	 if (inputNode==null)    {
         return null;

     }
     
 
     if (theItem.compareTo(inputNode.item) < 0 ) {

         inputNode.left = remove(inputNode.left,theItem);
         
         int l=0;
         if(inputNode.left!=null){
        	 l= inputNode.left.height;
         }

 
         if((inputNode.right != null) && (inputNode.right.height - l >= 2)) {

        	 int rightHeight=0;
        	 int leftHeight=0;
        	 if(inputNode.right.right!=null){
        		 rightHeight=inputNode.right.height;
        	 }

        	 if(inputNode.right.left!=null){
        		 leftHeight=inputNode.right.left.height;
        	 }

 
             if(rightHeight >= leftHeight){
                 inputNode = rotateWithLeftChild(inputNode);            
             }else{
                 inputNode = doubleWithRightChild(inputNode);
             }
         }
         
     }else if (theItem.compareTo(inputNode.item) > 0) {

    	 int r=0;
         inputNode.right = remove(inputNode.right,theItem);
         if(inputNode.right!=null){
        	 r=inputNode.right.height;
         }

         
         if((inputNode.left != null) && (inputNode.left.height - r >= 2)) {

        	 int leftHeight=0;
        	 int rightHeight=0;

        	 if(inputNode.left.left!=null){
        		 leftHeight=inputNode.left.left.height;
        	 }

        	 if(inputNode.left.right!=null){
        		 rightHeight=inputNode.left.right.height;
        	 }

        	 
            	 if(leftHeight >= rightHeight)
             	    inputNode = rotateWithRightChild(inputNode);               
           	  else
             	    inputNode = doubleWithLeftChild(inputNode);
         }

     }
     
     else if(inputNode.left != null) {

         inputNode.item = findMax(inputNode.left).item;
         remove(inputNode.left,inputNode.item);
      
         if((inputNode.right != null)&&(inputNode.right.height - inputNode.left.height >= 2)) {
        	 int rightHeight=0;
        	 int leftHeight=0;
        	 if(inputNode.right.right!=null){
        		 rightHeight=inputNode.right.right.height;
        	 }

        	 
        	 if(inputNode.right.left!=null){
        		 leftHeight=inputNode.right.left.height;
        	 }

      
             if(rightHeight >= leftHeight){
                 inputNode = rotateWithLeftChild(inputNode);            
             }else{
                 inputNode = doubleWithRightChild(inputNode);
             }
         }
     }
      
     else{
         inputNode = (inputNode.left != null) ? inputNode.left : inputNode.right;
     }
     
     if(inputNode != null) {
    	 int leftHeight=0;
    	 int rightHeight=0;
    	 
    	 if(inputNode.left!=null){
    		 leftHeight=inputNode.left.height;
    	 }

    	 
    	 if(inputNode.right!=null){
    		 rightHeight=inputNode.right.height;
    	 }

     }
     return inputNode;
     
 } 
	/**
	 * 
	 */
	private void remove(Node<T> theNode) {

		if (theNode.right == null) {
			
			if (theNode == root) {
				root = theNode.left;
				if (root != null)
					root.parent = null;
			} else {
				if (theNode.parent.left == theNode)
					theNode.parent.left = theNode.left;
				else
					theNode.parent.right = theNode.left;
				if (theNode.left != null)
					theNode.left.parent = theNode.parent;
			}
		} else if (theNode.left == null) {
			
			if (theNode == root) {
				root = theNode.right;
				root.parent = null;
			} else {
				if (theNode.parent.left == theNode)
					theNode.parent.left = theNode.right;
				else
					theNode.parent.right = theNode.right;
				theNode.right.parent = theNode.parent;
			}
		} else {
			
			Node<T> successor = theNode.getSuccessor();
			theNode.item = successor.item;
			remove(successor);
		}
	}
	
	public boolean contains(T item) {

		if (item == null)
			throw new IllegalArgumentException();
		
		if (root == null)
			return false;
		else
			return contains(root, item);
	}
	
	private boolean contains(Node<T> theNode, T item) {

		int temp = theNode.item.compareTo(item);
		if (temp > 0)
			
			if (theNode.left == null)
				return false;
			else
				return contains(theNode.left, item);
		else if (temp < 0)
			
			if (theNode.right == null)
				return false;
			else
				return contains(theNode.right, item);
		else
			return true;
	}

	public int size() {
		return size;
	}

	public void clear() {
		size = 0;
		root = null;
	}

	public void hasValidStructure() {
		if (root != null) {
			assert root.parent == null;
			hasValidStructure(root);
		}
	}

	private void hasValidStructure(Node<T> theNode) {
		if (theNode.left != null) {
			assert theNode.left.item.compareTo(theNode.item) < 0;
			hasValidStructure(theNode.left);
		}
		if (theNode.right != null) {
			assert theNode.right.item.compareTo(theNode.item) > 0;
			hasValidStructure(theNode.right);
		}
	}
	
	
	public void printTree(){
		printTree(root);
	}
	
	 public void printTree(Node<T> root){

         if( root != null ){
             printTree( root.left );
             System.out.println( root.item );
             printTree( root.right );
         }
     }
	 /////
	 public void preorder(){
         preorder(root);
     }
	 
     private void preorder(Node<T> theNode){
    	 
         if (theNode != null){
             System.out.print(theNode.item +" ");
             preorder(theNode.left);             
             preorder(theNode.right);
         }
     }
     
     public void inorder(){
         inorder(root);
     }
     
     private void inorder(Node<T> theNode){
         if (theNode != null){
             inorder(theNode.left);
             System.out.print(theNode.item +" ");
             inorder(theNode.right);
         }
     }
     
	public static void main(String[]args){
		AVLTree<Integer> a = new AVLTree<Integer>();
		
		a.add(10);
		a.add(9);
		a.add(8);
		a.add(7);
		a.add(6);
		
		a.add(12);
		a.add(13);
		a.add(15);
		a.preorder();
		System.out.println();
		a.inorder();
		System.out.println();
		System.out.println(a.size());
		
		a.remove(0);
	}
}