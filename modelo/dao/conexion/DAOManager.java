package FernanEvents.modelo.dao.conexion;

import FernanEvents.modelo.utilidades.GestorProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOManager {

    private Connection conn;
    private static DAOManager singleton;
    private static final String RUTA_CONFIG = "datosJSON/configuracion.properties";

    private DAOManager() {
        this.conn = null;
    }

    /**
     * Método que funciona a modo de "constructor" público. Comprueba si el atributo singlenton ya tiene valor. Si no lo tiene, crea la conexión y el objeto.
     * Si lo tiene, devuelve null para que no se creen más objetos de la clase DAOManager.
     */
    public static DAOManager getSingletonInstance(){
        if (singleton == null) {
            singleton = new DAOManager();
            return singleton;
        }else return null;
    }

    /**
     * Método que se encarga de abrir la conexión con la BDD con los datos de configuración del fichero properties
     */
    public void open() throws SQLException, ClassNotFoundException {
        if(conn != null && !conn.isClosed()){
            return;
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        GestorProperties gestorProperties = new GestorProperties(RUTA_CONFIG);

        String url = gestorProperties.obtenerRuta("db.url");
        String user = gestorProperties.obtenerRuta("db.user");
        String password = gestorProperties.obtenerRuta("db.password");

        conn = DriverManager.getConnection(url, user, password);
    }

    /**
     * Método que devuelve la conexión con la BDD
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Método que se encarga de cerrar la conexión con la BDD
     */
    public void close() throws SQLException {
        if(conn != null && !conn.isClosed()){
            conn.close();
        }
    }

}
