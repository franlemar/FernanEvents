package FernanEvents.modelo;

import java.time.LocalDate;

public class Evento {

    //Atributos
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private LocalDate fecha;
    private int aforo;
    private int personasInscritas;


    //Constructor
    public Evento(String nombre, String descripcion, Categoria categoria, LocalDate fecha, int aforo, int personasInscritas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fecha = fecha;
        this.aforo = aforo;
        this.personasInscritas = personasInscritas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getPersonasInscritas() {
        return personasInscritas;
    }

    public void setPersonasInscritas(int personasInscritas) {
        this.personasInscritas = personasInscritas;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }


}
