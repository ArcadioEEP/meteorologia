package objetos;

import java.util.ArrayList;

/*import java.util.Set;
import java.util.List;
import java.util.Collection;*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table ( name = "ciudad" , schema = "meteorologia" )
public class Ciudad {
	@Id
	@Column(name="id_ciudad")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int numero;
	private String nombre;
	/*@OneToMany
	ArrayList<Tiempo> tiempos = new ArrayList <Tiempo>();*/
	
	public Ciudad() {}
	public Ciudad(String nombre) {
		super();
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
	/*public ArrayList<Tiempo> getTiempos() {
		return tiempos;
	}
	public void setTiempos(ArrayList<Tiempo> tiempos) {
		this.tiempos = tiempos;
	}*/
	
	
}
