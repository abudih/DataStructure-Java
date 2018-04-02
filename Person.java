/**
 * A simple model of a person
 * 
 * @author Andrew Budihardja
 */
public class Person {
	// a person is defined by a name and age
	private int age;
	private String name;

	/**
	 * creates a person given a name and age
	 * 
	 * @param n
	 *            the name of the person
	 * @param a
	 *            the age of the person
	 */
	public Person(String n, int a) {
		name = n;
		age = a;
	}

	/**
	 * Returns the name and age of the person
	 */
	public String speak() {
		return "name = " + name + ", age = " + age;
	}
}











