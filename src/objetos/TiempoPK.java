package objetos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class TiempoPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Date fecha;
	int ciudad;
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCiudad() {
		return ciudad;
	}
	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}
	
	public TiempoPK() {
		super();
	}
	public TiempoPK(Date fecha, int ciudad) {
		super();
		this.fecha = fecha;
		this.ciudad = ciudad;
	}
	  @Override
	  public int hashCode() {
		  int codigo=this.fecha.hashCode() + this.ciudad;
		  return codigo;
	  }
	  
	  @Override
	  public boolean equals(Object obj) {
		  boolean iguales=false;
		  TiempoPK tiempo2=(TiempoPK)obj;
		  if(tiempo2.ciudad == this.ciudad) {
			  if(tiempo2.fecha.compareTo(this.fecha)==0) {
				  iguales=true;
			  }
		  } 
		  return iguales;
	  }
	
	
	
	
	
	
	
	
	
	
	

}
