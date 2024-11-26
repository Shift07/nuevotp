package tpprg2;

public class Aeropuerto {
	protected String nombre;
	protected String pais;
	protected String provincia;
	protected String direccion;
	
	public Aeropuerto(String nombre, String pais, String provincia, String direccion) {
		this.nombre=nombre;
		this.pais=pais;
		this.provincia=provincia;
		this.direccion=direccion;
	}
	
	@Override
	public String toString() {
		return "Aeropuerto [nombre=" + nombre + ", pais=" + pais + ", provincia=" + provincia + ", direccion=" + direccion + "]";
	}
}