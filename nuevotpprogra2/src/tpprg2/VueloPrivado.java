package tpprg2;

import java.util.ArrayList;
import java.util.List;

public class VueloPrivado extends Vuelo{
	
	private static final String TIPO = "PRIVADO";
	private int jetsNecesarios;
	
	private double precioJet;
	private double precioPasaje;
	private int cantAsientos;
	private int dniComprador;
	private int acompañantes;
	private int capMax;
	
	public VueloPrivado(String origen, String destino, String fecha, int precioPasaje, String horaSalida,
			String horaLlegada, String aeropuertoSalida, String aeropuertoLlegada, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos, Asiento asiento, List<Pasajero> registroPasajeros, Seccion seccion ) {
	
    super(origen, destino, fecha, precioPasaje, horaSalida, horaLlegada,new ArrayList<>() ,aeropuertoSalida, aeropuertoLlegada,
		          asiento, registroPasajeros, seccion, tripulantes);
    if(cantAsientos.length < 0 || cantAsientos.length > 15)
    	throw new RuntimeException("La cantidad de asientos debe ser ");
    }

	public int getCantidadJets() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public double calcularRecaudacion() {
        // Lógica para calcular la recaudación de un vuelo privado, teniendo en cuenta
        // la cantidad de acompañantes y el precio final del vuelo
        double precioFinal = this.precioPasaje * 1.30; // Ejemplo de un 30% adicional por impuestos
        return precioFinal * this.registroPasajeros.size();  // Multiplicado por la cantidad de pasajeros
    }
	public String getTipoVuelo() {
		return String.format("%s (%d)", TIPO, jetsNecesarios);
	    	
	    }

		
	}
	
