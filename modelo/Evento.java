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
    private int id;
    private static int contadorId = 0; //aquí he puesto static para que todos los objetos compartan el mismo contador
    private String correoOrganizador;

    private EntradasTipo[] tiposEntrada;
    private int numTipos;

    //Constructor
    public Evento(String nombre, String descripcion, CategoriaEvento categoriaEvento, LocalDate fecha, int aforo, int personasInscritas, String correoOrganizador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaEvento = categoriaEvento;
        this.fecha = fecha;
        this.aforo = aforo;
        this.personasInscritas = personasInscritas;
        this.id = contadorId;
        contadorId++;
        this.correoOrganizador = correoOrganizador;
        this.tiposEntrada = new EntradasTipo[3];
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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public EntradasTipo[] getTiposEntrada() {return tiposEntrada;}

    public void setTiposEntrada(EntradasTipo[] tiposEntrada) {this.tiposEntrada = tiposEntrada;}

    public int getNumTipos() {return numTipos;}

    public void setNumTipos(int numTipos) {this.numTipos = numTipos;}

    public String getCorreoOrganizador() {return correoOrganizador;}

    public void setCorreoOrganizador(String correoOrganizador) {this.correoOrganizador = correoOrganizador;}

    //añadir un tipo, solo se usa al crear el evento
    public void aniadirTipoEntrada(String nombre, float precio, int cantidad){
        if (numTipos < 3){
            tiposEntrada[numTipos++] = new EntradasTipo(nombre, precio, cantidad);
        }
    }

    public EntradasTipo getTipoEntrada(String nombreTipo) {
        for (int i = 0; i < numTipos; i++) {
            if (tiposEntrada[i].getNombre().equalsIgnoreCase(nombreTipo)) {
                return tiposEntrada[i];
            }
        }
        return null;
    }

    public boolean venderEntradas(String nombreTipo, int cantidad){
        EntradasTipo tipo = getTipoEntrada(nombreTipo);
        if (tipo != null && tipo.getCantidadDisponible() >= cantidad){
            tipo.setCantidadDisponible(tipo.getCantidadDisponible() - cantidad);

            this.personasInscritas = personasInscritas + cantidad;
            return true;
        }
        return false;
    }

}
