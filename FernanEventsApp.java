package FernanEvents;

import FernanEvents.controlador.ControladorFernan;
import FernanEvents.modelo.GestionEvento;
import FernanEvents.modelo.GestionUsuario;
import FernanEvents.vista.VistaFernan;

public class FernanEventsApp {
    static void main(String[] args) throws InterruptedException {
        VistaFernan vista = new VistaFernan();
        GestionUsuario modeloUsuario = new GestionUsuario();
        GestionEvento modeloEvento = new GestionEvento(vista);

        ControladorFernan controlador = new ControladorFernan(modeloUsuario, vista, modeloEvento);
        controlador.iniciarFernan();
    }
}
