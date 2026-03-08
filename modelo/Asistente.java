package FernanEvents.modelo;

import FernanEvents.modelo.utilidades.interfaces.Bloqueable;

public class Asistente extends Usuario implements Bloqueable {

    private Entrada[] misEntradas;
    private int numEntradas;
    private boolean bloqueado = false;
    private String[] amigosReferidos;
    private int numAmigosReferidos;

    public Asistente(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ASISTENTE);
        misEntradas = new Entrada[10];
        numEntradas = 0;
        amigosReferidos = new String[10];
        numAmigosReferidos = 0;
    }

    /**
     * Obtiene el array de entradas del asistente
     */
    public Entrada[] getMisEntradas() {
        return misEntradas;
    }

    /**
     * Establece el array de entradas del asistente
     */
    public void setMisEntradas(Entrada[] misEntradas) {
        this.misEntradas = misEntradas;
    }

    /**
     * Obtiene el número de entradas del asistente
     */
    public int getNumEntradas() {
        return numEntradas;
    }

    /**
     * Establece el número de entradas del asistente
     */
    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
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

    //metodos para las entradas
//    public void aniadirEntrada(Entrada entrada){
//        if (numEntradas < misEntradas.length){
//            misEntradas[numEntradas] = entrada;
//            numEntradas++;
//        }
//    }
//
//    //obtener el total de entradas
//    public int getTotalEntradasCompradas(){
//        int total=0;
//        for (int i = 0; i < numEntradas; i++) {
//            total= total+ misEntradas[i].getCantidad();
//        }
//        return total;
//    }

}
