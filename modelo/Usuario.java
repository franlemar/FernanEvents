package FernanEvents.modelo;

public abstract class Usuario {

    //Atributos
    private String nombre;
    private String correo;
    private String password;
    private Rol rol;
    private float saldoUsuario;


    //Constructor
    public Usuario(String nombre, String correo, String password, Rol rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.saldoUsuario = 0f;
    }

    //Métodos


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public float getSaldoUsuario() {
        return saldoUsuario;
    }

    public void setSaldoUsuario(float saldoUsuario) {
        this.saldoUsuario = saldoUsuario;
    }


}
