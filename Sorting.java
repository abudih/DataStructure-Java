/**
 * css 342
 * @author andrewbudihardja
 *
 * @param <T>
 */

public class Sorting<T>{
	
	public static int cmp; 
	public static int exch;
	
	public static LinkedNode shellSort(LinkedNode node){
	    if (node == null)
	        return null;


	    LinkedNode sortedList = node;
	    node = node.next;
	    sortedList.next = null;

	    while(node != null) {
	    	cmp++;
	    	
	        @SuppressWarnings("rawtypes")
			final LinkedNode current = node;
	        node = node.next;

	        if ((Integer)current.data < (Integer)sortedList.data){

	        	current.next = sortedList;
	            sortedList = current;
	            cmp++;
	            exch++;
	        } else {

	            LinkedNode search = sortedList;
	            while(search.next != null && (Integer)current.data > (Integer)search.next.data)
	                search = search.next;
	            cmp++;
	            exch++;
	            current.next = search.next;
	            search.next = current;
	            
	        }
	    }

	    return sortedList;
	}
	
public static LinkedNode bubbleSort(LinkedNode head) {
		
		if (head == null)
	        System.out.println("An empty list is already sorted."); 
	    else if (head.getNext() == null)
	        System.out.println("A one-link list is already sorted.");
	    else {
	        LinkedNode current = head;
	        boolean swapDone = true;
	        while (swapDone) {
	            cmp++;
	        	swapDone = false;
	            while (current != null) {
	                cmp++;
	            	if (current.getNext() != null && (Integer)current.data > (Integer)current.getNext().data) {
	            		cmp++;
	                    int temp = (Integer)current.data;
	                    current.setNext(current.getNext());
	                    current.getNext().setElement(temp);
	                    swapDone = true;
	                    exch++;
	                }
	                current = current.getNext();
	            }
	            current = head;
	        } 
	        
	        return current;
	    }
		return head;	
	} 
}