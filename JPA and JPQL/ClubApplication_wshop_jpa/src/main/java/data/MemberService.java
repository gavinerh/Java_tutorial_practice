package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Member;


public class MemberService {
	protected EntityManager em;
	
	public MemberService(EntityManager em) {
		this.em = em;
	}
	
	public Member createMember(String surname, String firstname, String secondname) {
		Member m = new Member(surname, firstname, secondname);
		em.persist(m);
		return m;
	}
	
	public List<Member> listAllMembers(){
		TypedQuery<Member> query = em.createQuery("SELECT e from Member e", Member.class);
		return query.getResultList();
	}
	
	public void removeMember(Member m) {
		em.getTransaction().begin();
		em.remove(m);
		em.getTransaction().commit();
		System.out.println("Member deleted: " + m.getSurname());
	}
	
	public Member findMember(int id) {
		return em.find(Member.class, id);
		
	}
}
