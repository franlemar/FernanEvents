package FernanEvents.modelo;

import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.DAOEntradaSQL;
import FernanEvents.modelo.dao.modeloDAO.DAOEventoSQL;
import FernanEvents.modelo.utilidades.FuncionesFechas;
import FernanEvents.vista.VistaFernan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class GestionEvento {

    private ArrayList<Evento> eventos;
    private VistaFernan vista;
    private DAOEventoSQL eventoDAO;
    private DAOEntradaSQL entradaDAO;

    public GestionEvento(VistaFernan vista) {
        eventos = new ArrayList<>();
        this.vista = vista;
        this.eventoDAO = new DAOEventoSQL();
        this.entradaDAO = new DAOEntradaSQL();
    }

    private DAOManager getDAOManager(){
        return DAOManager.getSingletonInstance();
    }

    public void cargarEventosDesdeBDD(){
        DAOManager daoManager = getDAOManager();
        ArrayList<Evento> eventosBDD = eventoDAO.readAll(daoManager);

        if(eventosBDD != null){
            eventos.clear();
            eventos.addAll(eventosBDD);
        }
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

        for(CategoriaEntrada categoria : CategoriaEntrada.values()){
            if(aforoRestante <= 0){
                vista.aforoCompleto(categoria.toString());
                nuevoEvento.setConfiguracionEntrada(new Entrada(categoria, 0, 0));
            }else{
                vista.preguntaCantidadEntradasPorTipo(categoria.toString(), aforoRestante);
                int cantidadEntradas = Integer.parseInt(s.nextLine());

                if(cantidadEntradas > aforoRestante || cantidadEntradas < 0){
                    vista.errorCantidadNoValida();
                    nuevoEvento.setConfiguracionEntrada(new Entrada(categoria, 0, 0));
                }else{
                    float precio = 0;
                    if(cantidadEntradas > 0){
                        vista.preguntaPrecioEntrada(categoria.toString());
                        precio = Float.parseFloat(s.nextLine());
                    }

                    nuevoEvento.setConfiguracionEntrada(new Entrada(categoria, precio, cantidadEntradas));
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
        if(nuevoEvento == null) return false;

        if(eventoDAO.insert(nuevoEvento, getDAOManager())){
            for(Entrada entrada : nuevoEvento.getTiposDeEntrada()){
                entrada.setId_evento(nuevoEvento.getId());
                entradaDAO.insert(entrada, getDAOManager());
            }
            eventos.add(nuevoEvento);
            return true;
        }
        return false;
    }

    //----------------------------------------------------------------------------------------------------
    //R --> READ
    /**
     * Busca un evento por su nombre
     */
    public Evento buscarEventoPorNombre(String nombre) {
        for (Evento evento : eventos) {
            if (evento.getNombre().equalsIgnoreCase(nombre.trim())) {
                return evento;
            }
        }
        return null;
    }

    /**
     * Busca la posición de un evento en el array por su nombre
     */
    public int buscarPosicionPorNombre(String nombre) {
        for (int i = 0; i < eventos.size(); i++) {
            if (eventos.get(i).getNombre().equalsIgnoreCase(nombre.trim())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Muestra todos los eventos disponibles
     */
    public void mostrarEventos() {
        if (eventos.isEmpty()) {
            vista.noHayEventos();
            return;
        }
        vista.tituloEventosDisponibles();

        for (Evento evento : eventos) {

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

    /**
     * Devuelve un ArrayList de Eventos
     */
    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    //----------------------------------------------------------------------------------------------------
    //U --> UPDATE
    /**
     * Gestiona la modificación de un evento
     */
    public void modificarEvento() {
        Scanner s = new Scanner(System.in);
        if (eventos.isEmpty()) {
            vista.noHayEventos();
            return;
        }

        vista.mostrarListaEventos(eventos, eventos.size());
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

        String nombreAnterior = evento.getNombre();
        evento.setNombre(nuevoNombre);

        if (eventoDAO.update(evento, getDAOManager())) {
            return true;
        } else {
            evento.setNombre(nombreAnterior);
            return false;
        }
    }

    /**
     * Actualiza la descripción de un evento
     */
    public boolean actualizarDescripcion(String nombreEvento, String nuevaDescripcion) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        String descAnterior = evento.getDescripcion();
        evento.setDescripcion(nuevaDescripcion);

        if (eventoDAO.update(evento, getDAOManager())) {
            return true;
        } else {
            evento.setDescripcion(descAnterior);
            return false;
        }
    }

    /**
     * Actualiza la categoria de un evento
     */
    public boolean actualizarCategoria(String nombreEvento, CategoriaEvento nuevaCategoria) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        CategoriaEvento catAnterior = evento.getCategoria();
        evento.setCategoria(nuevaCategoria);

        if (eventoDAO.update(evento, getDAOManager())) {
            return true;
        } else {
            evento.setCategoria(catAnterior);
            return false;
        }
    }

    /**
     * Actualiza la fecha de un evento
     */
    public boolean actualizarFecha(String nombreEvento, String nuevaFecha) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        LocalDate fechaAnterior = evento.getFecha();
        LocalDate fecha = FuncionesFechas.convertirStringEnFecha(nuevaFecha);
        evento.setFecha(fecha);

        if (eventoDAO.update(evento, getDAOManager())) {
            return true;
        } else {
            evento.setFecha(fechaAnterior);
            return false;
        }
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

        int aforoAnterior = evento.getAforo();
        evento.setAforo(nuevoAforo);

        if (eventoDAO.update(evento, getDAOManager())) {
            return true;
        } else {
            evento.setAforo(aforoAnterior);
            return false;
        }
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

        int inscritosAnteriores = evento.getPersonasInscritas();
        evento.setPersonasInscritas(nuevosInscritos);

        if (eventoDAO.update(evento, getDAOManager())) {
            return true;
        } else {
            evento.setPersonasInscritas(inscritosAnteriores);
            return false;
        }
    }

    /**
     * Actualiza la información relacionada con las entradas disponibles para un evento de la plataforma
     */
    public boolean actualizarEntradasInterno(Evento evento){
        Scanner s = new Scanner(System.in);
        int aforoRestante = evento.getAforo() - evento.getPersonasInscritas();
        ArrayList<Entrada> entradasAnteriores = evento.getTiposDeEntrada();
        ArrayList<Entrada> nuevasEntradas = new ArrayList<>();

        for(CategoriaEntrada categoria : CategoriaEntrada.values()){
            vista.preguntaCantidadEntradasPorTipo(categoria.toString(), aforoRestante);
            int cantidad = Integer.parseInt(s.nextLine());

            Entrada nuevaEntrada;
            if(cantidad <= aforoRestante && cantidad >= 0){
                float precio = 0;
                if(cantidad > 0){
                    vista.preguntaPrecioEntrada(categoria.toString());
                    precio = Float.parseFloat(s.nextLine());
                }
                nuevaEntrada = new Entrada(categoria, precio, cantidad);
                aforoRestante -= cantidad;
            }else{
                vista.errorCantidadNoValida();
                nuevaEntrada = new Entrada(categoria, 0, 0);
            }

            for(Entrada entradaAnterior : entradasAnteriores){
                if(entradaAnterior.getCategoria().equals(categoria)){
                    nuevaEntrada.setId(entradaAnterior.getId());
                    nuevaEntrada.setId_evento(entradaAnterior.getId_evento());
                    break;
                }
            }
            nuevasEntradas.add(nuevaEntrada);
        }

        evento.setTiposDeEntrada(nuevasEntradas);

        // Actualizamos cada entrada individualmente en BD
        boolean todasEntradasOk = true;
        for(Entrada entrada : nuevasEntradas){
            if(!entradaDAO.update(entrada, getDAOManager())){
                todasEntradasOk = false;
                break;
            }
        }

        if(eventoDAO.update(evento, getDAOManager()) && todasEntradasOk){
            return true;
        } else {
            evento.setTiposDeEntrada(entradasAnteriores);
            return false;
        }
    }

    //D --> DELETE
    /**
     * Elimina un evento por su nombre
     */
    private boolean eliminaEvento(String nombreEvento) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) return false;

        if (eventoDAO.delete(evento, getDAOManager())) {
            eventos.remove(evento);
            return true;
        }
        return false;
    }

    /**
     * Gestiona la eliminación de un evento pidiendo confirmación
     */
    public String eliminarEvento() {
        Scanner s = new Scanner(System.in);
        if (eventos.isEmpty()) {
            vista.noHayEventos();
            return null;
        }

        vista.mostrarListaEventos(eventos, eventos.size());
        vista.pedirDatosEvento("Escribe el nombre del evento que quieres eliminar: ");
        String nombreEvento = s.nextLine();
        Evento evento = buscarEventoPorNombre(nombreEvento);

        if (evento != null) {
            if (vista.pedirConfirmacion("¿Estás seguro de que quieres eliminar " + nombreEvento + " ?")) {
                if (eliminaEvento(nombreEvento)) {
                    return nombreEvento;
                } else {
                    vista.mensajeError();
                }
            } else {
                vista.operacionCancelada();
            }
        } else {
            vista.eventoNoEncontrado();
        }
        return null;
    }

    /**
     * Actualiza el stock de las entradas y de las personas inscritas a un evento
     */
    public boolean controlaStockCorrecto(Evento evento, CategoriaEntrada categoriaEntrada, int cantidad) {
        for(Entrada entrada : evento.getTiposDeEntrada()){
            if(entrada.getCategoria().equals(categoriaEntrada)){
                if(entrada.getCantidadDisponible() >= cantidad){

                    int cantAnteriorEntrada = entrada.getCantidadDisponible();
                    int inscritosAnterioresEvento = evento.getPersonasInscritas();

                    entrada.setCantidadDisponible(cantAnteriorEntrada - cantidad);
                    evento.setPersonasInscritas(inscritosAnterioresEvento + cantidad);

                    boolean eventoActualizado = eventoDAO.update(evento, getDAOManager());
                    boolean entradaActualizada = entradaDAO.update(entrada, getDAOManager());

                    if (eventoActualizado && entradaActualizada) {
                        return true;
                    } else {
                        entrada.setCantidadDisponible(cantAnteriorEntrada);
                        evento.setPersonasInscritas(inscritosAnterioresEvento);
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Permite ordenar los eventos ordenados de mayor número de personas inscritas a menor
     */
    public void ordenarEventosPorAsistentesDesc() {
        eventos.sort((e1, e2) -> Integer.compare(e2.getPersonasInscritas(), e1.getPersonasInscritas()));
    }

    /**
     * Permite ordenar los eventos por fecha de más reciente a más antigua
     */
    public void ordenarEventosPorFecha() {
        eventos.sort((e1, e2) -> e2.getFecha().compareTo(e1.getFecha()));
    }

    /**
     * Permite ordenar las entradas por su tipo alfabéticamente
     */
    public void ordenarEntradasPorTipo(Evento evento) {
        if (evento != null) {
            evento.getTiposDeEntrada().sort(Comparator.comparing(t -> t.getCategoria().name()));
        }
    }

    /**
     * Permite ordenar las entradas por su precio, de más cara a más económica
     */
    public void ordenarEntradasPorImporteDesc(Evento evento) {
        if (evento != null) {
            evento.getTiposDeEntrada().sort((t1, t2) -> Float.compare(t2.getPrecio(), t1.getPrecio()));
        }
    }

    /**
     * Permite ordenar las entradas por su precio, de más económica a mas cára
     */
    public void ordenarEntradasPorImporteAsc(Evento evento) {
        if (evento != null) {
            evento.getTiposDeEntrada().sort((t1, t2) -> Float.compare(t1.getPrecio(), t2.getPrecio()));
        }
    }

}
