package FernanEvents.modelo;

public class EntradasTipo {
    private String nombre;
    private float precio;
    private int cantidadDisponible;

    public EntradasTipo( String nombre, float precio, int cantidadDisponible){
        this.nombre=nombre;
        this.precio=precio;
        this.cantidadDisponible=cantidadDisponible;
    }

    /**
     * Obtiene el nombre del tipo de entrada
     */
    public String getNombre() {return nombre;}

    /**
     * Establece el nombre del tipo de entrada
     */
    public void setNombre(String nombre) {this.nombre = nombre;}

    /**
     * Obtiene el precio del tipo de entrada
     */
    public float getPrecio() {return precio;}

    /**
     * Establece el precio del tipo de entrada
     */
    public void setPrecio(float precio) {this.precio = precio;}

    /**
     * Obtiene la cantidad disponible
     */
    public int getCantidadDisponible() {return cantidadDisponible;}

    /**
     * Establece la cantidad disponible
     */
    public void setCantidadDisponible(int cantidadDisponible) {this.cantidadDisponible = cantidadDisponible;}
}
