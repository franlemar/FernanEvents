package FernanEvents.modelo.utilidades;

import FernanEvents.vista.VistaFernan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class FuncionesFechas {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_SALIDA = DateTimeFormatter.ofPattern("dd 'de' MMMM yyyy - HH:mm");

    /**
     * Convierte un String en formato dd/MM/yyyy a un objeto LocalDate
     */
    public static LocalDate convertirStringEnFecha(String fechaString){
        return LocalDate.parse(fechaString, FORMATO_FECHA);
    }

    /**
     * Convierte un objeto LocalDate a String en formato dd/MM/yyyy
     */
    public static String convertirLocalDateString(LocalDate fecha){
        return fecha.format(FORMATO_FECHA);
    }
// está bien pero la siguiente está mejor expresada
//    public static boolean fechaHoraAnterior(LocalDateTime fechaHora1, LocalDateTime fechaHora2){
//        if (fechaHora1.isBefore(fechaHora2)) {return true;}
//        return false;
//    }

    /**
     * Comprueba si una fecha es anterior a la fecha actual
     */
    public static boolean esFechaAnteriorALaActual(LocalDate fecha) {
        return fecha.isBefore(LocalDate.now());
    }

    /**
     * Solicita al usuario una fecha que sea válida y también la valida
     */
    public static LocalDate pedirFechaValida(Scanner s, VistaFernan vista) {
        LocalDate fecha = null;
        while (fecha == null) {
            vista.pedirDatosEvento("Introduce la fecha (dd/MM/yyyy): ");
            String fechaStr = s.nextLine();
            try {
                fecha = LocalDate.parse(fechaStr, FORMATO_FECHA);
                if (fecha.isBefore(LocalDate.now())) {
                    vista.error("La fecha no puede ser anterior a la actual");
                    fecha = null; // reiniciar
                }
            } catch (Exception e) {
                vista.fechaNoValida();
            }
        }
        return fecha;
    }

    /**
     * Calcula los segundos restantes hasta una fecha determinada
     */
    public static long segundosRestantes(LocalDateTime fecha){
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), fecha);
    }

    /**
     * Calcula los minutos restantes hasta una fecha determinada
     */
    public static long minutosRestantes(LocalDateTime fecha){
        return ChronoUnit.MINUTES.between(LocalDateTime.now(), fecha);
    }

    /**
     * Calcula las horas restantes hasta una fecha determinada
     */
    public static long horasRestantes(LocalDateTime fecha){
        return ChronoUnit.HOURS.between(LocalDateTime.now(), fecha);
    }

    /**
     * Calcula los días restantes hasta una fecha determinada
     */
    public static long diasRestantes(LocalDate fecha){
        return ChronoUnit.DAYS.between(LocalDate.now(), fecha);
    }

    // funciones adicionales que nos pueden venir bien
    /**
     * Calcula los días restantes hasta la fecha de un evento
     */
    public static long diasHastaEvento(LocalDate fechaEvento){
        return ChronoUnit.DAYS.between(LocalDateTime.now(), fechaEvento);
    }
}
