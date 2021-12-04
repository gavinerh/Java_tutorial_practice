package data;

import java.util.List;

import javax.persistence.*;

import model.Facility;


public class FacilityService {
   protected EntityManager em;
   
   public FacilityService(EntityManager em) {
      this.em = em;
   }
   
   public Facility createFacility(String name, String description) {
	   Facility f = new Facility();
	   f.setName(name);
	   f.setDescription(description);
	   em.persist(f);
	   return f;
   }
   
   public Facility updateFacility(int id, String name, String description) {
	  Facility f = findFacility(id);
	  f.setDescription(description);
	  f.setName(name);
	  return f;
   }
   
   public void removeFacility(int id) {
	   // does not matter if find is after the transaction
	   // begins or before
	   Facility f = findFacility(id);
	   em.getTransaction().begin();
	   em.remove(f);
	   em.getTransaction().commit();
	   System.out.println("Facility deleted: " + f.getName());
   }
   
   public Facility findFacility(int id) {
      Facility f = em.find(Facility.class, id);
      return f;
   }
   
   public List<Facility> listAllFacilities() {
      TypedQuery<Facility> query = em.createQuery("SELECT e FROM Facility e", Facility.class);
      return query.getResultList();
   }
   
}

