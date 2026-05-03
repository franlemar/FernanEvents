package FernanEvents.modelo;

import FernanEvents.modelo.utilidades.Cadenas;

import java.util.Collection;
import java.util.HashMap;

public class GestionUsuario{

    private HashMap<String, Usuario> usuarios;

    public GestionUsuario(){
        this.usuarios = new HashMap<>();
    }

    /**
     * Devuelve los valores(Usuarios) almacenados en el HashMap
     */
    public Collection<Usuario> getUsuarios() {
        return usuarios.values();
    }

    /**
     * Obtiene el número de usuarios almacenados
     */
    public int getNumUsuarios(){
        return usuarios.size();
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.CRUD.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    /**
     * Añade un nuevo usuario al HashMap
     */
    public boolean aniadirUsuario(Usuario nuevoUsuario){
        if(nuevoUsuario == null || usuarios.containsKey(nuevoUsuario.getCorreo())){
            return false;
        }
        usuarios.put(nuevoUsuario.getCorreo(), nuevoUsuario);
        return true;
    }

    /**
     * Actualiza el nombre de un usuario
     */
    public boolean actualizarNombre(String correo, String nuevoNombre) {
        Usuario usuario = usuarios.get(correo);
        if (usuario == null) return false;

        for (Usuario u : usuarios.values()) {
            if (u.getNombre().equalsIgnoreCase(nuevoNombre) && !u.getCorreo().equals(correo)) {
                return false;
            }
        }

        usuario.setNombre(nuevoNombre);
        return true;
    }

    /**
     * Actualiza la contraseña de un usuario
     */
    public boolean actualizarContrasena(String correo, String nuevaContrasena){
        Usuario usuario = usuarios.get(correo);
        if (usuario == null) { return false; }

        usuario.setPassword(nuevaContrasena);
        return true;
    }

    /**
     * Actualiza si está bloqueado o no un usuario
     */
    public boolean actualizaEstadoBloqueo(String correoUsuario, boolean estado){
        Usuario usuario = buscaUsuarioPorCorreo(correoUsuario);
        if(usuario != null){
            usuario.setBloqueado(estado);
            return true;
        }
        return false;
    }

    /**
     * Añade saldo a la cartera de un usuario
     */
    public boolean aniadirSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null || cantidad <= 0) { return false; }
        usuario.setSaldo(usuario.getSaldo() + cantidad);
        return true;
    }

    /**
     * Retira saldo a la cartera de un usuario
     */
    public boolean quitarSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null || cantidad <= 0) { return false; }

        if (usuario.getSaldo() >= cantidad) {
            usuario.setSaldo(usuario.getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    /**
     * Añade amigos referidos a la lista de amigos referidos de los asistentes
     */
    public boolean aniadirAmigoReferido(Usuario usuario, String correoAmigo) {
        if (usuario instanceof Asistente asistente && correoAmigo.contains("@")) {
            asistente.getAmigosReferidos().add(correoAmigo);
            return true;
        }
        return false;
    }

    /**
     * Elimina un usuario por su correo
     */
    public boolean eliminaUsuario(String correo) {
        return usuarios.remove(correo) != null;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS HELPER.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    /**
     * Crea los usuarios predefinidos para las pruebas
     */
    public void cargarUsuariosPredefinidos(){
        aniadirUsuario(new Administrador("admin", "admin@fernanevents.com", Cadenas.hashearPassword("admin")));
        aniadirUsuario(new Organizador("organizador1", "organizador1@fernanevents.com", Cadenas.hashearPassword("organizador1")));
        aniadirUsuario(new Asistente("asistente1", "asistente1@fernanevents.com", Cadenas.hashearPassword("1234")));
        aniadirUsuario(new Asistente("asistente2", "asistente2@fernanevents.com", Cadenas.hashearPassword("5678")));
    }

    /**
     * Busca a un usuario por su correo
     */
    public Usuario buscaUsuarioPorCorreo(String correo){
        return usuarios.get(correo);
    }

    /**
     * Busca a un usuario por su nombre
     */
    public Usuario buscarPorNombre(String nombre){
        for(Usuario u : usuarios.values()){
            if(u.getNombre().equalsIgnoreCase(nombre)){
                return u;
            }
        }
        return null;
    }

    /**
     * Verifica si hay usuarios bloqueados
     */
    public boolean confirmaUsuariosBloqueados(){
        for(Usuario u : usuarios.values()){
            if(u.isBloqueado()){
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si un asistente tiene amigos referidos
     */
    public boolean tieneAmigosReferidos(Usuario usuario){
        if(usuario instanceof Asistente asistente){
            return !asistente.getAmigosReferidos().isEmpty();
        }
        return false;
    }

    /**
     * Elimina un evento del historial de inscripciones de todos los asistentes.
     */
    public void limpiarEventoDeAsistentes(String nombreEvento){
        for(Usuario u : usuarios.values()){
            if(u instanceof Asistente asistente){
                asistente.getEventosInscrito().remove(nombreEvento);
            }
        }
    }

}