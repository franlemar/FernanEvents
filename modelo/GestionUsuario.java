package FernanEvents.modelo;

import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.DAOAmigos_ReferidosSQL;
import FernanEvents.modelo.dao.modeloDAO.DAOUsuarioSQL;
import FernanEvents.modelo.utilidades.Cadenas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GestionUsuario{

    private HashMap<String, Usuario> usuarios;
    private DAOUsuarioSQL usuarioDAO;
    private DAOAmigos_ReferidosSQL amigosReferidosDAO;

    public GestionUsuario(){
        this.usuarios = new HashMap<>();
        this.usuarioDAO = new DAOUsuarioSQL();
        this.amigosReferidosDAO = new DAOAmigos_ReferidosSQL();
    }

    private DAOManager getDaoManager(){
        return DAOManager.getSingletonInstance();
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

        if(usuarioDAO.insert(nuevoUsuario, getDaoManager())){
            usuarios.put(nuevoUsuario.getCorreo(), nuevoUsuario);
            return true;
        }

        return false;
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

        String nombreAnterior = usuario.getNombre();
        usuario.setNombre(nuevoNombre);

        if (usuarioDAO.update(usuario, getDaoManager())) {
            return true;
        } else {
            usuario.setNombre(nombreAnterior);
            return false;
        }
    }

    /**
     * Actualiza la contraseña de un usuario
     */
    public boolean actualizarContrasena(String correo, String nuevaContrasena){
        Usuario usuario = usuarios.get(correo);
        if (usuario == null) { return false; }

        String passAnterior = usuario.getPassword();
        usuario.setPassword(nuevaContrasena);

        if (usuarioDAO.update(usuario, getDaoManager())) {
            return true;
        } else {
            usuario.setPassword(passAnterior);
            return false;
        }
    }

    /**
     * Actualiza si está bloqueado o no un usuario
     */
    public boolean actualizaEstadoBloqueo(String correoUsuario, boolean estado){
        Usuario usuario = buscaUsuarioPorCorreo(correoUsuario);
        if(usuario != null){
            boolean estadoOriginal = usuario.isBloqueado();
            usuario.setBloqueado(estado);

            if(usuarioDAO.update(usuario, getDaoManager())){
                return true;
            } else {
                usuario.setBloqueado(estadoOriginal);
                return false;
            }
        }
        return false;
    }

    /**
     * Añade saldo a la cartera de un usuario
     */
    public boolean aniadirSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null || cantidad <= 0) { return false; }

        float saldoAnterior = usuario.getSaldo();
        usuario.setSaldo(saldoAnterior + cantidad);

        if (usuarioDAO.update(usuario, getDaoManager())) {
            return true;
        } else {
            usuario.setSaldo(saldoAnterior);
            return false;
        }
    }

    /**
     * Retira saldo a la cartera de un usuario
     */
    public boolean quitarSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null || cantidad <= 0) { return false; }

        if (usuario.getSaldo() >= cantidad) {
            float saldoAnterior = usuario.getSaldo();
            usuario.setSaldo(saldoAnterior - cantidad);

            if (usuarioDAO.update(usuario, getDaoManager())) {
                return true;
            } else {
                usuario.setSaldo(saldoAnterior);
                return false;
            }
        }
        return false;
    }

    /**
     * Añade amigos referidos a la lista de amigos referidos de los asistentes
     */
    public boolean aniadirAmigoReferido(Usuario usuario, String correoAmigo) {
        if (usuario instanceof Asistente asistente && correoAmigo.contains("@")) {
            if (asistente.getAmigosReferidos().contains(correoAmigo)) {
                return false;
            }

            if (amigosReferidosDAO.insert(asistente.getCorreo(), correoAmigo, getDaoManager())) {
                asistente.getAmigosReferidos().add(correoAmigo);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un usuario por su correo
     */
    public boolean eliminaUsuario(String correo) {
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null) { return false; }

        if (usuarioDAO.delete(usuario, getDaoManager())) {
            usuarios.remove(correo);
            return true;
        }

        return false;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS HELPER.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    /**
     * Carga los usuarios directamente desde la base de datos de FernanEvents.
     * Si la BD está vacía, inserta los usuarios predefinidos.
     */
    public void cargarUsuariosDesdeBDD() {
        DAOManager daoManager = getDaoManager();
        ArrayList<Usuario> usuariosBDD = usuarioDAO.readAll(daoManager);

        if (usuariosBDD != null) {
            usuarios.clear();

            for (Usuario usuario : usuariosBDD) {
                usuarios.put(usuario.getCorreo(), usuario);

                if (usuario instanceof Asistente asistente) {
                    ArrayList<String> amigosReferidos = amigosReferidosDAO.readAllAmigos(asistente.getCorreo(), daoManager);
                    if (amigosReferidos != null) {
                        asistente.setAmigosReferidos(amigosReferidos);
                    }
                }
            }
        }
    }

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