package managers;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;

import objetos.Ciudad;
import objetos.Tiempo;
import objetos.TiempoPK;
import objetos.Utilidades;

public class TiempoManager {

private static EntityManager entityManager;
	
	public static void crearTiempo(Tiempo Tiempo) {
		if(entityManager == null || !entityManager.isOpen()) {
	   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
	   	}	
        entityManager.getTransaction().begin();
        entityManager.persist(Tiempo);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
	 public static void eliminarTiempo(Tiempo Tiempo) {
		 	if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
	   		entityManager.getTransaction().begin();
	   		//merge es unir.
	   		entityManager.remove(entityManager.contains(Tiempo)
	   				?Tiempo:entityManager.merge(Tiempo));
	   		entityManager.getTransaction().commit();
	   		entityManager.close(); 
	 }
	 public static ArrayList<Tiempo> consultarTiempos() {
		   	ArrayList<Tiempo> Tiempos= new ArrayList<Tiempo>();
		   	if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		   	//entityManager.getTransaction().begin();
		   	Tiempos= (ArrayList<Tiempo>) entityManager
		   			.createQuery("Select t from objetos.Tiempo t").getResultList();
		       return Tiempos;
	 }
	 public static ArrayList<Tiempo> mostrarTiempo(TiempoPK t) {
		 ArrayList<Tiempo> tiempos = new ArrayList<Tiempo>();
		 if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		 //entityManager.getTransaction().begin();
		 tiempos = (ArrayList<Tiempo>) entityManager
		   			.createQuery("Select t from objetos.Tiempo t where ciudad = '"+t.getCiudad()+"' and fecha = '"+Utilidades.fechaEnString(t.getFecha())+"'").getResultList();
		 return tiempos;
	 }
	/* public static Tiempo mostrarTiempo(Ciudad c, Date d) {
		 Tiempo t = new Tiempo();
		 entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		 entityManager.getTransaction().begin();
		 t = (Tiempo) entityManager
		   			.createQuery("Select t from objetos.Tiempo t where "+c.getNombre()+" = ciudad and"+d+" between fecha_ini and fecha_fin").getSingleResult();
		 if(t.getMeteo().length() > 0) {
			 return t; 
		 } else {
			 return null;
		 }
	}*/
	 public static boolean coincide(Tiempo t) {
		 ArrayList<Tiempo> tiempos = new ArrayList<Tiempo>();
		 if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		 //entityManager.getTransaction().begin();
		 tiempos = (ArrayList<Tiempo>) entityManager
		   			.createQuery("Select t from objetos.Tiempo t where ciudad = '"+t.getClave().getCiudad()+"' and fecha = '"+Utilidades.fechaEnString(t.getClave().getFecha())+"'").getResultList();
		 if(tiempos.size() > 0) {
			 return true; 
		 } else {
			 return false;
		 }
	 }
}
