package FernanEvents.modelo.dao.modeloDAO.interfaces;

import FernanEvents.modelo.dao.conexion.DAOManager;

import java.util.ArrayList;

public interface Amigos_ReferidosDAO {
    public boolean insert(String correoAsistente, String correoAmigo, DAOManager dao);
    public boolean delete(String correoAsistente, String correoAmigo, DAOManager dao);
    public ArrayList<String> readAllAmigos(String correoAsistente, DAOManager dao);
}
