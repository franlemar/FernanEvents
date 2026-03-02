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

    public String getNombreEvento() {return nombreEvento;}

    public void setNombreEvento(String nombreEvento) {this.nombreEvento = nombreEvento;}

    public String getTipoEntrada() {return tipoEntrada;}

    public void setTipoEntrada(String tipoEntrada) {this.tipoEntrada = tipoEntrada;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public float getPrecioPagado() {return precioPagado;}

    public void setPrecioPagado(float precioPagado) {this.precioPagado = precioPagado;}

    public LocalDateTime getFechaDeCompra() {return fechaDeCompra;}

    public void setFechaDeCompra(LocalDateTime fechaDeCompra) {this.fechaDeCompra = fechaDeCompra;}
}
