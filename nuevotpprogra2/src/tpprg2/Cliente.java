package tpprg2;

public class Cliente {
	protected Integer dni;
	protected String nombre;
	protected String telefono;
	
	public Cliente(int dni, String nombre, String telefono) {
		this.dni=dni;
		this.nombre=nombre;
		this.telefono=telefono;
	}

	public boolean coincideDni(int dni) {
        return this.dni.equals(dni);
    }
}