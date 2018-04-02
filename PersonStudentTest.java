import junit.framework.TestCase;

/**
 * A test of the Person and Student classes
 * @author Andrew Budihardja
 *
 */
public class PersonStudentTest extends TestCase{
	
	/**
	 *tests student and person
	 */
	public static void main(String[]args){
		Student s = new Student("Maria",28,3.8);
		System.out.println(s.speak());// Uses speak from person
		
		Person p = s;
		//the static type of p is Person = the type used to declare the variable p.
		//the dynamic type of p is Student = the type of the object that p points to
		System.out.println(p.speak());
		
		System.out.println(p.speak());
			//the statement compiles because speak is in Person
			//at run time, speak from student is called (= dynamic binding or dynamic dispatch)
		
		//Another example of dynamic binding
		String[]names = {"Mark","Wenbin","Zamora","David"};
		Person[] a = new Person[names.length];
		for(int i=0;i<a.length;i++){
			int age = (int)(Math.random()*10+20);
			if(Math.random()<0.5){
				a[i] = new Person(names[i],age);
			}else{
				double gpa = Math.random()*0.5 +3.5;
				a[i] = new Student(names[i], age, gpa);
				
			}
		}
		// dynamic binding
		for (Person ps: a){
			System.out.println(ps.speak());
			System.out.println();
			}
		}
	// A unit test method: the method name starts with test
	public void testpersonStudent(){
		Person p = new Student("Uyen",29,3.9);
		assertTrue(p.speak().contains("gpa"));
	}
	
}

