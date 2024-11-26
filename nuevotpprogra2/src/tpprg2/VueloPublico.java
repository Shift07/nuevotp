package tpprg2;

import java.util.ArrayList;
import java.util.List;


public class VueloPublico extends Vuelo {
	public static final int costoRefrigerio = 2000;
	//private List<Asiento> asientos;
	private double valorRefrigerio;
    private double[] precios;
    private int[] cantAsientos;
	
	
	public VueloPublico(String origen, String destino, String fecha, int precioPasaje, String horaSalida,
			String horaLlegada, String aeropuertoSalida, String aeropuertoLlegada, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos, Asiento asiento, List<Pasajero> registroPasajeros, Seccion seccion ) {
	
    super(origen, destino, fecha, precioPasaje, horaSalida, horaLlegada,new ArrayList<>() ,aeropuertoSalida, aeropuertoLlegada,
		          asiento, registroPasajeros, seccion, tripulantes);
    
    if(cantAsientos.length < 0 || cantAsientos.length > 15)
    	throw new RuntimeException("La cantidad de asientos debe ser ");
	
    //QUEDA PENDIENTE SACAR EL SUPER ( )
	this.valorRefrigerio=valorRefrigerio;
	this.precios=precios;
	this.cantAsientos=cantAsientos;
	
	
    //this.asientos = new ArrayList<>();

	
	// Crear los asientos en función de `cantAsientos`
    for (int i = 0; i < cantAsientos[0] + cantAsientos[1]; i++) {
        this.asientos.add(new Asiento());
    }
}


	public double calcularRecaudacion(Pasajero pasajero) {
        // Lógica para calcular la recaudación de un vuelo privado, teniendo en cuenta
        // la cantidad de acompañantes y el precio final del vuelo
        double precioFinal = this.precioPasaje * 0.20; // Ejemplo de un 20% adicional por impuestos
        return precioFinal * this.registroPasajeros.size();  // Multiplicado por la cantidad de pasajeros
    }
	
public String getTipoVuelo() {
	return null;
    	
    }
}


