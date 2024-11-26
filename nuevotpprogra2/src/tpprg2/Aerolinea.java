package tpprg2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

public class Aerolinea implements IAerolinea {
	private String nombre;
	private String cuit;
	private HashMap<Integer, Cliente> clientes = new HashMap<>();
	private HashMap<String, Aeropuerto> aeropuertos = new HashMap<>();
	private HashMap<String, Vuelo> vuelos = new HashMap<>();
	private HashMap<String,Double> recaudacionPorDestino = new HashMap<>();
    private int contadorVuelosPrivados = 1;
    
    
	public Aerolinea (String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit=cuit;
	}
	@Override

	public void registrarCliente(int dni, String nombre, String telefono) {
		if (this.clientes.containsKey(dni))
			throw new RuntimeException("El cliente ya está registrado");
		clientes.put(dni, new Cliente(dni,nombre,telefono));
	}
	
	@Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		if(this.aeropuertos.containsKey(nombre))
			throw new RuntimeException("Aeropuerto ya registrado");
		aeropuertos.put(nombre, new Aeropuerto(nombre, pais, provincia, direccion));
}
	
	
	
	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
	        double valorRefrigerio, double[] precios, int[] cantAsientos) {

		boolean destinoRegistrado = false;

	    // Buscar si el destino está registrado en los aeropuertos
	    for (Aeropuerto aeropuerto : aeropuertos.values()) {
	        if (aeropuerto.nombre.equalsIgnoreCase(destino)) {
	            destinoRegistrado = true;

	            Asiento asiento = new Asiento();
	            String codVuelo = String.format("%03d-PUB", vuelos.size() + 1); // Generar código único

	            List<Pasajero> registroPasajeros = new ArrayList<>();
	            Seccion seccion = new Seccion(tripulantes);

	            VueloNacional vuelo = new VueloNacional(origen, destino, fecha, 0, origen, destino, fecha, origen,
	                    tripulantes, valorRefrigerio, precios, cantAsientos,
	                    asiento, registroPasajeros, seccion);

	            vuelos.put(codVuelo, vuelo);
	            return codVuelo;
	        }
	    }

	    if (!destinoRegistrado) {
	        throw new RuntimeException("Destino no registrado");
	    }

	    return null;
	}
	
		
	
	@Override
		public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
				double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
			// Verificar que el origen y el destino estén registrados en los aeropuertos de la aerolínea
		    if (!aeropuertos.containsKey(origen) || !aeropuertos.containsKey(destino) ) {
		        throw new RuntimeException("Origen o destino no registrado en la aerolínea");
		    }

		    // Validar longitud de los arrays de precios y cantidades de asientos
		    if (precios.length != 3 || cantAsientos.length != 3) {
		        throw new IllegalArgumentException("La longitud de precios y cantAsientos debe ser 3");
		    }

		    // Crear el objeto VueloInternacional con los datos correspondientes
		    VueloInternacional vuelo = new VueloInternacional(origen, destino, fecha, cantRefrigerios, fecha, fecha, fecha, fecha, cantRefrigerios, valorRefrigerio, precios, cantAsientos, null, null, null, fecha);

		    // Agregar las escalas adicionales, si existen
		    if (escalas.length > 1) {
		        for (int i = 1; i < escalas.length; i++) {
		            vuelo.agregarEscala(escalas[i]); //  agregar escalas
		        }
		    }

		    // Generar un código de vuelo único con el formato {número de vuelo}-PUB
		    String codigoVuelo = 1 + "-PUB";
		    vuelo.setCodigoVuelo(codigoVuelo);

		    // Registrar el vuelo en el HashMap de vuelos
		    vuelos.put(codigoVuelo, vuelo);

		    return codigoVuelo;
		}
	
	
	
	@Override
	public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio,
			int dniComprador, int[] acompaniantes) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaVuelo;
	    
	    try {
	        fechaVuelo = sdf.parse(fecha);
	        
	    } catch (ParseException e) {
	        throw new RuntimeException("Formato de fecha inválido. Use dd/MM/yyyy.");
	    }

	    // 2. Obtener la fecha actual
	    Date fechaActual = new Date();

	    // 3. Comparar la fecha del vuelo con la fecha actual
	    if (fechaVuelo.before(fechaActual)) {
	        throw new RuntimeException("La fecha de vuelo caducó. No se puede vender un vuelo en una fecha anterior.");
	    }
	    
	    Cliente comprador = clientes.get(dniComprador);
	    if (comprador == null) {
	        throw new RuntimeException("El cliente comprador no está registrado.");
	    }
	    
	    
	    // 3. Verificar si los acompañantes están registrados
	    for (int dni : acompaniantes) {
	        if (clientes.get(dni) == null) {
	            throw new RuntimeException("Uno o más acompañantes no están registrados.");
	        }
	    }
	    
	 // 4. Generar un código de vuelo único que termine en "-PRI"
	    String codigoVuelo = contadorVuelosPrivados + "-PRI";
	    contadorVuelosPrivados++;
	    
	    // 5. Crear y registrar el vuelo privado
	    VueloPrivado vueloPrivado = new VueloPrivado(codigoVuelo, codigoVuelo, codigoVuelo, dniComprador, codigoVuelo, codigoVuelo, codigoVuelo, codigoVuelo, dniComprador, precio, null, acompaniantes, null, null, null);
	    vuelos.put(codigoVuelo, vueloPrivado);

	    // 6. Retornar el código del vuelo
	    return codigoVuelo;
	}
	
	
	
	@Override
	public Map<Integer, String> asientosDisponibles(String codVuelo) {
	    Vuelo vuelo = vuelos.get(codVuelo);
	    if (!vuelos.containsKey(codVuelo)) {
	        throw new RuntimeException("El vuelo no existe.");
	    }

	    Map<Integer, String> asientosDisponibles = new HashMap<>();
	    for (int i = 0; i < vuelo.asientos.size(); i++) {
	        Asiento asiento = vuelo.asientos.get(i);
	        if (asiento.isDisponible()) {
	            asientosDisponibles.put(i, "Disponible");
	        }
	    }

	    return asientosDisponibles;
	}
	
	 // Método para buscar un cliente por su DNI sin usar get directamente
    public Cliente buscarCliente(int dni) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.coincideDni(dni)) {
                return cliente;
            }
        }
        throw new RuntimeException("El cliente no está registrado.");
    }
	
	
	@Override
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		 Cliente cliente = buscarCliente(dni);

		    // Verificar si el vuelo existe
		    Vuelo vuelo = vuelos.get(codVuelo);
		    if (!vuelos.containsKey(codVuelo)) {
		        throw new RuntimeException("El vuelo no existe.");
		    }

		    // Verificar si el asiento está disponible
		    if (!vuelo.asientoEstaDisponible(nroAsiento)) {
		        throw new RuntimeException("El asiento ya está ocupado.");
		    }

		    // Registrar al pasajero
		    Pasajero pasajero = new Pasajero(cliente.dni, cliente.nombre, cliente.telefono, nroAsiento);
		    vuelo.registrarPasajero(pasajero);

		    return nroAsiento;
		}
	    //a
	    
	
	@Override
	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
		List<String> vuelosSimilares = new ArrayList<>();

	    // Parsear la fecha de entrada
	    LocalDate fechaInicio = LocalDate.parse(Fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	    // Fecha límite de una semana después
	    LocalDate fechaFin = fechaInicio.plusDays(7);

	    // Recorrer todos los vuelos registrados en la aerolínea
	    for (Vuelo vuelo : vuelos.values()) {
	        // Parsear la fecha del vuelo
	        LocalDate fechaVuelo = LocalDate.parse(vuelo.fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	        // Verificar origen, destino y si la fecha está en el rango
	        if (vuelo.origen.equals(origen) &&
	            vuelo.destino.equals(destino) &&
	            !fechaVuelo.isBefore(fechaInicio) &&
	            !fechaVuelo.isAfter(fechaFin)) {
	            vuelosSimilares.add(vuelo.codigo);
	        }
	    }

	    return vuelosSimilares;
	}
	@Override
	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
			// Verificar si el vuelo existe
		    if (!vuelos.containsKey(codVuelo)) {
		        throw new RuntimeException("El vuelo no existe.");
		    }
		    // Obtener el vuelo
		    Vuelo vuelo = vuelos.get(codVuelo);
		    // Verificar que el asiento sea válido
		    if (nroAsiento < 0 || nroAsiento >= vuelo.asientos.size()) {
		        throw new RuntimeException("Número de asiento inválido.");
		    }
		    // Obtener el asiento
		    Asiento asiento = vuelo.asientos.get(nroAsiento);

		    // Verificar que el asiento está ocupado por el cliente
		    if (!vuelo.asientosOcupados.containsKey(dni) || !vuelo.asientosOcupados.get(dni).equals(asiento)) {
		        throw new RuntimeException("El cliente no tiene este asiento reservado.");
		    }

		    // Liberar el asiento
		    asiento.liberar(); // Método en la clase Asiento para marcarlo como disponible

		    // Remover del mapa de asientos ocupados
		    vuelo.asientosOcupados.remove(dni);
		}

	@Override
	public void cancelarPasaje(int dni, int codPasaje) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<String> cancelarVuelo(String codVuelo) {
		   List<String> resultados = new ArrayList<>();
		   List<String> pasajesNoReprogramados = new ArrayList<>();

		    // Verificar si el vuelo existe
		    if (!vuelos.containsKey(codVuelo)) {
		        throw new IllegalArgumentException("Vuelo no encontrado");
		    }

		    // Obtener el vuelo a cancelar
		    Vuelo vueloACancelar = vuelos.get(codVuelo);
		    
		    // Buscar vuelos alternativos con el mismo destino, pero distinto código
		    List<Vuelo> vuelosAlternativos = new ArrayList<>();
		    for (Vuelo vuelo : vuelos.values()) {
		        if (vuelo.destino.equals(vueloACancelar.destino) && !vuelo.codigo.equals(codVuelo)) {
		            vuelosAlternativos.add(vuelo);
		        }
		    }

		    List<Pasajero> pasajerosReprogramados = new ArrayList<>();

		    // Iterar sobre los pasajeros del vuelo a cancelar
		    for (Pasajero pasajero : vueloACancelar.pasajeros) {
		        boolean reprogramado = false;

		        // Intentar reprogramar al pasajero en uno de los vuelos alternativos
		        for (Vuelo vueloAlternativo : vuelosAlternativos) {
		            for (Seccion seccion : vueloAlternativo.secciones) {
		                if (seccion.hayAsientosDisponibles()) {
		                    // Asignar asiento en el vuelo alternativo
		                    Asiento asientoNuevo = seccion.asignarAsiento(pasajero);
		                    pasajero.reprogramarVuelo(vueloAlternativo, asientoNuevo);

		                    // Agregar al listado de pasajeros reprogramados
		                    pasajerosReprogramados.add(pasajero);
		                    reprogramado = true;

		                    // Crear el registro con los datos del pasajero y el código del vuelo alternativo
		                    StringBuilder registro = new StringBuilder();
		                    registro.append(pasajero.dni)
		                            .append(" - ")
		                            .append(pasajero.nombre)
		                            .append(" - ")
		                            .append(pasajero.telefono)
		                            .append(" - ")
		                            .append(vueloAlternativo.codigo); // Código del nuevo vuelo
		                    resultados.add(registro.toString());
		                    break; // Salir del bucle de secciones
		                }
		            }
		            if (reprogramado) {
		                break; // Salir del bucle de vuelos alternativos si se reprogramó el pasajero
		            }
		        }

		        // Si no se pudo reprogramar, agregar al listado de resultados como CANCELADO
		        if (!reprogramado) {
		            StringBuilder registro = new StringBuilder();
		            registro.append(pasajero.dni)
		                    .append(" - ")
		                    .append(pasajero.nombre)
		                    .append(" - ")
		                    .append(pasajero.telefono)
		                    .append(" - CANCELADO");
		            resultados.add(registro.toString());
		            pasajesNoReprogramados.add(pasajero.codigo); // Guardar el código de pasaje no reprogramado

		            // Eliminar al pasajero del vuelo cancelado
		            vueloACancelar.eliminarPasajero(pasajero);
		        }
		    }

		    // Eliminar el vuelo cancelado de la lista de vuelos
		    vuelos.remove(codVuelo);

		    return resultados;
		}
	
	@Override
	public double totalRecaudado(String destino) {
		 double totalRecaudado = 0.0;

		    // Recorrer todos los vuelos
		    for (Vuelo vuelo : vuelos.values()) {
		        // Comprobar si el destino del vuelo coincide con el parámetro destino
		        if (vuelo.destino.equals(destino)) {

		            // Si es un vuelo privado, sumar el precio con los acompañantes
		            if (vuelo instanceof VueloPrivado) {
		                VueloPrivado vueloPrivado = (VueloPrivado) vuelo;
		                
		                // Calcular recaudación de vuelo privado, con impuestos
		                totalRecaudado += vueloPrivado.calcularRecaudacion();  // El precio ya tiene impuestos incluidos
		            }
		            // Si es un vuelo internacional o nacional público, calcular el valor de los asientos y refrigerios
		            else if (vuelo instanceof VueloPublico) {
		                VueloPublico vueloPublico = (VueloPublico) vuelo;

		                // Recorrer los pasajeros y sumar el precio del asiento según la sección
		                double precioTotalAsientos = 0;
		                for (Pasajero pasajero : vueloPublico.pasajeros) {
		                    precioTotalAsientos += vueloPublico.calcularRecaudacion(pasajero);  // Calcula el precio según la sección
		                }

		                // Calcular el costo de los refrigerios (todos los pasajeros reciben el mismo tipo de refrigerio)
		                double costoRefrigerios = vueloPublico.pasajeros.size() * vueloPublico.costoRefrigerio;

		                // Calcular el valor total con impuestos
		                double recaudacionVueloPublico = (precioTotalAsientos + costoRefrigerios) * 1.20;  // Se le aplica un 20% de impuestos

		                totalRecaudado += recaudacionVueloPublico;
		            }
		        }
		    }

		    return totalRecaudado;
		}
	
	
	
	@Override
	public String detalleDeVuelo(String codVuelo) {
		 if (!vuelos.containsKey(codVuelo)) {
		        throw new IllegalArgumentException("Código de vuelo no existe: " + codVuelo);
		    }

		    StringBuilder sb = new StringBuilder();
		    Vuelo vuelo = vuelos.get(codVuelo);

		    // Obtener el tipo de vuelo usando el método getTipoVuelo()
		    String tipoVuelo = vuelo.getTipoVuelo();

		    // Construir el detalle del vuelo
		    sb.append(codVuelo)
		      .append(" - ")
		      .append(vuelo.origen)
		      .append(" - ")
		      .append(vuelo.destino)
		      .append(" - ")
		      .append(vuelo.fecha)
		      .append(" - ")
		      .append(tipoVuelo);

		    return sb.toString();
		}
}



