package objetos;

/*import java.util.Set;
import java.util.List;
import java.util.Collection;*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table ( name = "usuario" , schema = "meteorologia" )
public class Usuario {
	@Id
	@Column(name="id_usuario")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int numero;
	private String nombre;
	private String password;
	
	
	public Usuario() {}
	public Usuario(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}
		public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
