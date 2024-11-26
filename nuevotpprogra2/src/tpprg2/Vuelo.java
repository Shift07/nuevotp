package tpprg2;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Vuelo {
	protected String origen;
	protected String destino;
	protected String fecha;
	protected int precioPasaje;
	protected String horaSalida;
	protected String horaLlegada;
    protected String aeropuertoSalida;
	protected String aeropuertoLlegada;
    protected List<Asiento> asientos; // Lista de asientos
    public String codigo;
    protected Asiento asiento;
    protected List<Pasajero> registroPasajeros;
    protected Seccion seccion;
    protected int tripulantes;
    protected HashMap<Integer, Asiento> asientosOcupados = new HashMap<>();
	public List<Pasajero> pasajeros;
	public Seccion[] secciones;
	
    
	public Vuelo(String origen, String destino, String fecha, int precioPasaje, String horaSalida, String horaLlegada,
			List<Asiento> asientos, String aeropuertoSalida, String aeropuertoLlegada, Asiento asiento, List<Pasajero> registroPasajeros,
			Seccion seccion, int tripulantes) {
		
		
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.precioPasaje = precioPasaje;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.aeropuertoSalida = aeropuertoSalida;
		this.aeropuertoLlegada = aeropuertoLlegada;
		this.asiento = asiento;
		this.asientos = asientos;
		this.registroPasajeros =  new ArrayList<>();
		this.seccion = seccion;
		this.tripulantes = tripulantes;
	}


	public void cancelarPasaje (String dni, String codigo, int numeroAsiento) {
		
	}
	
	// Método para verificar si un asiento está disponible
	public boolean asientoEstaDisponible(int nroAsiento) {
        if (nroAsiento < 0 || nroAsiento >= asientos.size()) {
            throw new IllegalArgumentException("Número de asiento inválido.");
        }
        return asientos.get(nroAsiento).isDisponible();
    }

	
	
	public void registrarPasajero(Pasajero pasajero) {
        registroPasajeros.add(pasajero); // Añade el pasajero al registro

        // Marcar el asiento como ocupado
        Asiento asiento = asientos.get(pasajero.nroAsiento); // Acceso directo al atributo nroAsiento
        asiento.ocupar();
    }


	public HashMap<Integer, Asiento> getAsientosOcupados() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean tieneAsientosDisponibles() {
		// TODO Auto-generated method stub
		return false;
	}


	public int asignarAsiento(Object seccion2) {
		// TODO Auto-generated method stub
		return 0;
	}



	public List<Pasajero> ObtenerPasajeros() {
		// TODO Auto-generated method stub
		return pasajeros;
	}


	public void reprogramarPasajero(Pasajero pasajero) {
		// Agregar el pasajero al registro del nuevo vuelo
	    registroPasajeros.add(pasajero);
	    
	    // Buscar un asiento disponible para el pasajero en la nueva sección
	    for (Asiento asiento : asientos) {
	        if (asiento.disponible) {
	            // Asignar el asiento al pasajero y marcarlo como ocupado
	            asiento.ocupar();  // Marcar el asiento como ocupado
	            asientosOcupados.put(pasajero.dni, asiento);  // Registrar el asiento ocupado
	            break;  // Una vez asignado un asiento, salimos del bucle
	        }
	    }
	}

	public void removerPasajero(Pasajero pasajero) {
		// Eliminar al pasajero del registro de pasajeros
	    registroPasajeros.remove(pasajero);
	    
	    // Buscar el asiento que ocupa el pasajero y liberarlo
	    Asiento asiento = asientosOcupados.get(pasajero.dni);
	    if (asiento != null) {
	        asiento.liberar();  // Liberar el asiento
	        asientosOcupados.remove(pasajero.dni);  // Eliminar la entrada del mapa
	    }
	}
	
	public boolean hayAsientosDisponiblesEnSeccion(String seccion2) {
	    // Verificar si la sección tiene asientos disponibles
	    for (Asiento asiento : asientos) {
	        // Verificar que el asiento esté en la sección correcta y disponible
	        if (asiento.getSeccion() == seccion2 && asiento.disponible) {
	            return true;  // Si encontramos un asiento disponible, retornamos true
	        }
	    }
	    return false;  // Si no hay asientos disponibles en la sección, retornamos false
	}


	public void agregarPasajero(Pasajero pasajero) {
        this.pasajeros.add(pasajero);
    }

    // Método para eliminar un pasajero del vuelo
    public void eliminarPasajero(Pasajero pasajero) {
        this.pasajeros.remove(pasajero);
    }
    
    public void reprogramarVuelo(Pasajero pasajero, Vuelo nuevoVuelo) {
        // Eliminar al pasajero del vuelo original
        this.eliminarPasajero(pasajero);

        // Intentar asignar un asiento en el nuevo vuelo
        Asiento asientoNuevo = nuevoVuelo.asignarAsiento(pasajero);

        if (asientoNuevo != null) {
            // Si se asignó un asiento, reprogramar al pasajero en el nuevo vuelo
            pasajero.reprogramarVuelo(nuevoVuelo, asientoNuevo);
            nuevoVuelo.agregarPasajero(pasajero); // Agregar al pasajero al nuevo vuelo
        } else {
            // Si no hay asientos disponibles, lanzar excepción o manejar el error
            throw new IllegalStateException("No hay asientos disponibles en el nuevo vuelo.");
        }
    }
    
    public Asiento asignarAsiento(Pasajero pasajero) {
        for (Seccion seccion : secciones) {
            Asiento asiento = seccion.asignarAsiento(pasajero); // Intenta asignar un asiento
            if (asiento != null) {
                return asiento; // Si se asignó con éxito, se devuelve el asiento
            }
        }
        return null; // Si no se pudo asignar el asiento en ninguna sección
    }
    
    public String getTipoVuelo() {
		return null;
	}
    

    

}
	
    
