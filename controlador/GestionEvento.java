package FernanEvents.controlador;

import FernanEvents.modelo.CategoriaEntrada;
import FernanEvents.modelo.CategoriaEvento;
import FernanEvents.modelo.EntradasTipo;
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

    public GestionEvento(int tamanio, VistaFernan vista) {
        eventos = new Evento[tamanio];
        numEventos = 0;
        this.vista = vista;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MÉTODOS INTERFAZ AUMENTABLE.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Aumenta la capacidad del array de eventos en 1
     */
    public void aumentarCapacidad() {
        aumentarCapacidad(1);
    }

    /**
     * Aumenta la capacidad del array de eventos en la cantidad que se le pase
     */
    public void aumentarCapacidad(int cantidad) {
        Evento[] nuevoArray = new Evento[eventos.length + cantidad];
        for (int i = 0; i < numEventos; i++) {
            nuevoArray[i] = eventos[i];
        }
        this.eventos = nuevoArray;
    }

    /**
     * Disminuye la capacidad del array de eventos en 1
     */
    public void disminuirCapacidad() {
        disminuirCapacidad(1);
    }

    /**
     * Disminuye la capacidad del array en la cantidad que se le pase
     */
    public void disminuirCapacidad(int cantidad) {
        int nuevoTamanio = eventos.length - cantidad;
        if (nuevoTamanio < numEventos) {
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
    /**
     * Crea un nuevo evento pidiendo los datos
     */
    public Evento crearEvento() {
        Scanner s = new Scanner(System.in);

        vista.pedirDatosEvento("Introduce el nombre del evento: ");
        String nombreEvento = s.nextLine();
        vista.pedirDatosEvento("Introduce la descripción del evento: ");
        String descripcionEvento = s.nextLine();
        CategoriaEvento categoriaEve = null;
        while (categoriaEve == null) {
            vista.pedirDatosEventoCategoria("categoría");
            String categoriaEvento = s.nextLine().toUpperCase();
            categoriaEve = switch (categoriaEvento) {
                case "ARTE" -> CategoriaEvento.ARTE;
                case "TECNOLOGIA" -> CategoriaEvento.TECNOLOGIA;
                case "CINE" -> CategoriaEvento.CINE;
                case "MUSICA" -> CategoriaEvento.MUSICA;
                case "MODA" -> CategoriaEvento.MODA;
                case "JUEGOS" -> CategoriaEvento.JUEGOS;
                default -> null;
            };

            if (categoriaEve == null) {
                vista.categoriaNoValida();
            }
        }

        LocalDate fechaEve = FuncionesFechas.pedirFechaValida(s, vista);

        vista.pedirDatosEvento("Introduce el aforo del evento: ");
        int aforoEvento = Integer.parseInt(s.nextLine());
        vista.pedirDatosEvento("Introduce el número de inscritos: ");
        int numInscritosEvento = Integer.parseInt(s.nextLine());

        Evento nuevoEvento = new Evento(nombreEvento, descripcionEvento, categoriaEve, fechaEve, aforoEvento,
                numInscritosEvento);
        int aforoRestante = nuevoEvento.getAforoRestante();
        CategoriaEntrada[] categorias = {CategoriaEntrada.GENERAL, CategoriaEntrada.VIP, CategoriaEntrada.INFANTIL};
        String[] nombreCategoriasEntrada = {"General", "VIP","Infantil"};
        float precio = 0;

        for (int i = 0; i < 3; i++) {
            if(aforoRestante <= 0){
                vista.aforoCompleto(nombreCategoriasEntrada[i]);
                nuevoEvento.setConfiguracionEntrada(i, new EntradasTipo(categorias[i], 0, 0));
            }else{
                vista.preguntaCantidadEntradasPorTipo(nombreCategoriasEntrada[i], aforoRestante);
                int cantidadEntradas = Integer.parseInt(s.nextLine());

                if(cantidadEntradas > aforoRestante){
                    vista.errorCantidadNoValida();
                    nuevoEvento.setConfiguracionEntrada(i, new EntradasTipo(categorias[i], 0, 0));
                }else{
                    if(cantidadEntradas > 0){
                        vista.preguntaPrecioEntrada(nombreCategoriasEntrada[i]);
                        precio = Float.parseFloat(s.nextLine());
                    }
                    EntradasTipo entrada = new EntradasTipo(categorias[i], precio, cantidadEntradas);
                    nuevoEvento.setConfiguracionEntrada(i, entrada);

                    aforoRestante -= cantidadEntradas;
                }
            }
        }
        return nuevoEvento;
    }

    /**
     * Añade un nuevo evento al array
     */
    public boolean aniadirEvento(Evento nuevoEvento) {
        if (numEventos == eventos.length) {
            aumentarCapacidad(1);
        }

        if (numEventos < eventos.length) {
            eventos[numEventos++] = nuevoEvento;
            return true;
        } else {
            return false;
        }
    }

    //----------------------------------------------------------------------------------------------------
    //R --> READ
    /**
     * Busca un evento por su nombre
     */
    public Evento buscarEventoPorNombre(String nombre) {
        for (int i = 0; i < numEventos; i++) {
            if (eventos[i] != null && eventos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return eventos[i];
            }
        }
        return null;
    }

    /**
     * Busca la posición de un evento en el array por su nombre
     */
    public int buscarPosicionPorNombre(String nombre) {
        for (int i = 0; i < numEventos; i++) {
            if (eventos[i] != null && eventos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Muestra todos los eventos disponibles
     */
    public void mostrarEventos() {
        if (numEventos == 0) {
            vista.noHayEventos();
            return;
        }
        vista.tituloEventosDisponibles();

        for (int i = 0; i < numEventos; i++) {
            Evento evento = eventos[i];
            if (evento != null) {
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

                vista.mostrarVistaDetalladaEntradas(evento.getTiposDeEntrada());
            }
        }

    }

    //----------------------------------------------------------------------------------------------------
    //U --> UPDATE
    /**
     * Gestiona la modificación de un evento
     */
    public void modificarEvento() {
        Scanner s = new Scanner(System.in);
        if (numEventos == 0) {
            vista.noHayEventos();
            return;
        }

        vista.mostrarListaEventos(eventos, numEventos);
        vista.pedirDatosEvento("Introduce el nombre del evento que quieres modificar: ");
        String nombreActual = s.nextLine();

        Evento evento = buscarEventoPorNombre(nombreActual);

        if (evento != null) {
            vista.mostrarOpcionesEvento();
            int opcion = Integer.parseInt(s.nextLine());
            boolean funciona = false;

            switch (opcion) {
                case 1:
                    vista.pedirDatosEvento("Introduce el nuevo nombre: ");
                    funciona = actualizarNombre(nombreActual, s.nextLine());
                    break;
                case 2:
                    vista.pedirDatosEvento("Introduce la nueva descripción: ");
                    funciona = actualizarDescripcion(nombreActual, s.nextLine());
                    break;
                case 3:
                    CategoriaEvento categoriaEve = null;
                    while (categoriaEve == null) {
                        vista.pedirDatosEvento("Introduce la nueva categoría (ARTE, TECNOLOGIA, CINE, MUSICA, MODA, JUEGOS): ");
                        String nuevaCategoria = s.nextLine().toUpperCase();
                        categoriaEve = switch (nuevaCategoria) {
                            case "ARTE" -> CategoriaEvento.ARTE;
                            case "TECNOLOGIA" -> CategoriaEvento.TECNOLOGIA;
                            case "CINE" -> CategoriaEvento.CINE;
                            case "MUSICA" -> CategoriaEvento.MUSICA;
                            case "MODA" -> CategoriaEvento.MODA;
                            case "JUEGOS" -> CategoriaEvento.JUEGOS;
                            default -> null;
                        };

                        if (categoriaEve != null) {
                            funciona = actualizarCategoria(nombreActual, categoriaEve);
                        } else {
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
                    funciona = actualizarAforo(nombreActual, Integer.parseInt(s.nextLine()));
                    break;
                case 6:
                    vista.pedirDatosEvento("Introduce los inscritos: ");
                    funciona = actualizarInscritos(nombreActual, Integer.parseInt(s.nextLine()));
                    break;
                case 7:
                    funciona = actualizarEntradasInterno(evento);
                    break;

                case 8:
                    vista.operacionCancelada();
                    break;

                default:
                    vista.opcionNoValida();
                    break;
            }
            if (funciona) {
                vista.mensajeConfirmacion();
            }
        } else {
            vista.eventoNoEncontrado();
        }
    }

    /**
     * Actualiza el nombre de un evento
     */
    public boolean actualizarNombre(String nombreActual, String nuevoNombre) {
        Evento evento = buscarEventoPorNombre(nombreActual);
        if (evento == null) return false;

        if (buscarEventoPorNombre(nuevoNombre) != null && !nombreActual.equalsIgnoreCase(nuevoNombre)) {
            return false;
        }

        evento.setNombre(nuevoNombre);
        return true;
    }

    /**
     * Actualiza la descripción de un evento
     */
    public boolean actualizarDescripcion(String nombreEvento, String nuevaDescripcion) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        evento.setDescripcion(nuevaDescripcion);
        return true;
    }

    /**
     * Actualiza la categoria de un evento
     */
    public boolean actualizarCategoria(String nombreEvento, CategoriaEvento nuevaCategoria) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        evento.setCategoria(nuevaCategoria);
        return true;
    }

    /**
     * Actualiza la fecha de un evento
     */
    public boolean actualizarFecha(String nombreEvento, String nuevaFecha) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        LocalDate fecha = FuncionesFechas.convertirStringEnFecha(nuevaFecha);
        evento.setFecha(fecha);
        return true;
    }

    /**
     * Actualiza el aforo de un evento
     */
    public boolean actualizarAforo(String nombreEvento, int nuevoAforo) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        if (nuevoAforo < evento.getPersonasInscritas()) {
            return false;
        }

        evento.setAforo(nuevoAforo);
        return true;
    }

    /**
     * Actualiza las personas inscritas de un evento
     */
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

    /**
     * Actualiza la información relacionada con las entradas disponibles para un evento de la plataforma
     */
    public boolean actualizarEntradasInterno(Evento evento){
        Scanner s = new Scanner(System.in);
        int aforoRestante = evento.getAforo() - evento.getPersonasInscritas();
        CategoriaEntrada[] categorias = {CategoriaEntrada.GENERAL, CategoriaEntrada.VIP, CategoriaEntrada.INFANTIL};
        String[] nombreCategoriasEntrada = {"General", "VIP","Infantil"};

        for (int i = 0; i < 3; i++) {
            vista.preguntaCantidadEntradasPorTipo(nombreCategoriasEntrada[i], aforoRestante);
            int cantidad = Integer.parseInt(s.nextLine());

            if(cantidad <= aforoRestante){
                float precio = 0;
                if(cantidad > 0){
                    vista.preguntaPrecioEntrada(nombreCategoriasEntrada[i]);
                    precio = Float.parseFloat(s.nextLine());
                }
                EntradasTipo modificacionEntrada = new EntradasTipo(categorias[i], precio, cantidad);
                evento.setConfiguracionEntrada(i, modificacionEntrada);
                aforoRestante -= cantidad;
            }else{
                vista.errorCantidadNoValida();
                evento.setConfiguracionEntrada(i, new EntradasTipo(categorias[i], 0 ,0));
            }
        }
        return true;
    }

    //D --> DELETE
    /**
     * Elimina un evento por su nombre
     */
    public boolean eliminarEvento(String nombre) {
        int posicion = buscarPosicionPorNombre(nombre);
        if (posicion == -1) return false;

        eventos[posicion] = eventos[numEventos - 1];
        eventos[numEventos - 1] = null;
        numEventos--;

        return true;
    }

    /**
     * Gestiona la eliminación de un evento pidiendo confirmación
     */
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
    }

    /**
     * Actualiza el stock de las entradas y de las personas inscritas a un evento
     */
    public boolean controlaStockCorrecto(Evento evento, int indiceEntrada, int cantidad) {
        EntradasTipo entrada = evento.getTiposDeEntrada()[indiceEntrada];

        if (entrada != null && entrada.getCantidadDisponible() >= cantidad) {
            entrada.setCantidadDisponible(entrada.getCantidadDisponible() - cantidad);
            evento.setPersonasInscritas(evento.getPersonasInscritas() + cantidad);
            return true;
        }
        return false;
    }

}
