package tpprg2;

public class Asiento {
	    public boolean disponible;

	    public Asiento() {
	        this.disponible = true; // El asiento comienza disponible
	    }

	    public boolean isDisponible() {
	        return disponible;
	    }

	    public void ocupar() {
	        this.disponible = false;
	    }

		public void liberar() {
			// TODO Auto-generated method stub
			
		}

		public Object getPasajero() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getSeccion() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void asignarA(Pasajero pasajero) {
	        if (disponible) {
	            this.disponible = false; // Marcar el asiento como ocupado
	            // Aquí puedes agregar lógica para asociar el pasajero al asiento si es necesario
	        }
	    }
	}
	