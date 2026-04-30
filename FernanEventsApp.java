package FernanEvents;

import FernanEvents.controlador.ControladorFernan;
import FernanEvents.modelo.GestionEvento;
import FernanEvents.modelo.GestionUsuario;
import FernanEvents.vista.VistaFernan;

public class FernanEventsApp {
    static void main(String[] args) throws InterruptedException {

        GestionUsuario modelo = new GestionUsuario();
        VistaFernan vista = new VistaFernan();
        GestionEvento evento = new GestionEvento(vista);
        ControladorFernan controlador = new ControladorFernan(modelo,vista, evento);

        controlador.iniciarFernan();
    }
}
