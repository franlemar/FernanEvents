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

    public Entrada[] getMisEntradas() {
        return misEntradas;
    }

    public void setMisEntradas(Entrada[] misEntradas) {
        this.misEntradas = misEntradas;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public String[] getAmigosReferidos() {
        return amigosReferidos;
    }

    public void setAmigosReferidos(String[] amigosReferidos) {
        this.amigosReferidos = amigosReferidos;
    }

    public int getNumAmigosReferidos() {
        return numAmigosReferidos;
    }

    public void setNumAmigosReferidos(int numAmigosReferidos) {
        this.numAmigosReferidos = numAmigosReferidos;
    }

    @Override
    public void bloquear() {
        this.bloqueado = true;
    }

    public void desbloquear(){
        this.bloqueado = false;
    }

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
