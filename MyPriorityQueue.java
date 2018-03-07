import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author Andrew Budihardja
 */

public class MyPriorityQueue<T extends Comparable<T>>{
	
	private ArrayList<T> pqElements;
	
	int pqSize;

//	@SuppressWarnings("unchecked")
	public MyPriorityQueue(){

		pqElements =  new ArrayList<T>();
		
		pqSize = 0;
	}
	
	public void add(T item){
			
		pqElements.add(item);
		

		pqSize++;
		//System.out.println(pqSize);

		percolateUp();	
	}
	
	public T removeMin(){
		
        if (pqElements.size() == 0) {
            throw new NoSuchElementException("PQ is empty");
            
        }
        if (pqElements.size() == 1) {
        	pqSize--;
            return pqElements.remove(0);
            
        }
        
        T temp = pqElements.get(0);
        
        pqElements.set(0, pqElements.remove(pqElements.size()-1));
        pqSize--;
        
        percolateDown();
      
        return temp;
	}

	public void percolateUp(){ 
	
			int k = pqElements.size()-1;		
			while(k > 0){
	
				T item = pqElements.get(k);
				int p = (k-1)/2;
				T parent  = pqElements.get(p);
				
				
				if(item.compareTo(parent) < 0){
					//swap 
					pqElements.set(p, item);
					pqElements.set(k, parent);
					
					
					k = p;
				}else{
					break;
				}					
		}
	}
	
	public void percolateDown(){
		
		int k = 0;
        int l = 2*k+1;
     
        while (l < pqElements.size()) {

            int min=l;
            int r=l+1;
            
            if (r < pqElements.size()) { 
            	
                if (pqElements.get(l).compareTo(pqElements.get(r)) <= 0){
                    min = l;
                }else{
                	min = r;
                }
            }
            
            if (pqElements.get(k).compareTo(pqElements.get(min)) >  0){
            	
                    // switch
                    T temp = pqElements.get(k);
                    pqElements.set(k, pqElements.get(min));
                    pqElements.set(min, temp);
                    k = min;
                    l = 2*k+1;
            }else{
                break;
                
            }
        }
	}
	
	public int getSize(){
		
		return pqSize;
	}
	
	public boolean isEmpty(){
		
		return pqSize==0;
	}	
}
