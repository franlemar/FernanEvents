package FernanEvents.modelo.dao.modeloDAO.interfaces;

import FernanEvents.modelo.Usuario;
import FernanEvents.modelo.dao.conexion.DAOManager;

public interface UsuarioDAO {
    public boolean insert(Usuario usuario, DAOManager dao);
    public boolean delete(Usuario Usuario, DAOManager dao);
    public boolean update(Usuario Usuario, DAOManager dao);
    public Usuario read(String correo, DAOManager dao);

}
