package managers;

import java.util.ArrayList;
import javax.persistence.EntityManager;

import objetos.Usuario;

public class UsuarioManager {
	
	private static EntityManager entityManager;
	
	public static void crearUsuario(Usuario usuario) {
		if(entityManager == null || !entityManager.isOpen()) {
	   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
	   	}	
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
	 public static void eliminarUsuario(Usuario usuario) {
		 if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
	   		entityManager.getTransaction().begin();
	   		//merge es unir.
	   		entityManager.remove(entityManager.contains(usuario)
	   				?usuario:entityManager.merge(usuario));
	   		entityManager.getTransaction().commit();
	   		entityManager.close(); 
	   }
	 public static ArrayList<Usuario> consultarUsuarios() {
		   	ArrayList<Usuario> usuarios= new ArrayList<Usuario>();
		   	if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		   	//entityManager.getTransaction().begin();
		   	usuarios= (ArrayList<Usuario>) entityManager
		   			.createQuery("Select u from objetos.Usuario u").getResultList();
		       return usuarios;
	 }
	 public static boolean comprobarUsuarioExiste(Usuario usuario) {
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			boolean existe=false;
			if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		   	//entityManager.getTransaction().begin();
		   	usuarios = (ArrayList<Usuario>) entityManager
		   			.createQuery("Select u from objetos.Usuario u where u.nombre = '" 
		   						+ usuario.getNombre()+"'").getResultList();
		    if(!usuarios.isEmpty()) {
		    	existe=true;
		    }
		    return existe;
	   }
	 public static boolean comprobarPss(Usuario usuario) {
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			boolean ok=true;
			if(entityManager == null || !entityManager.isOpen()) {
		   		entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		   	}	
		   	//entityManager.getTransaction().begin();
		   	usuarios = (ArrayList<Usuario>) entityManager
		   			.createQuery("Select u from objetos.Usuario u where u.nombre = '" 
		   						+ usuario.getNombre()+"' and u.password = '"+ usuario.getPassword()+"'").getResultList();
		    if(usuarios.isEmpty()) {
		    	ok=false;
		    }
		    return ok;
	   }
}
