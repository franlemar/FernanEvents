package FernanEvents.modelo;

public class GestionEvento {

    private Evento[] eventos;
    private int numEventos;

    public GestionEvento(int tamanio){
        eventos = new Evento[tamanio];
        numEventos = 0;
    }

    public Evento[] getEventos() {
        return eventos;
    }

    public void setEventos(Evento[] eventos) {
        this.eventos = eventos;
    }

    public int getNumEventos() {
        return numEventos;
    }

    public void setNumEventos(int numEventos) {
        this.numEventos = numEventos;
    }
}
