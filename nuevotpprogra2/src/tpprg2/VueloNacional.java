package tpprg2;

import java.util.HashMap;
import java.util.List;



public class VueloNacional extends VueloPublico{
	private static final String TIPO = "NACIONAL";
	private String codigo;
	
	

	public VueloNacional(String origen, String destino, String fecha, int precioPasaje, String horaSalida,
			String horaLlegada, String aeropuertoSalida, String aeropuertoLlegada, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos, Asiento asiento,
			List<Pasajero> registroPasajeros, Seccion seccion) {
		super(origen, destino, fecha, precioPasaje, horaSalida, horaLlegada, aeropuertoSalida, aeropuertoLlegada, tripulantes,
				valorRefrigerio, precios, cantAsientos, asiento, registroPasajeros, seccion);
		// TODO Auto-generated constructor stub
	}

	private HashMap<Integer, String> nacionales = new HashMap<>();

	
	
	
	
	public boolean nacional(String origen) {
		if(this.nacionales.containsKey(origen)) {
			return true;
		}
		return false;
	}
	
	public boolean nacional() {
        return true; 
    }
	
	public String getTipoVuelo() {
		return TIPO; 	
	    }
	public String getCodigoVuelo() {
		// TODO Auto-generated method stub
		return codigo;
	}
	
}