package FernanEvents.modelo;

import FernanEvents.modelo.utilidades.interfaces.Bloqueable;

public class Asistente extends Usuario implements Bloqueable {

    private boolean bloqueado = false;
    private String[] amigosReferidos;
    private int numAmigosReferidos;
    private String[] eventosInscrito;
    private int[] cantidadEntradasEvento;
    private int contadorInscripciones;

    public Asistente(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ASISTENTE);
        amigosReferidos = new String[10];
        numAmigosReferidos = 0;
        eventosInscrito = new String[100];
        cantidadEntradasEvento = new int[100];
        contadorInscripciones = 0;
    }

    /**
     * Consulta cuántas entradas para un evento tiene un asistente y devuelve el valor. Si no tiene ninguna, devuelve 0
     */
    public int getNumEntradasEvento(String nombreEvento) {
        for (int i = 0; i < contadorInscripciones; i++) {
            if (eventosInscrito[i].trim().equalsIgnoreCase(nombreEvento.trim())) {
                return cantidadEntradasEvento[i];
            }
        }
        return 0;
    }

    public String[] getEventosInscrito() {
        return eventosInscrito;
    }

    public int[] getCantidadEntradasEvento() {
        return cantidadEntradasEvento;
    }

    public int getContadorInscripciones() {
        return contadorInscripciones;
    }

    /**
     * Obtiene el array de amigos referidos por el asistente
     */
    public String[] getAmigosReferidos() {
        return amigosReferidos;
    }

    /**
     * Establece el array de amigos referidos por el asistente
     */
    public void setAmigosReferidos(String[] amigosReferidos) {
        this.amigosReferidos = amigosReferidos;
    }

    /**
     * Obtiene el número de amigos referidos por el asistente
     */
    public int getNumAmigosReferidos() {
        return numAmigosReferidos;
    }

    /**
     * Establece el número de amigos referidos por el asistente
     */
    public void setNumAmigosReferidos(int numAmigosReferidos) {
        this.numAmigosReferidos = numAmigosReferidos;
    }

    /**
     * Bloquea la cuenta del asistente
     */
    @Override
    public void bloquear() {
        this.bloqueado = true;
    }

    /**
     * Desbloquea la cuenta del asistente
     */
    public void desbloquear(){
        this.bloqueado = false;
    }

    /**
     * Comprueba si la cuenta del asistente está bloqueada
     */
    @Override
    public boolean estaBloqueado() {
        return this.bloqueado;
    }

    /**
     * Registra la compra de entradas a un evento en el historial del asistente
     */
    public void registraCompraEntrada(String nombreEvento, int cantidadEntradas){
        boolean eventoEncontrado = false;
        for (int i = 0; i < contadorInscripciones; i++) {
            if(eventosInscrito[i].equalsIgnoreCase(nombreEvento.trim())){
                cantidadEntradasEvento[i] += cantidadEntradas;
                eventoEncontrado = true;
                break;
            }
        }

        if(!eventoEncontrado && contadorInscripciones < eventosInscrito.length){
            eventosInscrito[contadorInscripciones] = nombreEvento;
            cantidadEntradasEvento[contadorInscripciones++] = cantidadEntradas;
        }
    }

}
