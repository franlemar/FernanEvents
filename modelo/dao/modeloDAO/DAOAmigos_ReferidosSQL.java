package FernanEvents.modelo.dao.modeloDAO;

import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.interfaces.Amigos_ReferidosDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAmigos_ReferidosSQL implements Amigos_ReferidosDAO {

    /**
     * Método que inserta un nuevo correo de amigo referenciado en FernanEvents, junto al correo del asistente que le invita a unirse
     */
    public boolean insert(String correoAsistente, String correoAmigo, DAOManager dao) {
        String sql = "INSERT INTO Amigos_referidos(correo_asistente, correo_amigo) VALUES(?, ?);";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, correoAsistente);
            ps.setString(2, correoAmigo);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que elimina un correo de amigo referenciado en FernanEvents, junto al correo del asistente que le invitó a unirse
     */
    public boolean delete(String correoAsistente, String correoAmigo, DAOManager dao) {
        String sql = "DELETE FROM Amigos_referidos WHERE correo_asistente = ? AND correo_amigo = ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, correoAsistente);
            ps.setString(2, correoAmigo);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> readAllAmigos(String correoAsistente, DAOManager dao) {
        String sql = "SELECT correo_amigo FROM Amigos_referidos WHERE correo_asistente = ?;";
        ArrayList<String> amigosReferidos = new ArrayList<>();

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, correoAsistente);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    String correoAmigo = rs.getString("correo_amigo");
                    amigosReferidos.add(correoAmigo);
                }
            }
            return amigosReferidos;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
