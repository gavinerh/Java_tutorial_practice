package java1;

import java.util.*;

interface IClub{
	public void addMember(String surname, String firstname, String secondname);
	public boolean removeMember(int memberNumber);
}

public class Club implements IClub{
	private int currentNumber;
	private List<Member> members;
	private int numberOfMembers;
	private Map<String, Facility> facilities;
	
	public Club() {
		this.currentNumber = 0;
		numberOfMembers = 0;
		members = new ArrayList<Member>();
		facilities = new HashMap<String, Facility>();
	}

	public Member getMember(String surname, String firstname, String secondname){
		for(Member m : members){
			if(m.getSurName() == surname && m.getSecondName() == secondname && m.getFirstName() == firstname){
				return m;
			}
		}
		return null;
	}
	
	public void addMember(String surname, String firstname, String secondname) {
		currentNumber++;
		numberOfMembers++;
		Member member = new Member(surname, firstname, secondname, currentNumber);
		addToMemberList(member);
	}
	
	public boolean removeMember(int memberNumber) {
		if(memberNumber > currentNumber) {
			return false;
		}
		for(Member m : members){
			if(m.getMemberNumber() == memberNumber){
				removeFromList(m);
				break;
			}
		}

//		for(int i=0; i<numberOfMembers; i++) {
//			if(members[i].getMemberNumber() == memberNumber) {
//				// member is found
//				removeFromArray(i);
//				numberOfMembers--;
//				return true;
//			}
//		}
		return false;
	}

	private void removeFromList(Member member){
		members.remove(member);
	}
	
	private void removeFromArray(int index) {
//		for(int i=index; i<numberOfMembers; i++) {
//			if(members[i+1] != null) {
//				members[i] = members[i+1];
//			}else {
//				//remove the current
//				members[i] = null;
//			}
//		}
	}

	private void addToMemberList(Member m){
		members.add(m);
	}
	
	private void addToMemberArray(Member member) {
//		if(numberOfMembers > members.length) {
//			//extend the array length
//			Member[] newArray = new Member[members.length * 2];
//			for(int i=0; i<members.length; i++) {
//				newArray[i] = members[i];
//			}
//			members = newArray;
//		}
//		members[numberOfMembers - 1] = member;
	}

	//============================================= Facility handling==============

	public void addFacility(String name, String description){
		Facility f = new Facility(name, description);
		facilities.put(name, f);
	}

	public Facility getFacility(String name){
		if(facilities.containsKey(name)){
			return facilities.get(name);
		}
		return null;
	}

	public Map<String, Facility> getFacilities(){
		return facilities;
	}

	public boolean removeFacility(String name){
		if(facilities.containsKey(name)){
			facilities.remove(name);
			return true;
		}
		return false;
	}

	public void show(){
		showFacilities();
		showMembers();
	}

	public void showFacilities(){
		for(String key : facilities.keySet()){
			facilities.get(key).show();
		}
		System.out.println("======================================");
	}

	public void showFacilitiesSortedByName(){
		List<Facility> listFacility = new ArrayList<>(facilities.values());
		Collections.sort(listFacility, new Comparator<Facility>() {
			@Override
			public int compare(Facility o1, Facility o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for(Facility f : listFacility){
			f.show();
		}
		System.out.println("======================================");
	}

	public void showFacilitiesSortedByDescription(){
		// get the entry set of the hashmap
		List<Facility> listFacility = new ArrayList(facilities.values());
		Collections.sort(listFacility, new Comparator<Facility>() {
			@Override
			public int compare(Facility o1, Facility o2) {
				return o1.getDescription().compareTo(o2.getDescription());
			}
		});
		for(Facility f : listFacility){
			f.show();
		}
		System.out.println("======================================");
	}

	public void showMembers() {
		for(Member m : members) {
			m.show();
		}
		System.out.println("========================");
	}

	public void showMembersSorted(){
		Collections.sort(members);
		for(Member m : members){
			m.show();
		}
	}
}