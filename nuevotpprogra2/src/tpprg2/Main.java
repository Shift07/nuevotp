package tpprg2;

import java.util.List;

public class Main {
	public static void main(String[] args) {
        // Instancia de la aerolínea
        Aerolinea aerolinea = new Aerolinea("BondiJet", "123456789");

        try {
            // Agregar aeropuertos
            aerolinea.registrarAeropuerto("Aeroparque", "Argentina", "Buenos Aires", "Av. Costanera");
            aerolinea.registrarAeropuerto("Bariloche", "Argentina", "Río Negro", "Calle Tronador");
            aerolinea.registrarAeropuerto("Barajas", "España", "Madrid", "Av. Madrid");
            
            // Registrar un vuelo nacional
            double[] preciosNac = { 5000.0, 10000.0 };
            int[] asientosNac = { 150, 20 };
            String codVueloNac = aerolinea.registrarVueloPublicoNacional("Aeroparque", "Bariloche", "15/12/2024", 6, 2000, preciosNac, asientosNac);
            
            System.out.println("Detalle del vuelo nacional:");
            System.out.println(aerolinea.detalleDeVuelo(codVueloNac));
            
            // Registrar un vuelo internacional
//            double[] preciosInt = { 15000.0, 30000.0, 50000.0 };
//            int[] asientosInt = { 200, 30, 10 };
//            String[] escalas = {};
//            String codVueloInt = aerolinea.registrarVueloPublicoInternacional("Ezeiza", "Barajas", "10/02/2025", 8, 2000, 3, preciosInt, asientosInt, escalas);
//            
//            System.out.println("Detalle del vuelo internacional:");
//            System.out.println(aerolinea.detalleDeVuelo(codVueloInt));
//            
            //Registrar un cliente y un vuelo privado
            aerolinea.registrarCliente(98765432, "Carlos Sanchez", "011-2345-6789");
            int[] acompaniantes = {};
            String codVueloPriv = aerolinea.VenderVueloPrivado("Aeroparque", "Bariloche", "07/01/2025", 4, 450000, 98765432, acompaniantes);
            
            System.out.println("Detalle del vuelo privado:");
            System.out.println(aerolinea.detalleDeVuelo(codVueloPriv));
            
        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
	
	
}



