package FernanEvents.modelo;

public class Entrada {
    private CategoriaEntrada categoria;
    private float precio;
    private int cantidadDisponible;
    private int id;
    private int id_evento;

    public Entrada(CategoriaEntrada categoria, float precio, int cantidadDisponible){
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

    /**
     * Obtiene el id de la entrada
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la entrada
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el id del evento
     */
    public int getId_evento() {
        return id_evento;
    }

    /**
     * Establece el id del evento
     */
    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }
}
