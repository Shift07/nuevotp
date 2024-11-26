package tpprg2;

public class Pasajero extends Cliente{
    protected int nroAsiento;
    protected String seccion;
    protected String codigo;
    private Vuelo vueloActual;
    private Asiento asientoActual;

    
    
	public Pasajero(int dni, String nombre, String telefono, int nroAsiento) {
        super(dni, nombre, telefono);
        this.nroAsiento = nroAsiento;
    }



	 public void reprogramarVuelo(Vuelo nuevoVuelo, Asiento nuevoAsiento) {
	        if (nuevoVuelo != null && nuevoAsiento != null) {
	            // Cambiar vuelo y asignar asiento
	            this.vueloActual = nuevoVuelo;
	            this.asientoActual = nuevoAsiento;
	        } else {
	            throw new IllegalArgumentException("El nuevo vuelo o asiento no son válidos.");
	        }
	    }


	public boolean hayAsientosDisponibles() {
		// TODO Auto-generated method stub
		return false;
	}



	public Asiento asignarAsiento(Pasajero pasajero) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

    
}