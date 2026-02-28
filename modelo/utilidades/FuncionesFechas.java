package FernanEvents.modelo.utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FuncionesFechas {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_SALIDA = DateTimeFormatter.ofPattern("dd 'de' MMMM yyyy - HH:mm");

    public static LocalDate convertirStringEnFecha(String fechaString){
        return LocalDate.parse(fechaString, FORMATO_FECHA);
    }

    public static String convertirLocalDateString(LocalDate fecha){
        return fecha.format(FORMATO_FECHA);
    }

    public static boolean fechaHoraAnterior(LocalDateTime fechaHora1, LocalDateTime fechaHora2){
        if (fechaHora1.isBefore(fechaHora2)) {return true;}
        return false;
    }

    public static long segundosRestantes(LocalDateTime fecha){
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), fecha);
    }

    public static long minutosRestantes(LocalDateTime fecha){
        return ChronoUnit.MINUTES.between(LocalDateTime.now(), fecha);
    }

    public static long horasRestantes(LocalDateTime fecha){
        return ChronoUnit.HOURS.between(LocalDateTime.now(), fecha);
    }

    public static long diasRestantes(LocalDate fecha){
        return ChronoUnit.DAYS.between(LocalDate.now(), fecha);
    }

    // funciones adicionales que nos pueden venir bien
    //cuenta atrás hasta el evento
    public static long diasHastaEvento(LocalDate fechaEvento){
        return ChronoUnit.DAYS.between(LocalDateTime.now(), fechaEvento);
    }
}
