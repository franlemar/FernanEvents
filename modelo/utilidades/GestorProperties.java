package FernanEvents.modelo.utilidades;

import java.io.File;
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

    /**
     * Método que se encarga de obtener la última fecha de conexión a FernanEvents de un usuario
     */
    public String obtenerUltimoLogin(String correoUsuario){
        Properties properties = new Properties();
        try{
            FileInputStream fis = new FileInputStream(rutaArchivo);
            properties.load(fis);
            fis.close();
            return properties.getProperty(correoUsuario);

        }catch (IOException e){
            return null;
        }
    }

    /**
     * Método que se encarga de actualizar el último login realizado para cada usuario, almacenando el correo del usuario
     *  y la fecha exacta de cuando inicia sesión en la plataforma
     */
    public void actualizaUltimoLogin(String correoUsuario, String fechaFormateada){
        Properties properties = new Properties();

        File archivo = new File(rutaArchivo);
        if(archivo.exists()){
            try{
                FileInputStream fis = new FileInputStream(rutaArchivo);
                properties.load(fis);
                fis.close();

            }catch (IOException e){
                System.out.println("ERROR: no se pudieron cargar los datos anteriores");
            }
        }
        properties.setProperty(correoUsuario, fechaFormateada);

        try{
            FileOutputStream fos = new FileOutputStream(rutaArchivo);
            properties.store(fos, "Registro de configuración y últimos logins realizados por usuarios");
            fos.close();
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
            fis.close();
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
            fis.close();
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
            fis.close();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero de configuración");
        }
        return properties;
    }
}
