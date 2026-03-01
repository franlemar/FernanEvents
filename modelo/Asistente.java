package FernanEvents.modelo;

import FernanEvents.modelo.utilidades.interfaces.Bloqueable;

public class Asistente extends Usuario implements Bloqueable {

    private Entrada[] misEntradas;
    private int numEntradas;
    private boolean bloqueado = false;

    public Asistente(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ASISTENTE);
        misEntradas = new Entrada[10];
        numEntradas = 0;
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
}
