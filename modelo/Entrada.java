package FernanEvents.modelo;

import FernanEvents.modelo.utilidades.interfaces.Aumentable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Entrada {
    private String nombreEvento;
    private String tipoEntrada;
    private int cantidad;
    private float precioPagado;
    private LocalDateTime fechaDeCompra;

    public Entrada(String nombreEvento, String tipoEntrada, int cantidad, float precioPagado){
        this.nombreEvento=nombreEvento;
        this.tipoEntrada=tipoEntrada;
        this.cantidad=cantidad;
        this.precioPagado=precioPagado;
        this.fechaDeCompra=LocalDateTime.now();
    }

    /**
     * Obtiene el nombre del evento de la entrada
     */
    public String getNombreEvento() {return nombreEvento;}

    /**
     * Establece el nombre del evento de la entrada
     */
    public void setNombreEvento(String nombreEvento) {this.nombreEvento = nombreEvento;}

    /**
     * Obtiene el tipo de entrada
     */
    public String getTipoEntrada() {return tipoEntrada;}

    /**
     * Establece el tipo de entrada
     */
    public void setTipoEntrada(String tipoEntrada) {this.tipoEntrada = tipoEntrada;}

    /**
     * Obtiene la cantidad de entradas compradas
     */
    public int getCantidad() {return cantidad;}

    /**
     * Establece la cantidad de entradas compradas
     */
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    /**
     * Obtiene el precio pagado por las entradas
     */
    public float getPrecioPagado() {return precioPagado;}

    /**
     * Establece el precio pagado por las entradas
     */
    public void setPrecioPagado(float precioPagado) {this.precioPagado = precioPagado;}

    /**
     * Obtiene la fecha y hora de compra de las entradas
     */
    public LocalDateTime getFechaDeCompra() {return fechaDeCompra;}

    /**
     * Establece la fecha y hora de compra de las entradas
     */
    public void setFechaDeCompra(LocalDateTime fechaDeCompra) {this.fechaDeCompra = fechaDeCompra;}
}
