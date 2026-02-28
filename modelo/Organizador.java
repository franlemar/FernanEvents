package FernanEvents.modelo;

import FernanEvents.modelo.utilidades.interfaces.Bloqueable;

public class Organizador extends Usuario implements Bloqueable {
    private boolean bloqueado = false;

    public Organizador(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ORGANIZADOR);
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
