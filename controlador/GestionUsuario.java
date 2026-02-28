package FernanEvents.controlador;

import FernanEvents.modelo.Rol;
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

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.CRUD.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    //C --> CREATE
    public boolean registrarUsuario(Usuario nuevoUsuario){
        if(numUsuarios == usuarios.length){
            aumentarCapacidad();
        }
        usuarios[numUsuarios++] = nuevoUsuario;
        return true;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS HELPER.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
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

    // buscar usuario por rol
    protected Usuario[] buscarPorRol(Rol rol){
        int contador = 0;
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getRol() == rol){
                contador++;
            }
        }
        Usuario[] resultado = new Usuario[contador];
        int posicion = 0;
        for (int i = 0; i < numUsuarios ; i++) {
            if (usuarios[i] != null && usuarios[i].getRol() == rol) {
                resultado[posicion++] = usuarios[i];
            }
        }
        return resultado;
    }

    //CRUD --> UPDATE
    //actualizar nombre de usuario
    public boolean actualizarNombre(String correo, String nuevoNombre){
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
    public boolean actualizarContrasena(String correo, String nuevaContrasena){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null) { return false; }

        usuario.setPassword(nuevaContrasena);
        return true;
    }

    public boolean cambiarRol (String correo, Rol nuevoRol){
        Usuario usuario = buscaUsuarioPorCorreo(correo);
        if (usuario == null) { return false; }

        usuario.setRol(nuevoRol);
        return true;
    }

    public boolean aniadirSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);

        if (usuario == null || cantidad <= 0) { return false; }
        usuario.setSaldoUsuario(usuario.getSaldoUsuario() + cantidad);
        return true;
    }

    public boolean quitarSaldo (String correo, float cantidad){
        Usuario usuario = buscaUsuarioPorCorreo(correo);

        if (usuario == null || cantidad <= 0) { return false; }

        if (usuario.getSaldoUsuario() >= cantidad) {
            usuario.setSaldoUsuario(usuario.getSaldoUsuario() - cantidad);
            return true;
        }
        return false;
    }

    //CRUD --> DELETE
    public boolean eliminaUsuario(String correo){
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
