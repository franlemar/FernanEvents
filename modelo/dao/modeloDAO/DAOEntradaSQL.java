package FernanEvents.modelo.dao.modeloDAO;

import FernanEvents.modelo.CategoriaEntrada;
import FernanEvents.modelo.Entrada;
import FernanEvents.modelo.Usuario;
import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.interfaces.EntradaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEntradaSQL implements EntradaDAO {

    /**
     * Método que inserta una entrada en la base de datos de FernanEvents
     */
    public boolean insert(Entrada entrada, DAOManager dao) {
        String sql = "INSERT INTO Entrada(id_evento, categoria, precio, cantidadDisponible) VALUES (?,?,?,?);";

        try (PreparedStatement ps = dao.getConn().prepareStatement(sql)) {
            ps.setInt(1, entrada.getId_evento());
            ps.setString(2, entrada.getCategoria().name());
            ps.setFloat(3, entrada.getPrecio());
            ps.setInt(4, entrada.getCantidadDisponible());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            //mensaje vista error de insercion de datos en controlador
            e.printStackTrace();
            return false;
        }
    }

    /**
    * Método que actualiza las entradas de un usuario de la base de datos de FernanEvents
    */
    public boolean update(Entrada entrada, DAOManager dao){
        String sql = "UPDATE Entrada SET id_evento= ?, categoria= ?, precio= ?, cantidadDisponible= ? " +
                "WHERE id= ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setInt(1, entrada.getId_evento());
            ps.setString(2, entrada.getCategoria().name());
            ps.setFloat(3, entrada.getPrecio());
            ps.setInt(4, entrada.getCantidadDisponible());
            ps.setInt(5, entrada.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (SQLException e){
            //mensaje vista error de actualizacion de datos en controlador
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que elimina las entradas de un usuario de la base de datos de FernanEvents
     */
    public boolean delete (Entrada entrada, DAOManager dao){
        String sql = "DELETE FROM Entrada WHERE id = ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setInt(1, entrada.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (SQLException e){
            //mensaje vista error de eliminacion de datos en controlador
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que devuelve una entrada con su información correspondiente de la base de datos de FernanEvents
     */
    public Entrada read (Entrada entrada, DAOManager dao){
        String sql = "SELECT * FROM entrada WHERE id = ?;";
        Entrada entradaLeida = null;

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setInt(1, entrada.getId());

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int id_evento = rs.getInt("id_evento");
                    String nombreCategoria = rs.getString("categoria");
                    float precio = rs.getFloat("precio");
                    int cantidadDisponible = rs.getInt("cantidadDisponible");

                    CategoriaEntrada categoria = CategoriaEntrada.valueOf(nombreCategoria);

                    entradaLeida = new Entrada(categoria, precio, cantidadDisponible);
                    entradaLeida.setId(entrada.getId());
                    entradaLeida.setId_evento(id_evento);
                }
            }

            return entradaLeida;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que devuelve un ArrayList con las entradas que tiene un evento dentro de FernanEvents
     */
    public ArrayList<Entrada> readEntradasPorEvento(int idEvento, DAOManager dao) {
        String sql = "SELECT * FROM Entrada WHERE id_evento = ?;";
        ArrayList<Entrada> entradas = new ArrayList<>();

        try (PreparedStatement ps = dao.getConn().prepareStatement(sql)) {
            ps.setInt(1, idEvento);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idEntrada = rs.getInt("id");
                    String nombreCategoria = rs.getString("categoria");
                    float precio = rs.getFloat("precio");
                    int cantidadDisponible = rs.getInt("cantidadDisponible");

                    CategoriaEntrada categoria = CategoriaEntrada.valueOf(nombreCategoria);
                    Entrada entrada = new Entrada(categoria, precio, cantidadDisponible);

                    entrada.setId(idEntrada);
                    entrada.setId_evento(idEvento);

                    entradas.add(entrada);
                }
            }
            return entradas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
