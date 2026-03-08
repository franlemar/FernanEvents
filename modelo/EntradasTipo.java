package FernanEvents.modelo;

public class EntradasTipo {
    private CategoriaEntrada categoria;
    private float precio;
    private int cantidadDisponible;

    public EntradasTipo(CategoriaEntrada categoria, float precio, int cantidadDisponible){
        this.categoria = categoria;
        this.precio=precio;
        this.cantidadDisponible=cantidadDisponible;
    }

    /**
     * Obtiene el nombre del tipo de entrada
     */
    public CategoriaEntrada getCategoria() {
        return categoria;
    }

    /**
     * Obtiene el precio del tipo de entrada
     */
    public float getPrecio() {return precio;}

    /**
     * Obtiene la cantidad disponible
     */
    public int getCantidadDisponible() {return cantidadDisponible;}

    /**
     * Establece la cantidad disponible
     */
    public void setCantidadDisponible(int cantidadDisponible) {this.cantidadDisponible = cantidadDisponible;}
}
