/**
 * 
 * @author andrewbudihardja
 * This is the Doubly LinkedList class 
 * that controls the data structure.
 *
 */

public class DoublyLinkedList {
	//variables for the nodes.
	private Node last;
	private Node head;
	private int size;
	/**
	 * 
	 * inner class for the nodes object
	 *
	 */
	private class Node{
		
		public int element;
		public Node next;
		public Node prev;
		
		public Node(int theElement, Node thePrev, Node theNext){
			element=theElement;
			next=theNext;
			prev=thePrev;
		}
	}
	//constructor
	public DoublyLinkedList() {
		
		size=0;
		head=null;
		last=null;	
	}
	
	
	/**
	 * adds the item to the end of the list
	 */
	public void add(int item) {

		Node temp = new Node(item,null,null);
		
		if(head==null){
			head=temp;
			last=head;
		}else{
			temp.prev=last;
			last.next=temp;
			last=temp;
		}
		size++;
	}
	/**
	 * adds the item according to the index
	 * 0-size;
	 * @param index
	 * @param item
	 */
	public void add(int index, int item) {

		
		Node temp = new Node(item,null,null);
		
		if(index>size||0>index){
			throw new IndexOutOfBoundsException();
		}
		if(isEmpty()){
			head=temp;
			last=head;
			size++;	
//		}else if(index==size()-1){
//			Node tp = last.prev;
//			last.prev=temp;
//			tp.next=temp;
//			temp.next=last;
//			temp.prev=tp;
//			
//			last=temp;
//			size++;
		}else if(index==size){
			last.next=temp;
			temp.prev=last;
			last=temp;
			size++;
		}else if(index==0){
		
			temp.next=head;
			head.prev=temp;
			head=temp;
			size++;
		}else{
			Node pointer = head;
			for(int i=1;i<=size();i++){
				if(i==index){
					Node tmp = pointer.next;
					pointer.next=temp;
					temp.prev=pointer;
					temp.next=tmp;
					tmp.prev=temp;
				}
				pointer=pointer.next;
			}
			size++;
		}
	}
	/**
	 * removes the item according to index
	 * 0->size-1
	 * @param index
	 */
	public void remove(int index) {

		if(index>=size||index<0){
			throw new IndexOutOfBoundsException();
		}
		if(index==0){
			if(size==1){
				head=null;
				last=null;
				size=0;
				return;
			}
			head=head.next;
			head.prev=null;
			size--;
			return;
		}else if(index==size()-1){	
			Node tmp=last.prev;
			tmp.next=null;
			last=tmp;
			size--;
		}else{
			Node pointer = head.next;
			for(int i=1;i<=size();i++){
				if(i==index){
					Node p = pointer.prev;
					Node a = pointer.next;
					
					p.next=a;
					a.prev=p;
					size--;
					return;
				}
				pointer=pointer.next;
			}
			
		}
	}
	
	/**
	 * clears the list
	 */
	public void clear() {
			head=null;
			size=0;	
	}
	
	/**
	 * returns the item according to the index
	 * @param index
	 * @return
	 */
	public int get(int index) {
		
		if(index<0||index>=size()){
			throw new IndexOutOfBoundsException();
		}
		if(index==0){
			return head.element;
		}else{
			Node pointer=head;
			for(int i=0;i<=size();i++){
				if(i==index){
					return pointer.element;
				}
				pointer=pointer.next;
			}
			
		}
		return 0;
	}
	
	/**
	 * sets the value in the
	 * index according to the item
	 * @param index
	 * @param item
	 */
	public void set(int index, int item) {

		if(index<0||index>=size()){
			throw new IndexOutOfBoundsException();
		}
		Node temp = new Node(item,null,null);
		if(index==0){
			temp.next=head.next;
			Node tmp = head.next;
			tmp.prev=temp;
			
			head=temp;
		}else if(index==size()-1){
			Node tmp = last.prev;
			tmp.next=temp;
			temp.prev=tmp;
			last=temp;
		}else{
			Node pointer =head.next;
			for(int i=1;i<size();i++){
				if(i==index){
					Node tmp = pointer.prev;
					Node tmp2 = pointer.next;
					tmp.next=temp;
					temp.prev=tmp;
					temp.next=pointer.next;
					tmp2.prev=temp;
					
				}
				pointer=pointer.next;
			}
		}
	}
	
	/**
	 * returns size of the list
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * returns true if empty
	 * false if not
	 * @return
	 */
	public boolean isEmpty() {	
		return size()==0;
	}
	
	/**
	 * return true if contains the item
	 * false if not
	 * @param item
	 * @return
	 */
	public boolean contains(int item) {
		Node pointer = head;
		while(pointer!=null){
			if(pointer.element==item){
				return true;
			}
			pointer=pointer.next;
		}
		
		return false;
	}
	/**
	 * returns the first index of occurence
	 * -1 if not found
	 * @param item
	 * @return
	 */
	public int indexOf(int item) {
		Node pointer=head;
		int i=0;
		while(pointer!=null){
			if(pointer.element==item){
				return i;
			}
			i++;
			pointer=pointer.next;
		}
		
		return -1;
	}
	/**
	 * returns string implementation of the list
	 */
	public String toString() {
		
		if(size==0){
			return "[]";
		}
		Node pointer = head;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i=0;
		while(pointer!=null){
			sb.append(pointer.element);
			if(i!=size()-1){
				sb.append(", ");
			}
			pointer=pointer.next;
			i++;
		}
		sb.append("]");
		return sb.toString();
		
	}
	/**
	 * reverses the items in the list
	 */
	public void reverse() {
		
		Node temp =null;
		Node pointer=head;
		Node temp2 = head;
		last=temp2;
		 while (pointer!=null){
	       temp = pointer.prev;
	       pointer.prev = pointer.next;
	       pointer.next = temp;              
	       pointer=pointer.prev;

	     }   
		 
		 if(temp != null ){
		        head = temp.prev;
		}     	 
	}
	/**
	 * returns a new linkedlist
	 * that shows the values that occur only once in the calling list
	 * or the other list but not once in both list combined.
	 * @param other
	 * @return
	 */
	public DoublyLinkedList getUniqueElements(DoublyLinkedList other) {
		
		DoublyLinkedList tempList = new DoublyLinkedList();
		DoublyLinkedList tempList2 = new DoublyLinkedList();
		DoublyLinkedList tpList = new DoublyLinkedList();
		DoublyLinkedList tpList2 = new DoublyLinkedList();
		for(int i=0;i<size;i++){
			if(!tempList.contains(get(i))){
				if(!tpList.contains(get(i))){
					tempList.add(get(i));
				}
				
			}else{
				tpList.add(get(i));
				tempList.remove(tempList.indexOf(get(i)));
				
			}
			
		}
		//System.out.println("templist 1"+tempList.toString());
		for(int i=0;i<other.size();i++){
			if(!tempList2.contains(other.get(i))){
				if(!tpList2.contains(other.get(i))){
					tempList2.add(other.get(i));
				}	
			}else{
				tpList2.add(other.get(i));
				tempList2.remove(tempList2.indexOf(other.get(i)));
			}
		}
		
		//System.out.println("templist 2"+tempList2.toString());
		
		for(int i=0;i<tempList2.size();i++){
			tempList.add(tempList2.get(i));
		}
		
		return tempList;
	}
	/**
	 * mixes the values in the other
	 * with the calling list. starts with the calling list
	 * then other list then etc.
	 * 
	 * 
	 * @param other
	 */
	public void interleave(DoublyLinkedList other) {
		
		int i=-1;
		Node pointer = other.head;
		for(int k=0;k<other.size();k++){
			if(i<size-1){
				i+=2;
				//System.out.println(size + " "+i);
			}else{
				i+=1;
				
			}
			//System.out.println("run");
			add(i,pointer.element);
			pointer=pointer.next;
		}
		
	}
}
	

