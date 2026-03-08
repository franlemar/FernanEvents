package FernanEvents.controlador;

import FernanEvents.modelo.*;
import FernanEvents.modelo.utilidades.interfaces.Aumentable;

public class GestionUsuario implements Aumentable {

    private Usuario[] usuarios;
    private int numUsuarios;

    public GestionUsuario(int tamanio) {
        usuarios = new Usuario[tamanio];
        numUsuarios = 0;
        cargarUsuariosPredefinidos();
    }

    /**
     * Obtiene el array de usuarios
     */
    protected Usuario[] getUsuarios() {
        return usuarios;
    }

    /**
     * Obtiene el número de usuarios almacenados
     */
    protected int getNumUsuarios() {
        return numUsuarios;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS INTERFAZ AUMENTABLE.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Aumenta la capacidad del array de usuarios en 1
     */
    public void aumentarCapacidad(){
        aumentarCapacidad(1);
    }

    /**
     * Aumenta la capacidad del array de usuarios en la cantidad que se le pase
     */
    public void aumentarCapacidad(int cantidad){
        Usuario[] nuevoArray = new Usuario[usuarios.length + cantidad];
        for (int i = 0; i < numUsuarios; i++) {
            nuevoArray[i] = usuarios[i];
        }
        this.usuarios = nuevoArray;
    }

    /**
     * disminuye la capacidad del array de usuarios en 1
     */
    public void disminuirCapacidad(){
        disminuirCapacidad(1);
    }

    /**
     * Disminuye la capacidad del array de usuarios en la cantidad que se le pase
     */
    public void disminuirCapacidad(int cantidad){
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

    /**
     * Aumenta la capacidad del array de amigos referidos de un asistente
     */
    public void aumentarCapacidadAR(Usuario usuario){
        Asistente asistente = (Asistente) usuario;
        String[] arrayActual = asistente.getAmigosReferidos();
        String[] nuevoArray = new String[arrayActual.length + 1];
        for (int i = 0; i < asistente.getNumAmigosReferidos(); i++) {
            nuevoArray[i] = arrayActual[i];
        }
        asistente.setAmigosReferidos(nuevoArray);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.CRUD.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    //C --> CREATE
    /**
     * Añade un nuevo usuario al array
     */
    protected boolean aniadirUsuario(Usuario nuevoUsuario){
        if(numUsuarios == usuarios.length){
            aumentarCapacidad();
        }
        usuarios[numUsuarios++] = nuevoUsuario;
        return true;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS HELPER.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Crea los usuarios predefinidos para las pruebas
     */
    private void cargarUsuariosPredefinidos(){
        Usuario admin = new Administrador("admin", "admin@fernanevents.com", "admin");
        Usuario organizador1 = new Organizador("organizador1", "organizador1@fernanevents.com", "organizador1");
        Usuario asistente1 = new Asistente("asistente1", "asistente1@fernanevents.com", "1234");
        Usuario asistente2 = new Asistente("asistente2", "asistente2@fernanevents.com", "5678");

        aniadirUsuario(admin);
        aniadirUsuario(organizador1);
        aniadirUsuario(asistente1);
        aniadirUsuario(asistente2);
    }

    /**
     * Verifica si hay usuarios bloqueados
     */
    protected boolean confirmaUsuariosBloqueados(){
        for (int i = 0; i < numUsuarios; i++) {
            if(usuarios[i] != null && usuarios[i].isBloqueado()){
                return true;
            }
        }
        return false;
    }

    /**
     * Busca a un usuario por su correo
     */
    protected Usuario buscaUsuarioPorCorreo(String correo){
        for (int i = 0; i < numUsuarios; i++) {
            if(usuarios[i].getCorreo().equalsIgnoreCase(correo)){
                return usuarios[i];
            }
        }
        return null;
    }

    /**
     * Busca la posición de un usuario en el array por su correo
     */
    protected int buscaPosicionPorCorreo(String correo){
        for (int i = 0; i < numUsuarios; i++) {
            if(usuarios[i].getCorreo().equalsIgnoreCase(correo)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca a un usuario por su nombre
     */
    protected Usuario buscarPorNombre(String nombre){
        for (int i = 0; i <numUsuarios ; i++) {
            if (usuarios[i] != null && usuarios[i].getNombre().equalsIgnoreCase(nombre)){
                return usuarios[i];
            }
        }
        return null;
    }

    /**
     * Comprueba si tiene amigos referidos
     */
    protected boolean tieneAmigosReferidos(Usuario usuario){
        if(usuario instanceof Asistente asistente){
            return asistente.getNumAmigosReferidos() > 0;
        }
        return false;
    }

    /**
     * Añade amigos referidos
     */
    protected boolean aniadirAmigoReferido(Usuario usuario, String correoAmigo){
        Asistente asistente = (Asistente) usuario;
        if(!correoAmigo.contains("@")){
            return false;
        }
        int posicionFinal = asistente.getNumAmigosReferidos();
        if(posicionFinal == asistente.getAmigosReferidos().length){
            aumentarCapacidadAR(usuario);
        }

        asistente.getAmigosReferidos()[posicionFinal] = correoAmigo;
        asistente.setNumAmigosReferidos(posicionFinal + 1);
        return true;
    }

    //CRUD --> UPDATE
    /**
     * Actualiza el nombre de un usuario
     */
    protected boolean actualizarNombre(String correo, String nuevoNombre){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null) { return false; }

        Usuario existente = buscarPorNombre(nuevoNombre);
        if (existente != null && !existente.getCorreo().equals(correo)){
            return false;
        }
        usuario.setNombre(nuevoNombre);
        return true;
    }

    /**
     * Actualiza la contraseña de un usuario
     */
    protected boolean actualizarContrasena(String correo, String nuevaContrasena){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null) { return false; }

        usuario.setPassword(nuevaContrasena);
        return true;
    }

    /**
     * Actualiza si está bloqueado o no un usuario
     */
    protected boolean actualizaEstadoBloqueo(String correoUsuario, boolean estado){
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
    protected boolean aniadirSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);

        if (usuario == null || cantidad <= 0) { return false; }
        usuario.setSaldo(usuario.getSaldo() + cantidad);
        return true;
    }

    /**
     * Retira saldo a la cartera de un usuario
     */
    protected boolean quitarSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);

        if (usuario == null || cantidad <= 0) { return false; }

        if (usuario.getSaldo() >= cantidad) {
            usuario.setSaldo(usuario.getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    //CRUD --> DELETE
    /**
     * Elimina un usuario por su correo
     */
    protected boolean eliminaUsuario(String correo){
        int posicion = buscaPosicionPorCorreo(correo);
        if(posicion < 0){
            return false;
        }else{
            usuarios[posicion] = null;
            eliminaEspacios(posicion);
            return true;
        }
    }

    /**
     * Reorganiza el array después de eliminar un usuario
     */
    private void eliminaEspacios(int posicion){
        usuarios[posicion] = usuarios[numUsuarios - 1];
        usuarios[numUsuarios - 1] = null;
        numUsuarios--;
    }

}
