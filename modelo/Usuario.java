package FernanEvents.modelo;

public abstract class Usuario {

    //Atributos
    private String nombre;
    private String correo;
    private String password;
    private Rol rol;
    private float saldo;
    private boolean bloqueado;


    //Constructor
    public Usuario(String nombre, String correo, String password, Rol rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.saldo = 0f;
        this.bloqueado = false;
    }

    //Métodos
    /**
     * Obtiene el nombre de usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol del usuario
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Obtiene el saldo de la cartera digital del usuario
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo de la cartera digital del usuario
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**
     * Comprueba si el usuario está bloqueado
     */
    public boolean isBloqueado() {
        return bloqueado;
    }

    /**
     * Establece el estado de bloqueo del usuario
     */
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
