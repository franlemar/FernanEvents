package FernanEvents.controlador;

import FernanEvents.modelo.CategoriaEvento;
import FernanEvents.modelo.Evento;
import FernanEvents.modelo.utilidades.FuncionesFechas;
import FernanEvents.vista.VistaFernan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GestionEvento {

    private Evento[] eventos;
    private int numEventos;
    private VistaFernan vista;

    public GestionEvento(int tamanio, VistaFernan vista){
        eventos = new Evento[tamanio];
        numEventos = 0;
        this.vista = vista;
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
    public Evento crearEvento(){
        Scanner s = new Scanner(System.in);

        vista.pedirDatosEvento("Introduce el nombre del evento: ");
        String nombreEvento = s.nextLine();

        vista.pedirDatosEvento("Introduce la descripción del evento: ");
        String descripcionEvento = s.nextLine();

        CategoriaEvento categoriaEve = null;
        while (categoriaEve == null){
            vista.pedirDatosEventoCategoria("categoría");
            String categoriaEvento = s.nextLine().toUpperCase();
            categoriaEve = switch (categoriaEvento){
                case "ARTE" -> CategoriaEvento.ARTE;
                case "TECNOLOGIA" -> CategoriaEvento.TECNOLOGIA;
                case "CINE" -> CategoriaEvento.CINE;
                case "MUSICA" -> CategoriaEvento.MUSICA;
                case "MODA" -> CategoriaEvento.MODA;
                case "JUEGOS" -> CategoriaEvento.JUEGOS;
                default -> null;
            };

            if (categoriaEve == null){
                vista.categoriaNoValida();
            }
        }

        LocalDate fechaEve = FuncionesFechas.pedirFechaValida(s, vista);

        vista.pedirDatosEvento("Introduce el aforo del evento: ");
        int aforoEvento = Integer.parseInt(s.nextLine());
        vista.pedirDatosEvento("Introduce el número de inscritos: ");
        int numInscritosEvento = Integer.parseInt(s.nextLine());

        return new Evento (nombreEvento,descripcionEvento,categoriaEve,fechaEve,aforoEvento,numInscritosEvento);
    }

    public boolean aniadirEvento (Evento nuevoEvento){
        if (numEventos == eventos.length){
            aumentarCapacidad(1);
        }

        if(numEventos < eventos.length){
            eventos[numEventos++] = nuevoEvento;
            return true;
        }else{
            return false;
        }
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

    public void mostrarEventos(){
        if (numEventos == 0){
            vista.noHayEventos();
            return;
        }

        vista.tituloEventosDisponibles();

        for (int i = 0; i < numEventos; i++) {
            Evento evento = eventos[i];
            if (evento != null){
                vista.mostrarEventoTabla(
                        evento.getNombre(),
                        evento.getCategoria().toString(),
                        FuncionesFechas.convertirLocalDateString(evento.getFecha())
                );

                vista.mostrarVistaDetalladaEvento(
                        evento.getNombre(),
                        evento.getCategoria().toString(),
                        FuncionesFechas.convertirLocalDateString(evento.getFecha()),
                        evento.getDescripcion(),
                        evento.getAforo(),
                        evento.getPersonasInscritas()
                );
            }
        }
        
    }

    //----------------------------------------------------------------------------------------------------
    //U --> UPDATE
    public void modificarEvento(){
        Scanner s = new Scanner(System.in);
        if (numEventos == 0){
            vista.noHayEventos();
            return;
        }

        vista.mostrarListaEventos(eventos, numEventos);
        vista.pedirDatosEvento("Introduce el nombre del evento que quieres modificar: ");
        String nombreActual = s.nextLine();

        Evento evento = buscarEventoPorNombre(nombreActual);

        if(evento != null){
            vista.mostrarOpcionesEvento();
            int opcion = Integer.parseInt(s.nextLine());
            boolean funciona = false;

            switch (opcion){
                case 1:
                    vista.pedirDatosEvento("Introduce el nuevo nombre: ");
                    funciona= actualizarNombre(nombreActual, s.nextLine());
                    break;
                case 2:
                    vista.pedirDatosEvento("Introduce la nueva descripción: ");
                    funciona= actualizarDescripcion(nombreActual, s.nextLine());
                    break;
                case 3:
                    CategoriaEvento categoriaEve = null;
                    while (categoriaEve == null){
                        vista.pedirDatosEvento("Introduce la nueva categoría (ARTE, TECNOLOGIA, CINE, MUSICA, MODA, JUEGOS): ");
                        String nuevaCategoria = s.nextLine().toUpperCase();
                        categoriaEve = switch (nuevaCategoria){
                            case "ARTE" -> CategoriaEvento.ARTE;
                            case "TECNOLOGIA" -> CategoriaEvento.TECNOLOGIA;
                            case "CINE" -> CategoriaEvento.CINE;
                            case "MUSICA" -> CategoriaEvento.MUSICA;
                            case "MODA" -> CategoriaEvento.MODA;
                            case "JUEGOS" -> CategoriaEvento.JUEGOS;
                            default -> null;
                        };

                        if (categoriaEve !=null){
                            funciona= actualizarCategoria(nombreActual,categoriaEve);
                        }else {
                            vista.categoriaNoValida();
                        }
                    }
                    break;

                case 4:
                    LocalDate nuevaFecha = FuncionesFechas.pedirFechaValida(s, vista);
                    funciona = actualizarFecha(nombreActual, FuncionesFechas.convertirLocalDateString(nuevaFecha));
                    break;
                case 5:
                    vista.pedirDatosEvento("Introduce el nuevo aforo: ");
                    funciona= actualizarAforo(nombreActual,Integer.parseInt(s.nextLine()));
                    break;
                case 6:
                    vista.pedirDatosEvento("Introduce los inscritos: ");
                    funciona= actualizarInscritos(nombreActual, Integer.parseInt(s.nextLine()));
                    break;
                case 7:
                    vista.operacionCancelada();
                    return;
                default:
                    vista.opcionNoValida();
                    return;
            }
            if (funciona){
                vista.mensajeConfirmacion();
            }
        }else{
            vista.eventoNoEncontrado();
        }
    }

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

        evento.setCategoria(nuevaCategoria);
        return true;
    }

    public boolean actualizarFecha(String nombreEvento, String nuevaFecha){
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        LocalDate fecha = FuncionesFechas.convertirStringEnFecha(nuevaFecha);
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

    public boolean actualizarInscritos(String nombreEvento, int cantidad) {
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

    public void eliminarEvento() {
        Scanner s = new Scanner(System.in);
        if (numEventos == 0) {
            vista.noHayEventos();
        } else {
            vista.mostrarListaEventos(eventos, numEventos);
            vista.pedirDatosEvento("Escribe el nombre del evento que quieres eliminar: ");
            String nombreEvento = s.nextLine();
            Evento evento = buscarEventoPorNombre(nombreEvento);

            if (evento != null) {
                if (vista.pedirConfirmacion("¿Estás seguro de que quieres eliminar " + nombreEvento + " ?")) {
                    if (eliminarEvento(nombreEvento)) {
                        vista.mensajeConfirmacion();
                    } else {
                        vista.mensajeError();
                    }
                } else {
                    vista.operacionCancelada();
                }
            } else {
                vista.eventoNoEncontrado();
            }
        }
    }}

//    //métodos adicionales que nos pueden servir
//    //nos vale para cuando queramos saber cuantas plazas libres quedan en un evento.
//
//     public int getAforoDisponible(String nombreEvento){
//        Evento evento = buscarEventoPorNombre(nombreEvento);
//        if (evento == null) return -1;
//
//        return evento.getAforo() - evento.getPersonasInscritas();
//    }

//    //verificar si se pueden comprar x entradas antes de que nos permita comprarlas
//    public boolean hayPlazasDisponibles (String nombreEvento, int plazasSolicitadas){
//        int disponibles = getAforoDisponible(nombreEvento);
//        return disponibles >= plazasSolicitadas;
//    }

    //METODOS PARA ENTRADAS
//    public float getPrecioTipo(String nombreEvento, String nombreTipo) {
//        Evento evento = buscarEventoPorNombre(nombreEvento);
//        if (evento == null) {
//            return -1;
//        }
//        EntradasTipo tipo = evento.getTipoEntrada(nombreTipo);
//        if (tipo == null) {
//            return -1;
//        }
//        return tipo.getPrecio();
//    }
//
//    public int getCantidadDisponible(String nombreEvento, String nombreTipo) {
//        Evento evento = buscarEventoPorNombre(nombreEvento);
//        if (evento == null) {
//            return -1;
//        }
//        EntradasTipo tipo = evento.getTipoEntrada(nombreTipo);
//
//        if (tipo == null) {
//            return -1;
//        }
//        return tipo.getCantidadDisponible();
//    }
//
//    public boolean comprarEntradas (String nombreEvento, String nombreTipo, int cantidad){
//        Evento evento = buscarEventoPorNombre(nombreEvento);
//        if (evento == null) return false;
//        return evento.venderEntradas(nombreTipo, cantidad);
//    }
