package tpprg2;

import java.util.List;

public class VueloInternacional extends VueloPublico {
	private String escala;
	
	public VueloInternacional(String origen, String destino, String fecha, int precioPasaje, String horaSalida,
			String horaLlegada, String aeropuertoSalida, String aeropuertoLlegada, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos, Asiento asiento,
			List<Pasajero> registroPasajeros, Seccion seccion, String escala) {
		super(origen, destino, fecha, precioPasaje, horaSalida, horaLlegada, aeropuertoSalida, aeropuertoLlegada, tripulantes,
				valorRefrigerio, precios, cantAsientos, asiento, registroPasajeros, seccion);
		this.escala = escala;
		// TODO Auto-generated constructor stub
	}

	private int refrigerio;
	private static final String TIPO = "INTERNACIONAL";
	



	public void agregarEscala(String string) {
		// TODO Auto-generated method stub
		
	}



	public void setCodigoVuelo(String codigoVuelo) {
		// TODO Auto-generated method stub
		
	}
	
	 public double calcularPrecioAsiento(Pasajero pasajero) {
	        // Lógica para calcular el precio del asiento según la sección
	        return this.precioPasaje;  
	    }

	    public double calcularRefrigerios() {
	        // Lógica para calcular el costo de los refrigerios
	        return this.pasajeros.size() * this.refrigerio * 2000; 
	    }
	    
	    public String getTipoVuelo() {
	    	return TIPO;
	        	
	        }

}