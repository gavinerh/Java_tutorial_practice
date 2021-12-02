package java1;

import java.util.Comparator;

public class Facility {
	private String name;
	private String description;
	
	public Facility(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void show() {
		if (description == null) {
			System.out.println("Facility : " + name);
		}
		else {
			System.out.println("Facility : " + name + " (" + description + ")");
		}
	}

	@Override
	public boolean equals(Object o){
		Facility f = (Facility) o;
		if (!this.name.equals(f.getName())) return false;
		if (!this.description.equals(f.getDescription())) return false;
		return true;
	}


		
}
