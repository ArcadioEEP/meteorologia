package objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table ( name = "estado" , schema = "meteorologia" )
public class Estado {
	@Id
	@Column(name="id_estado")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int numero;
	private String nombre;
	
	public Estado () {}

	public Estado(int numero, String nombre) {
		super();
		this.numero = numero;
		this.nombre = nombre;
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
	
}
