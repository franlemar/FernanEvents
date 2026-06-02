package FernanEvents.modelo.dao.modeloDAO.interfaces;

import FernanEvents.modelo.Entrada;
import FernanEvents.modelo.dao.conexion.DAOManager;

public interface EntradaDAO {
    public boolean insert(Entrada entrada, DAOManager dao);
    public boolean update(Entrada entrada, DAOManager dao);
    public boolean delete(Entrada entrada, DAOManager dao);
    public Entrada read(int id, DAOManager dao);
}
