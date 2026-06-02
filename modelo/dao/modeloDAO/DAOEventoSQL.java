package FernanEvents.modelo.dao.modeloDAO;

import FernanEvents.modelo.CategoriaEvento;
import FernanEvents.modelo.Evento;
import FernanEvents.modelo.Organizador;
import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.interfaces.EventoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOEventoSQL implements EventoDAO {
    /**
     * Método que inserta un nuevo evento en la base de datos de FernanEvents
     */
    public boolean insert(Evento evento, DAOManager dao) {
        String sql = "INSERT INTO Evento(nombre, descripcion, categoria, fecha, aforo, personas_inscritas, " +
                "correo_organizador) VALUES(?, ?, ?, ?, ?, ?, ?);";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getDescripcion());
            ps.setString(3, evento.getCategoria().name());
            ps.setObject(4, evento.getFecha());
            ps.setInt(5, evento.getAforo());
            ps.setInt(6, evento.getPersonasInscritas());
            ps.setString(7, evento.getOrganizador().getCorreo());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que actualiza los datos de un evento de la base de datos de FernanEvents
     */
    public boolean update(Evento evento, DAOManager dao) {
        String sql = "UPDATE Evento SET nombre = ?, descripcion = ?, categoria = ?, fecha = ?, aforo = ?, " +
                "personas_inscritas = ?, correo_organizador = ? WHERE id = ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getDescripcion());
            ps.setString(3, evento.getCategoria().name());
            ps.setObject(4, evento.getFecha());
            ps.setInt(5, evento.getAforo());
            ps.setInt(6, evento.getPersonasInscritas());
            ps.setString(7, evento.getOrganizador().getCorreo());
            ps.setInt(8, evento.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Método que elimina un evento de la base de datos de FernanEvents haciendo uso de su id
     */
    public boolean delete(Evento evento, DAOManager dao) {
        String sql = "DELETE FROM Evento WHERE id = ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setInt(1, evento.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que devuelve un evento con su información correspondiente
     */
    public Evento read(int idEvento, DAOManager dao) {
        String sql = "SELECT * FROM Evento WHERE id = ?;";
        Evento eventoLeido = null;

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setInt(1, idEvento);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    String nombreCategoria = rs.getString("categoria");
                    LocalDate fecha = rs.getObject("fecha", LocalDate.class);
                    int aforo = rs.getInt("aforo");
                    int personasInscritas = rs.getInt("personas_inscritas");
                    String correoOrganizador = rs.getString("correo_organizador");

                    CategoriaEvento categoria = CategoriaEvento.valueOf(nombreCategoria);

                    DAOUsuarioSQL usuarioDAO = new DAOUsuarioSQL();
                    Organizador organizador = (Organizador) usuarioDAO.read(correoOrganizador, dao);

                    eventoLeido = new Evento(nombre, descripcion, categoria, fecha, aforo, personasInscritas);
                    eventoLeido.setOrganizador(organizador);
                }
            }
            return eventoLeido;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que devuelve todos los eventos que existen en la base de datos de FernanEvents
     */
    public ArrayList<Evento> readAll(DAOManager dao){
        String sql = "SELECT * FROM Evento;";
        ArrayList<Evento> eventos = new ArrayList<>();

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                DAOUsuarioSQL usuarioDAO = new DAOUsuarioSQL();

                while(rs.next()){
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    String nombreCategoria = rs.getString("categoria");
                    LocalDate fecha = rs.getObject("fecha", LocalDate.class);
                    int aforo = rs.getInt("aforo");
                    int personasInscritas = rs.getInt("personas_inscritas");
                    String correoOrganizador = rs.getString("correo_organizador");

                    CategoriaEvento categoria = CategoriaEvento.valueOf(nombreCategoria);

                    Organizador organizador = (Organizador) usuarioDAO.read(correoOrganizador, dao);
                    Evento evento = new Evento(nombre, descripcion, categoria, fecha, aforo, personasInscritas);
                    evento.setOrganizador(organizador);

                    eventos.add(evento);
                }
            }
            return eventos;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}
