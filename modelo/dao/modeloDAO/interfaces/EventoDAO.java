package FernanEvents.modelo.dao.modeloDAO.interfaces;

import FernanEvents.modelo.Evento;
import FernanEvents.modelo.dao.conexion.DAOManager;

public interface EventoDAO {
    public boolean insert(Evento evento, DAOManager dao);
    public boolean update(Evento evento, DAOManager dao);
    public boolean delete(Evento evento, DAOManager dao);
    public Evento read(int idEvento, DAOManager dao);
}
