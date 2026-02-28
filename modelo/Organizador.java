package FernanEvents.modelo;

public class Organizador extends Usuario{

    public Organizador(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ORGANIZADOR);
    }

}
