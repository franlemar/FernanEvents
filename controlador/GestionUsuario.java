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

    //CRUD --> READ
    // buscar usuario por nombre
    public Usuario buscarPorNombre(String nombre){
        for (int i = 0; i <numUsuarios ; i++) {
            if (usuarios[i] != null && usuarios[i].getNombre().equalsIgnoreCase(nombre)){
                return usuarios[i];
            }
        }
        return null;
    }

    // buscar usuario por rol
    public Usuario[] buscarPorRol(Rol rol){
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
        Usuario usuario = buscaCorreo(correo);
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
        Usuario usuario = buscaCorreo(correo);
        if (usuario == null) { return false; }

        usuario.setPassword(nuevaContrasena);
        return true;
    }

    public boolean cambiarRol (String correo, Rol nuevoRol){
        Usuario usuario = buscaCorreo(correo);
        if (usuario == null) { return false; }

        usuario.setRol(nuevoRol);
        return true;
    }

    public boolean aniadirSaldo (String correo, float cantidad){
        Usuario usuario = buscaCorreo(correo);

        if (usuario == null || cantidad <= 0) { return false; }
        usuario.setSaldoUsuario(usuario.getSaldoUsuario() + cantidad);
        return true;
    }

    public boolean quitarSaldo (String correo, float cantidad){
        Usuario usuario = buscaCorreo(correo);

        if (usuario == null || cantidad <= 0) { return false; }

        if (usuario.getSaldoUsuario() >= cantidad) {
            usuario.setSaldoUsuario(usuario.getSaldoUsuario() - cantidad);
            return true;
        }
        return false;
    }

    //CRUD --> DELETE






}
