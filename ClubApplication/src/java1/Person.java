package java1;

public class Person {
	private String surName;
	private String firstName;
	private String secondName;
	
	public Person(String surName, String firstName, String secondName) {
		this.surName = surName;
		this.firstName = firstName;
		this.secondName = secondName == null ? "" : secondName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void show() {
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		return "Name is " + surName + " " + firstName + " " + secondName;
	}
	
}
