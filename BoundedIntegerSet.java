import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * A collection of integers that contains no duplicates, i.e., a set.  The set
 * is bounded and can only contain integers within a specified range, which is
 * the universe of the set.
 * @author Andrew Budihardja
 */
public class BoundedIntegerSet{
	
	
	Map<Integer,Integer> theSet;
	Map<Integer,Integer> tempSet;
	int theLowerBound;
	int theUpperBound;
	
	int size=0;
	int counter=0;
	/**
	 * Constructs a new, empty <code>BoundedIntegerSet</code> object with the
	 * specified bounds (a.k.a. universe).  The set will only be able to store
	 * integers in the range [<code>lowerBound</code>, <code>upperBound</code>].
	 * 
	 * @param lowerBound
	 *        the lower bound of the set, inclusive.
	 * @param upperBound
	 *        the upper bound of the set, inclusive.
	 * @throws IllegalArgumentException
	 *         if the lower bound is greater than the upper bound.
	 */
	public BoundedIntegerSet(int lowerBound, int upperBound){
		
		if(lowerBound>upperBound){
			throw new IllegalArgumentException();
		}
		
		theSet =new HashMap<Integer,Integer>();
		tempSet = new HashMap<Integer,Integer>();
		theLowerBound=lowerBound;
		theUpperBound=upperBound;
		
		
		for(int i=lowerBound;i<upperBound+1;i++){
			tempSet.put(counter,i);
			counter++;
			
		}
	}

	/**
	 * Adds the specified element to this set if it is not a member of this set.
	 * 
	 * @param element the element to be added to this set.
	 * @return <code>true</code> if the specified element was not a member of
	 *         this set, or <code>false</code> otherwise.
	 * @throws IllegalArgumentException if the specified element is outside
	 *         the bounds for this set.
	 */
	public boolean add(int element){
		boolean isMember=false;
		
		if(element<theLowerBound||element>theUpperBound){
			throw new IllegalArgumentException();
		}
		
		if(!contains(element)){
			isMember=true;
			
			theSet.put(size, element);
			
			tempSet.values().remove(element);
			
			counter--;
			size++;
		}
		return isMember;
	}

	/**
	 * Removes the specified element from this set if it is a member of this
	 * set.
	 * 
	 * @param element the element to be removed from this set.
	 * @return <code>true</code> if the specified element was a member of this
	 *         set, or <code>false</code> otherwise.
	 * @throws IllegalArgumentException if the specified element is outside
	 *         the bounds for this set.
	 */
	public boolean remove(int element){
		boolean isMember=false;
		
		if(element<theLowerBound||element>theUpperBound){
			throw new IllegalArgumentException();
		}
		
		if(contains(element)){
			isMember=true;
			
			
			theSet.values().remove(element);
			
			tempSet.put(counter,element);
			
			counter++;
			size--;
		}
		
		return isMember;
	}

	/**
	 * Tests whether the specified element is a member of this set.
	 * 
	 * @param element the element to test for membership within the set.
	 * @return <code>true</code> if the specified element is a member of
	 *         this set, or <code>false</code> otherwise.
	 * @throws IllegalArgumentException if the specified element is outside
	 *         the bounds for this set.
	 */
	public boolean contains(int element){
		boolean isContain = false;
		
		if(element<theLowerBound||element>theUpperBound){
			throw new IllegalArgumentException();	
		}
		
		if(theSet.containsValue(element)){
			isContain = true;
		}
	
		return isContain;
	}

	/**
	 * Returns the number of members in this set, i.e., its cardinality.
	 * 
	 * @return the number of members in this set.
	 */
	public int size(){

		return size;
	}
	
	
	/**
	 * Returns one of the members of this set chosen at random.  All members
	 * are equally likely to be returned, i.e., with uniform probability.
	 * 
	 * @return a random member of this set.
	 * @throws IllegalStateException if the set is empty.
	 */
	public int getRandomMember(){
		
		Random rn = new Random();
		
		int temp=0;
		temp=rn.nextInt(size+1);
		
		return theSet.get(temp);
	}

	/**
	 * Returns one of the members of the complement of this set chosen at
	 * random.  The complement of this set comprises all integers within the
	 * bounds (a.k.a. universe) of this set that are not members of this set.
	 * All complement members are equally likely to be returned, i.e., with
	 * uniform probability.
	 *  
	 * @return a random member of the complement of this set.
	 * @throws IllegalStateException if the complement of this set is empty.
	 */
	public int getRandomComplementMember(){
		
		int temp=0;
		Random rn = new Random();
		temp=rn.nextInt(tempSet.size());	
		
		return tempSet.get(temp);
	}
}
