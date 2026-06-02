package FernanEvents.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Evento {

    //Atributos
    private String nombre;
    private String descripcion;
    private CategoriaEvento categoriaEvento;
    private LocalDate fecha;
    private int aforo;
    private int personasInscritas;
    private int id;
    private ArrayList<Entrada> tiposDeEntrada;
    private Usuario organizador;


    //Constructor
    public Evento(String nombre, String descripcion, CategoriaEvento categoria, LocalDate fecha, int aforo, int personasInscritas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaEvento = categoria;
        this.fecha = fecha;
        this.aforo = aforo;
        this.personasInscritas = personasInscritas;
        this.tiposDeEntrada = new ArrayList<>();
    }

    /**
     * Obtiene el usuario organizador que ha creado el evento
     */
    public Usuario getOrganizador() {
        return organizador;
    }

    /**
     * Establece un organizador como creador para un evento
     */
    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    /**
     * Obtiene el aforo restante de un evento
     */
    public int getAforoRestante(){
        return aforo - personasInscritas;
    }

    /**
     * Establece la configuracion de las entradas para un evento en función del tipo de entrada
     */
    public void setConfiguracionEntrada(Entrada tipo) {
        for (int i = 0; i < tiposDeEntrada.size(); i++) {
            if(tiposDeEntrada.get(i).getCategoria().equals(tipo.getCategoria())){
                tiposDeEntrada.set(i, tipo);
                return;
            }
        }
        tiposDeEntrada.add(tipo);
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

    /**
     * Obtiene los tipos de entradas disponibles para un evento
     */
    public ArrayList<Entrada> getTiposDeEntrada() {
        return tiposDeEntrada;
    }

    /**
     * Establece los tipos de entrada disponibles para el evento.
     */
    public void setTiposDeEntrada(ArrayList<Entrada> tiposDeEntrada){
        this.tiposDeEntrada = tiposDeEntrada;
    }

    /**
     * Obtiene el id del evento
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del evento
     */
    public void setId(int id) {
        this.id = id;
    }
}
