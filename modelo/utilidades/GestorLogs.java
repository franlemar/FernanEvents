package FernanEvents.modelo.utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestorLogs {

    private String ruta_Archivo;

    public GestorLogs(String ruta){
        this.ruta_Archivo = ruta;
    }

    /**
     * Crea un registro en el archivo de logs en función de la acción realizada en FernanEvents, con el nombre
     * de usuario que lo realiza y la fecha y hora del momento exacto
     */
    public void registrar(String accion, String nombreUsuario){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaBienFormateada = ahora.format(formato);

        String lineaLog = String.format("(\"%s\"; %s; %s)", accion, nombreUsuario, fechaBienFormateada);

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.ruta_Archivo, true));
            bw.write(lineaLog);
            bw.newLine();
            bw.close();

        }catch (IOException e){
            System.out.println("Se ha producido un error al escribir en el archivo de logs");
            e.printStackTrace();
        }
    }
}
