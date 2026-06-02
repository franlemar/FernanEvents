package FernanEvents.modelo.dao.modeloDAO;

import FernanEvents.modelo.CategoriaEntrada;
import FernanEvents.modelo.Entrada;
import FernanEvents.modelo.Usuario;
import FernanEvents.modelo.dao.conexion.DAOManager;
import FernanEvents.modelo.dao.modeloDAO.interfaces.EntradaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOEntradaSQL implements EntradaDAO {

    /**
     * Método que inserta una entrada en la base de datos de FernanEvents
     */
    public boolean insert(Entrada entrada, DAOManager dao) {
        String sql = "INSERT INTO entrada(categoria, precio, cantidadDisponible) VALUES (?,?,?);";

        try (PreparedStatement ps = dao.getConn().prepareStatement(sql)) {
            ps.setString(1, entrada.getCategoria().name());
            ps.setFloat(2, entrada.getPrecio());
            ps.setInt(3, entrada.getCantidadDisponible());

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
        String sql = "UPDATE entrada SET id= ?, id_evento= ?, categoria= ?, precio= ?, cantidad_disponible= ? " +
                "WHERE id= ?;";

        try(PreparedStatement ps = dao.getConn().prepareStatement(sql)){
            ps.setInt(1, entrada.getId());
            ps.setInt(2, entrada.getId_evento());
            ps.setString(3, entrada.getCategoria().name());
            ps.setFloat(4, entrada.getPrecio());
            ps.setInt(5, entrada.getCantidadDisponible());
            ps.setInt(6, entrada.getId());

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
        String sql = "DELETE FROM entrada WHERE id = ?;";

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

}
