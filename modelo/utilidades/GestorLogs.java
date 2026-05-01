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

    public String getRuta_Archivo() {
        return ruta_Archivo;
    }

    public void setRuta_Archivo(String ruta_Archivo) {
        this.ruta_Archivo = ruta_Archivo;
    }

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
