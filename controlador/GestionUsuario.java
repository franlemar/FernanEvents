package FernanEvents.controlador;

import FernanEvents.modelo.Usuario;
import FernanEvents.modelo.utilidades.interfaces.Aumentable;

public class GestionUsuario implements Aumentable {

    private Usuario[] usuarios;
    private int numUsuarios;

    public GestionUsuario(int tamanio) {
        usuarios = new Usuario[tamanio];
        numUsuarios = 0;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }

    public void aumentarCapacidad(){
        aumentarCapacidad(1);
    }

    public void aumentarCapacidad(int cantidad){
        Usuario[] nuevoArray = new Usuario[usuarios.length + cantidad];
        for (int i = 0; i < numUsuarios; i++) {
            nuevoArray[i] = usuarios[i];
        }
        this.usuarios = nuevoArray;
    }

    public void disminuyeCapacidad(){
        disminuyeCapacidad(1);
    }

    public void disminuyeCapacidad(int cantidad){
        int nuevoTamanio = usuarios.length - cantidad;
        if(nuevoTamanio < numUsuarios){
            System.out.println("ERROR, no se puede reducir ese espacio");
            return;
        }
        Usuario[] nuevoArray = new Usuario[nuevoTamanio];

        for (int i = 0; i < numUsuarios; i++) {
            nuevoArray[i] = usuarios[i];
        }
        this.usuarios = nuevoArray;


    }

    public Usuario buscaCorreo(String correo){
        for (int i = 0; i < numUsuarios; i++) {
            if(usuarios[i].getCorreo().equalsIgnoreCase(correo)){
                return usuarios[i];
            }
        }
        return null;
    }
}
