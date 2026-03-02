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

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public float getPrecio() {return precio;}

    public void setPrecio(float precio) {this.precio = precio;}

    public int getCantidadDisponible() {return cantidadDisponible;}

    public void setCantidadDisponible(int cantidadDisponible) {this.cantidadDisponible = cantidadDisponible;}
}
