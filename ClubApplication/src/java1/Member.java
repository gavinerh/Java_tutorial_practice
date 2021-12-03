package java1;

public class Member extends Person implements Comparable {
	public Member(String surname, String firstname, String secondname, int membernumber) {
		super(surname, firstname, secondname);
		this.memberNumber = membernumber;
	}
	
	private int memberNumber;
	
	public int getMemberNumber() {
		return memberNumber;
	}
	
	public void show() {
		System.out.println(toString());
	}

	@Override
	public int compareTo(Object o){
		if(!(o instanceof Member)){
			return -1;
		}
		Member instance = (Member) o;
		int bySurName = this.getSurName().compareTo(instance.getSurName());
		if(bySurName != 0){
			return bySurName;
		}
		int byFirstName = this.getFirstName().compareTo(instance.getFirstName());
		if(byFirstName != 0){
			return byFirstName;
		}
		int bySecondName = this.getSecondName().compareTo(instance.getSecondName());
		return bySecondName;
	}

	@Override
	public boolean equals(Object o){
		Member m = (Member) o;
		if (this.memberNumber != m.getMemberNumber()) return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " " + memberNumber;
	}
}
