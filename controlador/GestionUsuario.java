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

    protected Usuario[] getUsuarios() {
        return usuarios;
    }

    protected void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    protected int getNumUsuarios() {
        return numUsuarios;
    }

    protected void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS INTERFAZ AUMENTABLE.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
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

    public void disminuirCapacidad(){
        disminuirCapacidad(1);
    }

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
    protected boolean aniadirUsuario(Usuario nuevoUsuario){
        if(numUsuarios == usuarios.length){
            aumentarCapacidad();
        }
        usuarios[numUsuarios++] = nuevoUsuario;
        return true;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS HELPER.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
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

    protected boolean confirmaUsuariosBloqueados(){
        for (int i = 0; i < numUsuarios; i++) {
            if(usuarios[i] != null && usuarios[i].isBloqueado()){
                return true;
            }
        }
        return false;
    }

    protected Usuario buscaUsuarioPorCorreo(String correo){
        for (int i = 0; i < numUsuarios; i++) {
            if(usuarios[i].getCorreo().equalsIgnoreCase(correo)){
                return usuarios[i];
            }
        }
        return null;
    }

    protected int buscaPosicionPorCorreo(String correo){
        for (int i = 0; i < numUsuarios; i++) {
            if(usuarios[i].getCorreo().equalsIgnoreCase(correo)){
                return i;
            }
        }
        return -1;
    }

    // buscar usuario por nombre
    protected Usuario buscarPorNombre(String nombre){
        for (int i = 0; i <numUsuarios ; i++) {
            if (usuarios[i] != null && usuarios[i].getNombre().equalsIgnoreCase(nombre)){
                return usuarios[i];
            }
        }
        return null;
    }

    protected boolean tieneAmigosReferidos(Usuario usuario){
        if(usuario instanceof Asistente asistente){
            return asistente.getNumAmigosReferidos() > 0;
        }
        return false;
    }

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
    //actualizar nombre de usuario
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

    // actualizar contraseña de usuario
    protected boolean actualizarContrasena(String correo, String nuevaContrasena){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null) { return false; }

        usuario.setPassword(nuevaContrasena);
        return true;
    }

    protected boolean actualizaEstadoBloqueo(String correoUsuario, boolean estado){
        Usuario usuario = buscaUsuarioPorCorreo(correoUsuario);
        if(usuario != null){
            usuario.setBloqueado(estado);
            return true;
        }
        return false;
    }

    protected boolean aniadirSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);

        if (usuario == null || cantidad <= 0) { return false; }
        usuario.setSaldo(usuario.getSaldo() + cantidad);
        return true;
    }

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

    private void eliminaEspacios(int posicion){
        usuarios[posicion] = usuarios[numUsuarios - 1];
        usuarios[numUsuarios - 1] = null;
        numUsuarios--;
    }


}
