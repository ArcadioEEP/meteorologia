package managers;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import objetos.Ciudad;
import objetos.Estado;

public class EstadoManager {
	private static EntityManager entityManager;
	public static ArrayList<Estado> consultarEstados() {
		   	ArrayList<Estado> estados= new ArrayList<Estado>();
		   	if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}		   	
		   //entityManager.getTransaction().begin();
		   	estados = (ArrayList<Estado>) entityManager
		   			.createQuery("Select e from objetos.Estado e").getResultList();
		       return estados;
	 }
	
}
