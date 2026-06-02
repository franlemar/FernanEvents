package FernanEvents.modelo.dao.modeloDAO;

import FernanEvents.modelo.Asistente;
import FernanEvents.modelo.Evento;
import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.interfaces.Asistentes_EventoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAsistentes_EventoSQL implements Asistentes_EventoDAO {

    /**
     * Método que inserta un asistente en un evento de la base de datos de FernanEvents
     */
    public boolean insert (Asistente asistente, Evento evento, DAOManager dao){
        String sql = "INSERT INTO asistentes_evento(correo_asistente, id_evento) " +
                "VALUES (?,?);";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, asistente.getCorreo());
            ps.setInt(2, evento.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }

    /**
     * Método que actualiza la cantidad de entradas de un asistente en un evento de la base de datos de FernanEvents
     */
    public boolean update(Asistente asistente, Evento evento, DAOManager dao){
        String sql = "UPDATE asistentes_evento SET cantidad_entradas = ? WHERE correo_asistente = ? AND id_evento = ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setInt(1, asistente.getNumEntradasEvento(evento.getNombre()));
            ps.setString(2, asistente.getCorreo());
            ps.setInt(3, evento.getId());

            int filasAfectadas= ps.executeUpdate();
            return filasAfectadas >0;

        } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }

    public boolean delete(Asistente asistente, Evento evento, DAOManager dao) {
        String sql = "DELETE FROM asistentes_Evento WHERE correo_asistente = ? AND id_evento = ?;";

        try (PreparedStatement ps = dao.getConn().prepareStatement(sql)) {
            ps.setString(1, asistente.getCorreo());
            ps.setInt(2, evento.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Asistente read(Asistente asistente, Evento evento, DAOManager dao){
            String sql = "SELECT * FROM Asistentes_Evento WHERE correo_asistente = ? AND id_evento = ?;";

            try(PreparedStatement ps= dao.getConn().prepareStatement(sql)){
                ps.setString(1, asistente.getCorreo());
                ps.setInt(2, evento.getId());

                try(ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        int cantidadEntradas = rs.getInt("cantidad_entradas");
                        asistente.registraCompraEntrada(evento.getNombre(), cantidadEntradas);
                    }
                }
                return asistente;
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }
}
