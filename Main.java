/**
 * @author Andrew Budihardja
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static ArrayList<String> ommisions = new ArrayList<String>();
	static ArrayList<String> exceptions = new ArrayList<String>();
	static boolean isBaron;
	static String meatType;
	static int pattyCount;
	ArrayList<String> order = new ArrayList<String>();
	static int counter=0;
	static Burger theBurger ;
	
	public static void main(String[]args){
		
		File n = new File("example.txt");
		
		try {
			Scanner sn = new Scanner(n);
			while(sn.hasNextLine()){
				String temp = sn.nextLine();
				parseLine(temp);
				counter++;		
			}	
			sn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void parseLine(String line){
		
		String theLine = line;
		String delim = " ";
		String[] tokens =theLine.split(delim);
		
		meatType="Beef";
		pattyCount=1;
		isBaron=false;
		
		int no1=0;
		int but1=0;
		int with=0;
		int but2=0;
		
		for(int i=0;i<tokens.length;i++){
		
			//System.out.print(tokens[i]+" ");
		
			if(tokens[i].equals("Baron")){
				isBaron=true;
			}
		}
				if(isBaron){
					
				theBurger= new Burger(true);
				
				for(int j=0;j<tokens.length;j++){
					
						if(tokens[j].equals("Triple")){
							pattyCount+=2;
						}
						if(tokens[j].equals("Double")){
							pattyCount++;
						}
						
						
						if(tokens[j].equals("Chicken"))
							meatType="Chicken";
					
						if(tokens[j].equals("Veggie")){
							meatType="Veggie";
						}
						
						if(tokens[j].equals("no")){
							no1=j;	
						}
						
						if(tokens[j].equals("but")){
							but1=j;
						}
				}

				if(no1!=0 && but1!=0){
					for(int n=no1+1;n<but1;n++){
						ommisions.add(tokens[n]);
					}
					for(int m=but1+1;m<tokens.length;m++){
						exceptions.add(tokens[m]);
						
					}

				}else if(no1!=0&& but1==0){
					
					for(int n=no1+1;n<tokens.length;n++){
						ommisions.add(tokens[n]);
					
					}
					
				}
				
				
				if(pattyCount==2){
					theBurger.addPatty();
				}else if(pattyCount==3){
					theBurger.addPatty();
					theBurger.addPatty();
				}
				
				if(meatType=="Veggie"){
					theBurger.changePatties("Veggie");
				}else if(meatType=="Chicken"){
					theBurger.changePatties("Chicken");
				}
				
				
				if(!ommisions.isEmpty()){
					for(int h=0;h<ommisions.size();h++){
						if(ommisions.get(h).equals("Veggies")||ommisions.get(h).equals("Cheese")||
								ommisions.get(h).equals("Sauce")){
							theBurger.removeCategory(ommisions.get(h));
								
						}else{
							
						theBurger.removeIngredient(ommisions.get(h));
						}
						
					}
				}
				
				if(!exceptions.isEmpty()){
					for(int g=0;g<exceptions.size();g++){
						
						theBurger.addIngredient(exceptions.get(g));
					
					}
				}		
					ommisions.clear();
					exceptions.clear();
				}else {
				
				theBurger = new Burger(false);
				
				for(int j=0;j<tokens.length;j++){
					
					if(tokens[j].equals("Triple")){
						pattyCount+=2;
						
						
					}
					if(tokens[j].equals("Double")){
						pattyCount++;
					}
					
					
					if(tokens[j].equals("Chicken"))
						meatType="Chicken";
				
					if(tokens[j].equals("Veggie")){
						meatType="Veggie";
					}
					
					
					if(tokens[j].equals("with")){
						with=j;		
					}
						
					if(tokens[j].equals("but")){
						but2=j;	
					}
				}
				
				if(with!=0 && but2!=0){
						
					for(int n=with+1;n<but2;n++){
						ommisions.add(tokens[n]);
					}	
					for(int m=but2+1;m<tokens.length;m++){
						exceptions.add(tokens[m]);
					}
				}else if(with!=0&& but2==0){
						
					for(int n=but2+1;n<tokens.length;n++){
						ommisions.add(tokens[n]);
						
					}
						
				}

				if(pattyCount==2){
					theBurger.addPatty();
				}else if(pattyCount==3){
					theBurger.addPatty();
					theBurger.addPatty();
				}
				
				if(meatType=="Veggie"){
					theBurger.changePatties("Veggie");
				}else if(meatType=="Chicken"){
					theBurger.changePatties("Chicken");
				}

				if(!ommisions.isEmpty()){
					for(int h=0;h<ommisions.size();h++){
						if(ommisions.get(h).equals("Veggies")||ommisions.get(h).equals("Cheese")||
								ommisions.get(h).equals("Sauces")){
							theBurger.addCategory(ommisions.get(h));
						}else{
					
						theBurger.addIngredient(ommisions.get(h));
						}
					}
				}

				if(!exceptions.isEmpty()){
					
					for(int g=0;g<exceptions.size();g++){
						theBurger.removeIngredient(exceptions.get(g));
					}
				}	
			}
				
		System.out.println("Processing Order "+counter +": "+ line );	
		System.out.println(theBurger.toString());			
		System.out.println();
		 
		ommisions.clear();
		exceptions.clear();
	}
	
	public static void testMyStack(){
		
		MyStack burger = new MyStack();
		System.out.println(burger.isEmpty());
		burger.push("a");
	}
	
	public static void testBurger(){
		
		Burger b = new Burger(true);
		
		b.removeCategory("Veggies");
		b.removeCategory("Sauce");
		
		b.addIngredient("Baron-Sauce");
		
		b.rearrange();
		System.out.println(b.toString());
		
	}
}
