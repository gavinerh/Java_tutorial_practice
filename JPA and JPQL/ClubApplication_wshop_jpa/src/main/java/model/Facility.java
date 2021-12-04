package model;

import javax.persistence.*;

@Entity
public class Facility {
	public Facility() {
	   }

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	   private int id;
	   private String name;
	   private String description;

	   public Facility(String name) {
	      this(name, null);
	   }

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

	   public int getId() {
	      return id;
	   }

	   public void setId(int id) {
	      this.id = id;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }

	   public void setDescription(String description) {
	      this.description = description;
	   }

	   public String toString() {
	      String fullName = name;
	      if (description != null) {
	         fullName += " (" + description + ")";
	      }
	      return (fullName);
	   }

}
