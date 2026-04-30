package FernanEvents;

import FernanEvents.controlador.ControladorFernan;
import FernanEvents.modelo.Evento;
import FernanEvents.modelo.GestionEvento;
import FernanEvents.modelo.GestionUsuario;
import FernanEvents.modelo.Usuario;
import FernanEvents.modelo.utilidades.PersistenciaJSON;
import FernanEvents.vista.VistaFernan;

import java.util.ArrayList;

public class FernanEventsApp {
    static void main(String[] args) throws InterruptedException {
        VistaFernan vista = new VistaFernan();
        GestionUsuario modeloUsuario = new GestionUsuario();
        GestionEvento modeloEvento = new GestionEvento(vista);

        // ----Cargar datos ----
        ArrayList<Usuario> usuariosGuardados = PersistenciaJSON.cargarUsuarios();
        if (usuariosGuardados.isEmpty()) {
            modeloUsuario.cargarUsuariosPredefinidos();
        } else {
            for (Usuario u : usuariosGuardados) modeloUsuario.aniadirUsuario(u);
        }

        ArrayList<Evento> eventosGuardados = PersistenciaJSON.cargarEventos();
        for (Evento e : eventosGuardados) modeloEvento.aniadirEvento(e);

        // -----Arrancar la app-----
        ControladorFernan controlador = new ControladorFernan(modeloUsuario, vista, modeloEvento);
        controlador.iniciarFernan();

        //-----Guardar datos al salir----
        PersistenciaJSON.guardarUsuarios(modeloUsuario.getUsuarios());
        PersistenciaJSON.guardarEventos(modeloEvento.getEventos());
    }
}
