package FernanEvents;

import FernanEvents.controlador.ControladorFernan;
import FernanEvents.controlador.GestionEvento;
import FernanEvents.controlador.GestionUsuario;
import FernanEvents.vista.VistaFernan;

public class FernanEventsApp {
    static void main(String[] args) throws InterruptedException {

        GestionUsuario modelo = new GestionUsuario(10);
        VistaFernan vista = new VistaFernan();
        GestionEvento evento = new GestionEvento(10, vista);
        ControladorFernan controlador = new ControladorFernan(modelo,vista, evento);

        controlador.iniciarFernan();
    }
}
