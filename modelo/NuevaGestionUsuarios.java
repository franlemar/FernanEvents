package FernanEvents.modelo;

import java.util.HashMap;

public class NuevaGestionUsuarios {

    private HashMap<String, Usuario> usuarios;

    public NuevaGestionUsuarios(){
        this.usuarios = new HashMap<>();
        cargarUsuariosPredefinidos();
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.CRUD.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    //C --> CREATE
    public boolean aniadirUsuario(Usuario nuevoUsuario){
        if(nuevoUsuario == null || usuarios.containsKey(nuevoUsuario.getCorreo())){
            return false;
        }
        usuarios.put(nuevoUsuario.getCorreo(), nuevoUsuario);
        return true;
    }

    /**
     * Crea los usuarios predefinidos para las pruebas
     */
    private void cargarUsuariosPredefinidos(){
        aniadirUsuario(new Administrador("admin", "admin@fernanevents.com", "admin"));
        aniadirUsuario(new Organizador("organizador1", "organizador1@fernanevents.com", "organizador1"));
        aniadirUsuario(new Asistente("asistente1", "asistente1@fernanevents.com", "1234"));
        aniadirUsuario(new Asistente("asistente2", "asistente2@fernanevents.com", "5678"));
    }


}
