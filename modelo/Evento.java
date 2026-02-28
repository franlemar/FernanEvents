package FernanEvents.modelo;

import java.time.LocalDate;

public class Evento {

    //Atributos
    private String nombre;
    private String descripcion;
    private CategoriaEvento categoriaEvento;
    private LocalDate fecha;
    private int aforo;
    private int personasInscritas;


    //Constructor
    public Evento(String nombre, String descripcion, CategoriaEvento categoriaEvento, LocalDate fecha, int aforo, int personasInscritas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaEvento = categoriaEvento;
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

    public CategoriaEvento getCategoria() {
        return categoriaEvento;
    }

    public void setCategoria(CategoriaEvento categoriaEvento) {
        this.categoriaEvento = categoriaEvento;
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
