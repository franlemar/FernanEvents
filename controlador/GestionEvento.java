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
    //U --> UPDATE
    //D --> DELETE







}
