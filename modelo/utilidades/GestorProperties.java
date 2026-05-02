package FernanEvents.modelo.utilidades;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
            properties.store(fos, "Registro de últimos inicios de sesión");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
