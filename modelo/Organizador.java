package FernanEvents.modelo;

import FernanEvents.modelo.utilidades.interfaces.Bloqueable;

public class Organizador extends Usuario implements Bloqueable {
    private boolean bloqueado = false;

    public Organizador(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ORGANIZADOR);
    }

    /**
     * Bloquea la cuenta del organizador
     */
    public void bloquear() {
        this.bloqueado = true;
    }

    /**
     * Desbloquea la cuenta del organizador
     */
    public void desbloquear(){
        this.bloqueado = false;
    }

    /**
     * Comprueba si la cuenta del organizador está bloqueada
     */
    public boolean estaBloqueado() {
        return this.bloqueado;
    }

}
