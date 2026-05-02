package FernanEvents.modelo.utilidades;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class GestorProperties {

    private String rutaArchivo;

    public GestorProperties(String rutaArchivo){
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Método que se encarga de obtener la última fecha de conexión a FernanEvents de un usuario
     */
    public String obtenerUltimoLogin(String correoUsuario){
        Properties properties = new Properties();
        try{
            FileInputStream fis = new FileInputStream(rutaArchivo);
            properties.load(fis);
            return properties.getProperty(correoUsuario);

        }catch (IOException e){
            return null;
        }
    }

    public void actualizaUltimoLogin(String correoUsuario, String fechaFormateada){
        Properties properties = new Properties();
        try{
            FileInputStream fis = new FileInputStream(rutaArchivo);
            properties.load(fis);

        }catch (IOException e){}

        try{
            FileOutputStream fos = new FileOutputStream(rutaArchivo);
            properties.setProperty(correoUsuario, fechaFormateada);
            properties.store(fos, "Registro de configuración y últimos logins realizados por usuarios");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registra el acceso de un usuario a la plataforma dándole formato a la fecha a registrar
     */
    public void registrarAccesoUsuario(String correo) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        String fechaFormateada = ahora.format(formato);

        actualizaUltimoLogin(correo, fechaFormateada);
    }

    /**
     * Obtiene la ruta de un archivo según la clave especificada en el properties
     */
    public String obtenerRuta(String clave) {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(rutaArchivo);
            properties.load(fis);
            return properties.getProperty(clave);

        } catch (IOException e) {
            System.out.println("Error al cargar la ruta");
            return null;
        }
    }

    /**
     * Comprueba si el acceso para invitados está en "true" o "false"
     */
    public boolean accesoInvitadoActivo() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(rutaArchivo);
            properties.load(fis);
            String valor = properties.getProperty("acceso.invitado", "false");
            return Boolean.parseBoolean(valor);

        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Devuelve todas las entradas del fichero de configuración
     */
    public Properties obtenerTodas() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(rutaArchivo);
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Error al leer el fichero de configuración");
        }
        return properties;
    }
}
