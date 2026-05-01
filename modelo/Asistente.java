package FernanEvents.modelo;

import FernanEvents.modelo.utilidades.interfaces.Bloqueable;

import java.util.ArrayList;
import java.util.HashMap;

public class Asistente extends Usuario implements Bloqueable {

    private ArrayList<String> amigosReferidos;
    private HashMap<String, Integer> eventosInscrito;

    public Asistente(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ASISTENTE);
        this.amigosReferidos = new ArrayList<>();
        this.eventosInscrito = new HashMap<>();
    }

    /**
     * Consulta cuántas entradas para un evento tiene un asistente y devuelve el valor. Si no tiene ninguna, devuelve 0
     */
    public int getNumEntradasEvento(String nombreEvento) {
        return eventosInscrito.getOrDefault(nombreEvento.trim(), 0);
    }

    /**
     * Devuelve un HashMap con los eventos a los que el asistente se ha inscrito, así como el número de entradas que tiene
     */
    public HashMap<String, Integer> getEventosInscrito(){
        return eventosInscrito;
    }

    /**
     * Devuelve solo el nombre de los eventos a los que se ha inscrito el asistente
     */
    public ArrayList<String> getNombreEventosInscrito(){
        return new ArrayList<>(eventosInscrito.keySet());
    }

    /**
     * Devuelve el total de eventos a los que se ha inscrito el asistente
     */
    public int getTotalEventosInscrito(){
        return eventosInscrito.size();
    }

    /**
     * Obtiene el array de amigos referidos por el asistente
     */
    public ArrayList<String> getAmigosReferidos() {
        return amigosReferidos;
    }

    /**
     * Establece el array de amigos referidos por el asistente
     */
    public void setAmigosReferidos(ArrayList<String> amigosReferidos) {
        this.amigosReferidos = amigosReferidos;
    }

    /**
     * Bloquea la cuenta del asistente
     */
    public void bloquear() {
        setBloqueado(true);
    }

    /**
     * Desbloquea la cuenta del asistente
     */
    public void desbloquear(){
        setBloqueado(false);
    }

    /**
     * Comprueba si la cuenta del asistente está bloqueada
     */
    public boolean estaBloqueado() {
        return isBloqueado();
    }

    /**
     * Registra la compra de entradas a un evento en el historial del asistente
     */
    public void registraCompraEntrada(String nombreEvento, int cantidadEntradas){
        int cantidadActual = getNumEntradasEvento(nombreEvento.trim());
        eventosInscrito.put(nombreEvento.trim(), cantidadActual + cantidadEntradas);
    }

}
