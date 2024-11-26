package tpprg2;

import java.util.ArrayList;
import java.util.List;

public class Seccion {
    private List<Asiento> asientos;

    public Seccion(int cantidadAsientos) {
        asientos = new ArrayList<>();
        for (int i = 0; i < cantidadAsientos; i++) {
            asientos.add(new Asiento());
        }
    }
    
	public boolean hayAsientosDisponibles() {
        for (Asiento asiento : asientos) {
            if (asiento.isDisponible()) {
                return true; // Si hay al menos un asiento disponible, devuelve true
            }
        }
        return false; // Si no hay asientos disponibles
    }

	public Asiento asignarAsiento(Pasajero pasajero) {
		for (Asiento asiento : asientos) {
            if (asiento.isDisponible()) {
                asiento.asignarA(pasajero); // Asignamos el asiento al pasajero
                return asiento;
            }
        }
        return null; // Si no hay asientos disponibles
    }

}
