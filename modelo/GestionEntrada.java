package FernanEvents.modelo;
import FernanEvents.modelo.utilidades.EnvioGmail;
import FernanEvents.modelo.utilidades.GestorLogs;
import FernanEvents.vista.VistaFernan;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionEntrada {
    private GestionUsuario modeloUsu;
    private GestionEvento modeloEve;
    private VistaFernan vista;
    private Usuario usuarioLogueado;
    private GestorLogs logs;

    public GestionEntrada(GestionUsuario modeloUsu, GestionEvento modeloEve, VistaFernan vista, Usuario usuarioLogueado, GestorLogs logs) {
        this.modeloUsu = modeloUsu;
        this.modeloEve = modeloEve;
        this.vista = vista;
        this.usuarioLogueado = usuarioLogueado;
        this.logs = logs;
    }

    /**
     * Establece el usuario logueado actual a través de un usuario que recibe el método por parámetro
     */
    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * Se encarga de todo el proceso de compra de entradas a un evento para los asistentes
     */
    public void gestionCompraEntradas(){
        Scanner s = new Scanner(System.in);

        if(modeloEve.getEventos().isEmpty()){
            vista.noHayEventos();
            return;
        }

        if(gestionarVisualizacionEventosEntradas()){
            vista.pedirNombreEventoInscribir();
            String eventoAInscribir = s.nextLine();
            Evento eventoSeleccionado = modeloEve.buscarEventoPorNombre(eventoAInscribir);

            if(eventoSeleccionado != null){
                ArrayList<Entrada> entradas = eventoSeleccionado.getTiposDeEntrada();
                vista.menuEntradaTipo(entradas);
                int opcionTipoEntrada = Integer.parseInt(s.nextLine()) - 1;

                if(opcionTipoEntrada < 0 || opcionTipoEntrada >= eventoSeleccionado.getTiposDeEntrada().size()){
                    vista.opcionNoValida();
                }else{
                    Entrada tipoEntradaElegido = eventoSeleccionado.getTiposDeEntrada().get(opcionTipoEntrada);
                    vista.mostrarDetallePreCompra(tipoEntradaElegido.getCategoria().toString(), tipoEntradaElegido.getPrecio());
                    int cantidadEntradas = Integer.parseInt(s.nextLine());

                    Asistente asistente = (Asistente) usuarioLogueado;
                    int entradasYaCompradas = asistente.getNumEntradasEvento(eventoSeleccionado.getNombre());

                    if(entradasYaCompradas + cantidadEntradas > 4){
                        vista.errorLimiteEntradas(entradasYaCompradas);
                    }else if(cantidadEntradas <= 0){
                        vista.errorCantidadNoValida();
                    }else{
                        float precioTotal = cantidadEntradas * tipoEntradaElegido.getPrecio();

                        if(tipoEntradaElegido.getCantidadDisponible() < cantidadEntradas){
                            vista.noHayStockEntradas();
                        }else if(usuarioLogueado.getSaldo() < precioTotal){
                            vista.saldoInsuficiente();
                        }else{
                            String mensajeConfirmaCompra = confirmaCompraEntrada(cantidadEntradas, precioTotal);
                            if(mensajeConfirmaCompra.equalsIgnoreCase("si")){
                                movimientoSaldosCompraEntrada(asistente, precioTotal, eventoSeleccionado,
                                        tipoEntradaElegido.getCategoria(), cantidadEntradas);
                            }else{
                                vista.operacionCancelada();
                            }
                        }
                    }
                }
            }else{
                vista.eventoNoEncontrado();
            }
        }
    }

    /**
     * Gestiona el movimiento de los saldos entre las carteras del administrador, organizadores y asistentes
     */
    private void movimientoSaldosCompraEntrada(Asistente asistente, float precioTotal, Evento eventoSeleccionado, CategoriaEntrada categoria, int cantidadEntradas ){
        modeloUsu.quitarSaldo(usuarioLogueado.getCorreo(), precioTotal);
        modeloUsu.aniadirSaldo(eventoSeleccionado.getOrganizador().getCorreo(), precioTotal * 0.90f);
        modeloUsu.aniadirSaldo("admin@fernanevents.com", precioTotal * 0.10f);
        modeloEve.controlaStockCorrecto(eventoSeleccionado, categoria, cantidadEntradas);
        asistente.registraCompraEntrada(eventoSeleccionado.getNombre(), cantidadEntradas);
        vista.mensajeConfirmacion();
        logs.registrar("Compra de entradas: " + cantidadEntradas + " para " + eventoSeleccionado.getNombre(),
                usuarioLogueado.getNombre());
    }

    /**
     * Confirma mediante unos mensajes en consola, el proceso de compra de entradas
     */
    private String confirmaCompraEntrada(int cantidadEntradas, float precioTotal){
        Scanner s = new Scanner(System.in);
        vista.avisoPrecioTotal(cantidadEntradas, precioTotal);
        vista.preguntaConfirmacionCompra(usuarioLogueado.getSaldo());
        return s.nextLine();
    }

    /**
     * Método que se encarga de ordenar y mostrar los eventos disponibles en FernanEvents en función de los
     * criterios que introduzca por consola el usuario
     */
    public boolean gestionarVisualizacionEventosEntradas() {
        Scanner s = new Scanner(System.in);
        vista.menuOrdenaEventos();
        int opcionEventos = Integer.parseInt(s.nextLine());
        if (opcionEventos == 3) return false;

        switch (opcionEventos) {
            case 1 -> modeloEve.ordenarEventosPorFecha();
            case 2 -> modeloEve.ordenarEventosPorAsistentesDesc();
            default -> {
                vista.opcionNoValida();
                return false;
            }
        }

        vista.menuOrdenaEntradas();
        int opcionEntradas = Integer.parseInt(s.nextLine());
        if (opcionEntradas == 4) return false;

        for (Evento evento : modeloEve.getEventos()) {
            switch (opcionEntradas) {
                case 1 -> modeloEve.ordenarEntradasPorImporteDesc(evento);
                case 2 -> modeloEve.ordenarEntradasPorImporteAsc(evento);
                case 3 -> modeloEve.ordenarEntradasPorTipo(evento);
                default -> {
                    vista.opcionNoValida();
                    return false;
                }
            }
        }
        modeloEve.mostrarEventos();
        return true;
    }

    /**
     * Recorre todos los asistentes y envía a cada uno un Excel con sus entradas
     */
    public void enviarListadoEntradasPorCorreo() {
        vista.enviandoCorreosEntradas();
        for (Usuario u : modeloUsu.getUsuarios()) {
            if (u instanceof Asistente asistente) {
                if (!asistente.getEventosInscrito().isEmpty()) {
                    EnvioGmail.enviarResumenEntradasAsistente(
                            asistente.getCorreo(),
                            asistente.getNombre(),
                            asistente.getEventosInscrito(),
                            modeloEve.getEventos()
                    );
                }
            }
        }
        vista.mensajeConfirmacion();
    }
}
