package FernanEvents.modelo.dao.modeloDAO;

import FernanEvents.modelo.*;
import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.interfaces.UsuarioDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOUsuarioSQL implements UsuarioDAO {

    /**
     * Método que inserta un nuevo usuario en la base de datos de FernanEvents
     */
    public boolean insert(Usuario usuario, DAOManager dao) {
        String sql = "INSERT INTO Usuario(nombre, correo, password, rol, saldo, bloqueado) VALUES(?, ?, ?, ?, ?, ?);";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol().name());
            ps.setFloat(5, usuario.getSaldo());
            ps.setBoolean(6, usuario.isBloqueado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;

        }catch (SQLException e){
            //mensaje vista error de insercion de datos en controlador
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que actualiza los datos de un usuario de la base de datos de FernanEvents
     */
    public boolean update(Usuario usuario, DAOManager dao) {
        String sql = "UPDATE Usuario SET nombre = ?, correo = ?, password = ?, rol = ?, saldo = ?, bloqueado = ? " +
                "WHERE id = ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol().name());
            ps.setFloat(5, usuario.getSaldo());
            ps.setBoolean(6, usuario.isBloqueado());
            ps.setInt(7, usuario.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (SQLException e){
            //mensaje vista error de actualizacion de datos en controlador
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Método que elimina un usuario de la base de datos de FernanEvents haciendo uso de su correo electrónico
     */
    public boolean delete(Usuario usuario, DAOManager dao) {
        String sql = "DELETE FROM Usuario WHERE correo = ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, usuario.getCorreo());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;


        }catch (SQLException e){
            //mensaje vista error de eliminacion de datos en controlador
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que devuelve un usuario con su información correspondiente. Para ello, utiliza el correo del usuario
     */
    public Usuario read(String correo, DAOManager dao) {
        String sql = "SELECT * FROM Usuario WHERE correo = ?;";
        Usuario usuarioLeido = null;

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, correo);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    usuarioLeido = creaUsuario(rs);
                }
            }
            return usuarioLeido;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que devuelve un usuario con su información correspondiente. Para ello, utiliza el id del usuario
     */
    public Usuario read(int id, DAOManager dao) {
        String sql = "SELECT * FROM Usuario WHERE id = ?;";
        Usuario usuarioLeido = null;

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    usuarioLeido = creaUsuario(rs);
                }
            }
            return usuarioLeido;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que devuelve todos los usuarios que existen en la base de datos de FernanEvents
     */
    public ArrayList<Usuario> readAll(DAOManager dao) {
        String sql = "SELECT * FROM Usuario;";
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement ps = dao.getConn().prepareStatement(sql)) {
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuarioLeido = creaUsuario(rs);
                    if(usuarioLeido != null){
                        usuarios.add(usuarioLeido);
                    }
                }
            }
            return usuarios;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Método helper para crear un usuario con todos los parámetros necesarios.
     * Hace uso del ResultSet para obtener la información necesaria.
     */
    private Usuario creaUsuario(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String correo = rs.getString("correo");
        String password = rs.getString("password");
        String nombreRol = rs.getString("rol");
        float saldo = rs.getFloat("saldo");
        boolean bloqueado = rs.getBoolean("bloqueado");

        Rol rol = Rol.valueOf(nombreRol);
        Usuario usuario = null;

        switch (rol) {
            case ADMINISTRADOR:
                usuario = new Administrador(nombre, correo, password);
                break;
            case ORGANIZADOR:
                usuario = new Organizador(nombre, correo, password);
                break;
            case ASISTENTE:
                usuario = new Asistente(nombre, correo, password);
                break;
        }

        if (usuario != null) {
            usuario.setId(id);
            usuario.setSaldo(saldo);
            usuario.setBloqueado(bloqueado);
        }

        return usuario;
    }
}
