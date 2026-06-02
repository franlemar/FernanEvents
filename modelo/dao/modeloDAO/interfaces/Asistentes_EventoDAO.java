package FernanEvents.modelo.dao.modeloDAO.interfaces;

import FernanEvents.modelo.Asistente;
import FernanEvents.modelo.Entrada;
import FernanEvents.modelo.Evento;
import FernanEvents.modelo.dao.conexion.DAOManager;

public interface Asistentes_EventoDAO {
    public boolean insert(Asistente asistente, Evento evento, DAOManager dao);
    public boolean update(Asistente asistente, Evento evento, DAOManager dao);
    public boolean delete(Asistente asistente, Evento evento, DAOManager dao);
    public Asistente read(Asistente asistente, Evento evento, DAOManager dao);
}
