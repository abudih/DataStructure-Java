import java.text.DecimalFormat;

/**
 * A student is a person with gpa
 * @author Andrew Budihardja
 *
 */
public class Student extends Person {
	//for the output
	//Don't declare the decimal format object in speak. It would be created every time speak is called ->
	//Inefficient 
	private static DecimalFormat df = new DecimalFormat("0.00");
	private double gpa;
	
	
	/**
	 * Creates a student given a name, age and gpa
	 * @param name
	 * 			The name of the gpa
	 * @param age
	 * 			The age of the student
	 * @param gpa
	 * 			The gpa of the student
	 */
	
	public Student(String name,int age,double gpa){
		super(name,age);//super has to be the first instruction in the constructor
		this.gpa = gpa;
	}
	/**
	 * Returns a string giving the name,age and gpa of the student
	 */
	@Override
	public String speak() {
		// TODO Auto-generated method stub
		//overrides the speak method from person
		//two uses of the keyword super
		//super(...) is used to call the superclass constructor
		// -> only in the class constructor and it must be the first instruction in that constructor
		
		//super.m() is used to call the method m()
		//in the superclass
		// -> can be used anywhere
		
		//another way to round to 2 decimal places
		double gpa2= Math.round(gpa*100)/100.0;
		// if gpa is 3.567
		// Math.round(3.567) = 356.7
		// 
		
		return super.speak()+",gpa="+gpa+ df.format(gpa);
	}
}
