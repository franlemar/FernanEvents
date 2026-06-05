package FernanEvents.modelo.dao.modeloDAO;

import FernanEvents.modelo.Asistente;
import FernanEvents.modelo.Evento;
import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.interfaces.Asistentes_EventoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DAOAsistentes_EventoSQL implements Asistentes_EventoDAO {

    /**
     * Método que inserta un asistente en un evento de la base de datos de FernanEvents
     */
    public boolean insert (Asistente asistente, Evento evento, DAOManager dao){
        String sql = "INSERT INTO Asistentes_evento(correo_asistente, id_evento, cantidad_entradas) " +
                "VALUES (?, ?, ?);";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, asistente.getCorreo());
            ps.setInt(2, evento.getId());
            ps.setInt(3, asistente.getNumEntradasEvento(evento.getNombre()));

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
        String sql = "UPDATE Asistentes_evento SET cantidad_entradas = ? WHERE correo_asistente = ? AND id_evento = ?;";

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

    /**
     * Método que elimina la inscripción de un asistente a un evento en la base de datos de FernanEvents
     */
    public boolean delete(Asistente asistente, Evento evento, DAOManager dao) {
        String sql = "DELETE FROM Asistentes_Evento WHERE correo_asistente = ? AND id_evento = ?;";

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

    /**
     * Método que comprueba si existe la inscripción de un asistente a un evento en la base de datos de FernanEvents
     */
    public Asistente read(Asistente asistente, Evento evento, DAOManager dao){
        String sql = "SELECT * FROM Asistentes_Evento WHERE correo_asistente = ? AND id_evento = ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, asistente.getCorreo());
            ps.setInt(2, evento.getId());

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return asistente;
                }
            }
            return null;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que devuelve un HashMap con todos los eventos a los que está inscrito un asistente y el número de entradas de cada uno
     */
    public HashMap<String, Integer> readAllInscripcionesPorAsistente(String correoAsistente, DAOManager dao) {
        HashMap<String, Integer> inscripciones = new HashMap<>();
        String sql = "SELECT e.nombre, ae.cantidad_entradas FROM Asistentes_evento ae " +
                "JOIN Evento e ON ae.id_evento = e.id " +
                "WHERE ae.correo_asistente = ?;";

        try (PreparedStatement ps = dao.getConn().prepareStatement(sql)) {
            ps.setString(1, correoAsistente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombreEvento = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad_entradas");

                    inscripciones.put(nombreEvento, cantidad);
                }
            }
            return inscripciones;

        } catch (SQLException e) {
            e.printStackTrace();
            return inscripciones;
        }
    }
}
