
/**
 * 
 * @author Andrew Budihardja
 *
 */
public class Burger {

	private MyStack theBurger;
	private MyStack temp;
	private MyStack theBurger2;
	private int pattyCount;
	private String pattyType;
	
	public Burger(boolean input){
			
		pattyCount=1;
		pattyType="Beef";
		theBurger = new MyStack();
		temp = new MyStack();
		theBurger2 = new MyStack();
		
		if(input){
	
			theBurger.push("BottomBun");	
			theBurger.push("Ketchup");
			theBurger.push("Mustard");
			theBurger.push("Mushrooms");
			theBurger.push("Beef");
			theBurger.push("Cheddar");
			theBurger.push("Mozarella");
			theBurger.push("Pepperjack");		
			theBurger.push("Onions");
			theBurger.push("Tomato");
			theBurger.push("Lettuce");
			theBurger.push("Baron-Sauce");
			theBurger.push("Mayonnaise");
			theBurger.push("TopBun");
			theBurger.push("Pickle");	
		}else{
			
			theBurger.push("BottomBun");	
			theBurger.push("Beef");		
			theBurger.push("TopBun");
		}
	}
	
	public void rearrange(){
		
		int tempo=0;
		while(!theBurger.isEmpty()){
			
			if(getvalue(theBurger.peek())<tempo){
				tempo=getvalue(theBurger.peek());
			}
		}
	}
	
	public int getvalue(String input){
		
		int value = 0;
		 switch (input) {
         case "Ketchup":  value= 1;
                  break;
         case "Mustard":  value= 2;
                  break;
         case "Mushrooms":  value=3;
                  break;
         case "Beef": value= 4;
                  break;
         case "Chicken":  value=4;
                  break;
         case "Veggie":  value=4;
                  break;
         case "Cheddar":  value=5;
                  break;
         case "Pepperjack":  value=6;
                  break;
         case "Mozarella":  value=7;
                  break;
         case "Onions": value=8;
                  break;
         case "Tomato": value=9;
                  break;
         case "Lettuce": value=10;
                  break;
         case "Baron-Sauce": value=11;
         	break;
         case "Mayonaisse": value=12;
         	break;
         case "TopBun": value=13;
         break;
                  
         default: value = 0;
                  break;
     }
		 return value;
	}
	
	public void changePatties(String thepattyType){

		pattyType=thepattyType;
		while(!theBurger.isEmpty()){
			
			String temps = theBurger.pop();
			if((temps.equals("Beef")||temps.equals("Chicken")||temps.equals("Veggie"))&&
					!temps.equals(thepattyType)){
				temp.push(thepattyType);
			}else{
			temp.push(temps);
			}
		}
		
		while(!temp.isEmpty()){
			theBurger.push(temp.pop());
		}
	}
	
	public void addPatty(){
		
		if(pattyCount==3)
			throw new IllegalArgumentException("patty cannot be more than 3");
		
		addIngredient(pattyType);
		pattyCount++;
	}
	
	public void removePatty(){
		
		int counter=0;
		if(pattyCount<1)
			throw new IllegalArgumentException("patty is already at minimum");
		
		while(!theBurger.isEmpty()){
			
			
			String temporary=theBurger.peek();
			if(temporary.equals(pattyType)&&counter<1){
				theBurger.pop();
				counter++;
			}else{
				temp.push(theBurger.pop());
				
			}
		}
		
		while(!temp.isEmpty()){
			
			theBurger.push(temp.pop());
		}
		pattyCount--;
	}
	
	public void addCategory(String type){
		
		if(type.equals("Cheese")){
			
			addIngredient("Pepperjack");
			addIngredient("Mozarella");
			addIngredient("Cheddar");
		}else if(type.equals("Veggies")){

			addIngredient("Pickle");
			addIngredient("Lettuce");
			addIngredient("Tomato");
			addIngredient("Onions");
			addIngredient("Mushrooms");
		}else{
	
			addIngredient("Baron-Sauce");
			addIngredient("Mayonnaise");
			addIngredient("Mustard");
			addIngredient("Ketchup");	
		}
	}
	
	public void removeCategory(String type){
		
		if(type.equals("Cheese")){
		
			removeIngredient("Cheddar");
			removeIngredient("Mozarella");
			removeIngredient("Pepperjack");
		}else if(type.equals("Veggies")){
			
			removeIngredient("Lettuce");
			removeIngredient("Tomato");
			removeIngredient("Onions");
			removeIngredient("Pickle");
			removeIngredient("Mushrooms");
		}else{
			
			removeIngredient("Ketchup");
			removeIngredient("Mustard");
			removeIngredient("Mayonnaise");
			removeIngredient("Baron-Sauce");
		}
	}

	public void addIngredient(String type){
		
		while(!(theBurger.peek().equals("Beef")||theBurger.peek().equals("Chicken")
				||theBurger.peek().equals("Veggie"))){
			
			theBurger2.push(theBurger.pop());
		}
		
		if(type.equals("Ketchup")||type.equals("Mustard")||type.equals("Mushrooms")||
					type.equals("Beef")||type.equals("Chicken")||type.equals("Veggie")){
				
				String temps=theBurger.pop();
				
				theBurger.push(type);
				
				theBurger.push(temps);
				
				while(!theBurger2.isEmpty()){
					theBurger.push(theBurger2.pop());
				}
		}else if(type.equals("Pickle")){
				
			while(!theBurger2.isEmpty()){
				temp.push(theBurger2.pop());
			}
			
			theBurger2.push("Pickle");
			
			while(!temp.isEmpty()){
				theBurger2.push(temp.pop());
			}
			
			while(!theBurger2.isEmpty()){
				theBurger.push(theBurger2.pop());
			}
		}else {
			 theBurger2.push(type);
			 while(!theBurger2.isEmpty()){
					theBurger.push(theBurger2.pop());
				}
		}	
	}
	
	public void removeIngredient(String type){
	
		while(!theBurger.isEmpty()){
			
			String temporary=theBurger.pop();
			if(!temporary.equals(type)){
				temp.push(temporary);
			}
		}
		while(!temp.isEmpty()){
			
			theBurger.push(temp.pop());
		}	
	}
	
	public String toString(){
		
		return theBurger.toString();
	}
}
