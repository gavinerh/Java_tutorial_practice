package model;

import javax.persistence.*;

@Entity
public class Member extends Person{
	public Member() {}
	
	public Member(String surname, String firstname, String secondname) {
		super(surname, firstname, secondname);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberId")
	private int memberId;
	
	public int getMemberId() {
		return memberId;
	}

	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Member)) {
			return false;
		}
		Member m = (Member) o;
		if(this.getMemberId() == m.getMemberId()) {
			return true;
		}
		return false;
	}
	
}
