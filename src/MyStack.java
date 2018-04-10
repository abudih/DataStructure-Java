/**
 * @author Andrew Budihardja
 */

import java.util.Arrays;

public class MyStack {
	
	
	private int top;
	private String[] theStack ;
	
	
	public MyStack(){
		theStack = new String[100];
		top = -1;
	}
	
	public boolean isEmpty(){
		
		return (top==-1);	
	}
	
	public void push(String item){
		
		if (top == theStack.length - 1) {
			String[] newArr = Arrays.copyOf(theStack, theStack.length*2);
			theStack=newArr;
 
        } else {
            theStack[++top] = item;
        }
	}
	
	public String pop(){
		
		 if (top < 0) {
	         
			 throw new IndexOutOfBoundsException("Stack is empty");
			 
	        } else {
	  
	            return theStack[top--];
	        }
	}
	
	public String peek(){
		
		if (top == -1){
            throw new IllegalArgumentException("Stack is empty");
		}
		return theStack[top];
	}
	
	public int size(){
		
		return (top+1);
	}
	
	public String toString(){
		
		StringBuffer theString = new StringBuffer("[ ") ;
		for(int i=size()-1;i>-1;i--) {
			
			 theString.append(theStack[i]+' ');			 
			 if(i!=0)
				 theString.append(',');
 			 
		}
		theString.append(']');
		return theString.toString();
	}
}
