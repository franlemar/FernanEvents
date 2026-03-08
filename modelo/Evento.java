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
    private EntradasTipo[] tiposDeEntrada;
    private int contadorTipos;
    private Usuario organizador;

    //Constructor
    public Evento(String nombre, String descripcion, CategoriaEvento categoria, LocalDate fecha, int aforo, int personasInscritas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaEvento = categoria;
        this.fecha = fecha;
        this.aforo = aforo;
        this.personasInscritas = personasInscritas;
        tiposDeEntrada = new EntradasTipo[3];
        contadorTipos = 0;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    public int getAforoRestante(){
        return aforo - personasInscritas;
    }

    public void setConfiguracionEntrada(int indice, EntradasTipo tipo) {
        if (indice >= 0 && indice < 3) {
            this.tiposDeEntrada[indice] = tipo;
        }
    }

    /**
     * Obtiene el nombre del evento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del evento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del evento
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del evento
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la categoría del evento
     */
    public CategoriaEvento getCategoria() {
        return categoriaEvento;
    }

    /**
     * Establece la categoría del evento
     */
    public void setCategoria(CategoriaEvento categoriaEvento) {
        this.categoriaEvento = categoriaEvento;
    }

    /**
     * Obtiene la fecha del evento
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del evento
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el numero de personas inscritas del evento
     */
    public int getPersonasInscritas() {
        return personasInscritas;
    }

    /**
     * Establece el número de personas inscritas del evento
     */
    public void setPersonasInscritas(int personasInscritas) {
        this.personasInscritas = personasInscritas;
    }

    /**
     * Obtiene el aforo máximo del evento
     */
    public int getAforo() {
        return aforo;
    }

    /**
     * Establece el aforo máximo del evento
     */
    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public CategoriaEvento getCategoriaEvento() {
        return categoriaEvento;
    }

    public void setCategoriaEvento(CategoriaEvento categoriaEvento) {
        this.categoriaEvento = categoriaEvento;
    }

    public EntradasTipo[] getTiposDeEntrada() {
        return tiposDeEntrada;
    }

    public void setTiposDeEntrada(EntradasTipo[] tiposDeEntrada) {
        this.tiposDeEntrada = tiposDeEntrada;
    }

    public int getContadorTipos() {
        return contadorTipos;
    }

    public void setContadorTipos(int contadorTipos) {
        this.contadorTipos = contadorTipos;
    }

    public void aniadirTipoEntrada(EntradasTipo nuevoTipo) {
        if (contadorTipos < tiposDeEntrada.length) {
            this.tiposDeEntrada[contadorTipos] = nuevoTipo;
            contadorTipos++;
        }
    }
}
