package application;

import javax.persistence.*;
import data.FacilityService;
import data.MemberService;
import model.Facility;
import model.Member;

import java.util.List;

public class ClubApplication {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA01");
		EntityManager em = emf.createEntityManager();
		FacilityService fs = new FacilityService(em);
		MemberService ms = new MemberService(em);
		
		seedMember(em, ms);
		seedFacility(em, fs);
	}
	
	public static void seedFacility(EntityManager em, FacilityService fs) {
		em.getTransaction().begin();
		Facility f = fs.createFacility("Gym", "Place to exercise and relax");
		Facility f2 = fs.createFacility("Game Room", "To enjoy a game with friends");
		Facility f3 = fs.createFacility("Swimming pool", "Cool off after a hot day");
		Facility f4 = fs.createFacility("Outdoor activity corner", "Stay outdoors when not raining");
		em.getTransaction().commit();
		System.out.println("Persisted new Facilities");
	}
	
	public static void seedMember(EntityManager em, MemberService ms) {
		em.getTransaction().begin();
		Member m1 = ms.createMember("Gavin", "Erh", "Hean");
		Member m2 = ms.createMember("Bob", "Ho", "Ri");
		Member m3 = ms.createMember("T110E3", "Tank Destroyer", "Very powerful gun");
		Member m4 = ms.createMember("E100", "Heavy Tank", "Very strong armor");
		em.getTransaction().commit();
	}
	
	private static void removeMember(String surname, MemberService ms) {
		List<Member> mList = ms.listAllMembers();
		for(Member m : mList) {
			if(m.getSurname().equals(surname)) {
				ms.removeMember(m);
			}
		}
	}
	
	private static void removeFacility(String name, FacilityService fs) {
		List<Facility> fList = fs.listAllFacilities();
		for(Facility f : fList) {
			if(f.getName().equals(name)) {
				fs.removeFacility(f.getId());
			}
		}
	}
	
}
