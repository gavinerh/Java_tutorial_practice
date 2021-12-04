package model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {
	private String surname;
	private String firstname;
	private String secondName;
	
	public Person() {}
	
	public Person(String surname, String firstname, String secondname) {
		this.firstname = firstname;
		this.surname = surname;
		this.secondName = secondname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getFirstName(){
		return firstname;
	}
	
	public String getSecondName() {
		return secondName;
	}
}
