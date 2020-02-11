package managers;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import objetos.Ciudad;
import objetos.Usuario;

public class CiudadManager {
private static EntityManager entityManager;
	
	public static void crearCiudad(Ciudad ciudad) {
		if(entityManager == null || !entityManager.isOpen()) {
	   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
	   	}	
        entityManager.getTransaction().begin();
        entityManager.persist(ciudad);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
	 public static void eliminarCiudad(Ciudad ciudad) {
		 if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
	   		entityManager.getTransaction().begin();
	   		//merge es unir.
	   		entityManager.remove(entityManager.contains(ciudad)
	   				?ciudad:entityManager.merge(ciudad));
	   		entityManager.getTransaction().commit();
	   		entityManager.close(); 
	   }
	 public static ArrayList<Ciudad> consultarCiudades() {
		   	ArrayList<Ciudad> ciudades= new ArrayList<Ciudad>();
		   	if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		   	//entityManager.getTransaction().begin();
		   	ciudades= (ArrayList<Ciudad>) entityManager
		   			.createQuery("Select c from objetos.Ciudad c").getResultList();
		       return ciudades;
	 }
	 public static ArrayList<Ciudad> consultarCiudad(int i) {
		   	ArrayList<Ciudad> ciudades= new ArrayList<Ciudad>();
		   	if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		   	//entityManager.getTransaction().begin();
		   	ciudades= (ArrayList<Ciudad>) entityManager
		   			.createQuery("Select c from objetos.Ciudad c where c.numero = " + i).getResultList();
		       return ciudades;
	 }
	 public static int comprobarCiudadExiste(String ciudad) {
			int existe = 1;
			ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
			if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		   	//entityManager.getTransaction().begin();
		   	ciudades = (ArrayList<Ciudad>) entityManager
		   			.createQuery("Select c from objetos.Ciudad c where c.nombre = '" 
		   						+ ciudad + "'").getResultList();
		    if(!ciudades.isEmpty()) {
		    	existe=0;
		    }
		    return existe;
		    
	   }
}
