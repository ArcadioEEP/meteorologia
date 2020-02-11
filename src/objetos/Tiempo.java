package objetos;

import java.util.ArrayList;
import java.util.Date;

/*import java.util.Set;
import java.util.List;
import java.util.Collection;*/

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import managers.TiempoManager;

@Entity
@Table ( name = "tiempo" , schema = "meteorologia" )
public class Tiempo {
	
	@EmbeddedId
	TiempoPK clave;
	private int tempMin;
	private int tempMax;
	@ManyToOne
	@JoinColumn(name = "estado")
	Estado estado;
	
	public Tiempo() {}
	
	public Tiempo(TiempoPK clave, int tempMin, int tempMax, Estado estado) {
		super();
		this.clave = clave;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.estado = estado;
	}

	public TiempoPK getClave() {
		return clave;
	}
	public void setClave(TiempoPK clave) {
		this.clave = clave;
	}
	public int getTempMin() {
		return tempMin;
	}
	public void setTempMin(int tempMin) {
		this.tempMin = tempMin;
	}
	public int getTempMax() {
		return tempMax;
	}
	public void setTempMax(int tempMax) {
		this.tempMax = tempMax;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
