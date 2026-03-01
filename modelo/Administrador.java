package FernanEvents.modelo;

public class Administrador extends Usuario{

    public Administrador(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ADMINISTRADOR);
    }


}
