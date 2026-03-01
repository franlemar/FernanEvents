package FernanEvents.controlador;

import FernanEvents.modelo.CategoriaEvento;
import FernanEvents.modelo.Evento;
import FernanEvents.modelo.Usuario;
import FernanEvents.modelo.utilidades.FuncionesFechas;

import java.time.LocalDate;

public class GestionEvento {

    private Evento[] eventos;
    private int numEventos;

    public GestionEvento(int tamanio){
        eventos = new Evento[tamanio];
        numEventos = 0;
    }

    public Evento[] getEventos() {
        return eventos;
    }

    public void setEventos(Evento[] eventos) {
        this.eventos = eventos;
    }

    public int getNumEventos() {
        return numEventos;
    }

    public void setNumEventos(int numEventos) {
        this.numEventos = numEventos;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS INTERFAZ AUMENTABLE.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    public void aumentarCapacidad(){
        aumentarCapacidad(1);
    }

    public void aumentarCapacidad(int cantidad){
        Evento[] nuevoArray = new Evento[eventos.length + cantidad];
        for (int i = 0; i < numEventos; i++) {
            nuevoArray[i] = eventos[i];
        }
        this.eventos = nuevoArray;
    }

    public void disminuirCapacidad(){
        disminuirCapacidad(1);
    }

    public void disminuirCapacidad(int cantidad){
        int nuevoTamanio = eventos.length - cantidad;
        if(nuevoTamanio < numEventos){
            System.out.println("ERROR, no se puede reducir ese espacio");
            return;
        }
        Evento[] nuevoArray = new Evento[nuevoTamanio];

        for (int i = 0; i < numEventos; i++) {
            nuevoArray[i] = eventos[i];
        }
        this.eventos = nuevoArray;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.CRUD.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    //C --> CREATE
    public boolean aniadirEvento (Evento nuevoEvento){
        if (nuevoEvento == null) return false;

        if (buscarEventoPorNombre(nuevoEvento.getNombre()) != null){
            return false;
        }

        if (numEventos == eventos.length){
            aumentarCapacidad(5);
        }

        eventos[numEventos++] = nuevoEvento;
        return true;
    }

    public Evento crearEvento (String nombre, String descripcion, CategoriaEvento categoria, String fecha, int aforo, int personasInscritas){
        LocalDate fechaEvento = FuncionesFechas.convertirStringEnFecha(fecha);
        if (fechaEvento == null) return null;

        Evento nuevoEvento = new Evento(nombre, descripcion, categoria, fechaEvento, aforo, personasInscritas);

        if (aniadirEvento(nuevoEvento)) {
            return nuevoEvento;
        }
        return null;

    }

    //----------------------------------------------------------------------------------------------------
    //R --> READ
    public Evento buscarEventoPorNombre (String nombre){
        for (int i = 0; i < numEventos; i++) {
            if (eventos[i] != null && eventos[i].getNombre().equalsIgnoreCase(nombre)){
                return eventos[i];
            }
        }
        return null;
    }

    public int buscarPosicionPorNombre(String nombre) {
        for (int i = 0; i < numEventos; i++) {
            if (eventos[i] != null && eventos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return i;
            }
        }
        return -1;
    }

    //----------------------------------------------------------------------------------------------------
    //U --> UPDATE
    public boolean actualizarNombre(String nombreActual, String nuevoNombre){
        Evento evento = buscarEventoPorNombre(nombreActual);
        if (evento == null) return false;

        if (buscarEventoPorNombre(nuevoNombre) != null && !nombreActual.equalsIgnoreCase(nuevoNombre)){
            return false;
        }

        evento.setNombre(nuevoNombre);
        return true;
    }

    public boolean actualizarDescripcion(String nombreEvento, String nuevaDescripcion){
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        evento.setDescripcion(nuevaDescripcion);
        return true;
    }

    public boolean actualizarCategoria(String nombreEvento, CategoriaEvento nuevaCategoria){
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

//        evento.setDescripcion(nuevaCategoria);
        return true;
    }

    public boolean actualizarFecha (String nombreEvento, String nuevaFecha){
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        LocalDate fecha = FuncionesFechas.convertirStringEnFecha(nuevaFecha);
        if (fecha == null) return false;

        evento.setFecha(fecha);
        return true;
    }

    public boolean actualizarAforo (String nombreEvento, int nuevoAforo){
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento==null) return false;

        if (nuevoAforo < evento.getPersonasInscritas()){
            return false;
        }

        evento.setAforo(nuevoAforo);
        return true;
    }

    public boolean actualizarInscritos (String nombreEvento, int nuevosInscritos){
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento== null) return false;

        if (nuevosInscritos > evento.getAforo()){
            return false;
        }

        evento.setPersonasInscritas(nuevosInscritos);
        return true;
    }

    // Ver con Fran; porque no sé si se podría combinar con la función de actualizar inscritos para que quede más compacta
    public boolean incrementarInscritos(String nombreEvento, int cantidad) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        int nuevosInscritos = evento.getPersonasInscritas() + cantidad;
        if (nuevosInscritos > evento.getAforo()) {
            return false;
        }

        evento.setPersonasInscritas(nuevosInscritos);
        return true;
    }

    //D --> DELETE
    public boolean eliminarEvento (String nombre){
        int posicion = buscarPosicionPorNombre(nombre);
        if (posicion == -1) return false;

        eventos[posicion] = eventos[numEventos-1];
        eventos[numEventos-1] = null;
        numEventos--;

        return true;
    }

    public boolean eliminarEventoPorPosicion(int posicion){
        if (posicion < 0 || posicion >= numEventos) return false;

        eventos[posicion] = eventos[numEventos-1];
        eventos[numEventos-1]= null;
        numEventos--;

        return true;
    }







}
