package FernanEvents.vista;

public class VistaFernan{

    private static Estilos estilo = new Estilos();
    //Métodos
    /**
     * Muestra el primer menú que aparece nada más iniciar FernanEvents donde la persona elige si desea iniciar sesión con sus credenciales o registrarse como un nuevo usuario en la plataforma
     */
    public static void menuLogin() {
        System.out.println(estilo.getPASTEL_BLUE() + "Bienvenido a FernanEvents. Seleccione qué desea hacer: " + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_BLUE() + "1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println(estilo.getNEON_PINK() + "3. Salir" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione una opción: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra el menú principal cuando se inicia el programa enseñando los distintos tipos de perfiles con los que puede interactuar el usuario
     */
    public static void menuOpcionesUsuario() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + estilo.getITALIC() + "✦ ELIGE TU TIPO DE USUARIO ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Administrador");
        System.out.println("2. Organizador");
        System.out.println("3. Asistente");
        System.out.println(estilo.getNEON_PINK() + "4. Salir" + estilo.getANSI_RESET());

        System.out.println(estilo.getSILVER() + "────────────────────────────────────────────" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione una opción: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra el menú de opciones disponibles que tiene el usuario cuando accede a FernanEvents como administrador
     */
    public static void menuAdministrador() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + estilo.getUNDERLINE() + "✦ MENÚ ADMINISTRADOR ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Panel de Control");
        System.out.println("2. Eventos");
        System.out.println("3. Cartera digital");
        System.out.println("4. Configuración");
        System.out.println(estilo.getNEON_PINK() + "5. Cerrar sesión" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione la opción deseada: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra el menú de opciones disponibles que tiene el usuario cuando accede a FernanEvents como organizador
     */
    public static void menuOrganizador() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + estilo.getUNDERLINE() + "✦ MENÚ ORGANIZADOR ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Mis eventos");
        System.out.println("2. Cartera digital");
        System.out.println("3. Configuración");
        System.out.println(estilo.getNEON_PINK() + "4. Cerrar sesión" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione la opción deseada: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra un menú que aparece cuando se intenta iniciar sesión como asistente, permitiendo al usuario elegir qué tipo de asistente es.
     */
    public static void menuTipoAsistente() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + "✦ ¿QUÉ TIPO DE ASISTENTE ERES? ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Asistente 1");
        System.out.println("2. Asistente 2");
        System.out.println(estilo.getNEON_PINK() + "3. Volver atrás" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione una opción: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra el menú de opciones disponibles que tiene el usuario cuando accede a FernanEvents como asistente
     */
    public static void menuAsistente() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + estilo.getUNDERLINE() + "✦ MENÚ ASISTENTE ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Mis eventos");
        System.out.println("2. Eventos");
        System.out.println("3. Cartera digital");
        System.out.println("4. Invita a un amigo");
        System.out.println("5. Configuración");
        System.out.println(estilo.getNEON_PINK() + "6. Cerrar sesión" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione la opción deseada: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra el menú de panel de control donde el administrador puede elegir sobre qué perfil trabajar pudiendo cambiar el nombre de usuario y contraseña de dicho perfil
     */
    public static void OpcionesUsuariosPanelSoloAdmin() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + "✦ PANEL DE CONTROL ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Organizador");
        System.out.println("2. Asistente 1");
        System.out.println("3. Asistente 2" + estilo.getANSI_RESET());
        System.out.println(estilo.getNEON_PINK() + "4. Volver atrás" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione una opción: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra el menú de cartera digital donde el usuario puede consultar el saldo que tiene actualmente, así como añadir o retirar saldo de su cartera
     */
    public static void menucarteraDigital() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + estilo.getUNDERLINE() + "✦ CARTERA DIGITAL ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Mostrar saldo actual");
        System.out.println("2. Añadir saldo");
        System.out.println("3. Retirar saldo");
        System.out.println(estilo.getNEON_PINK() + "4. Volver atrás" + estilo.getANSI_RESET());
    }

    /**
     * Muestra el menú de panel de configuración para el organizador y los asistente. Desde aquí pueden cambiar el nombre de su usuario y su contraseña de acceso
     */
    public static void menuConfiguracionGeneral() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + estilo.getUNDERLINE() + "✦ PANEL DE CONFIGURACIÓN ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Cambiar nombre de usuario");
        System.out.println("2. Cambiar contraseña" + estilo.getANSI_RESET());
        System.out.println(estilo.getNEON_PINK() + "3. Volver atrás" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_GREEN() + "Elige la opción que desea realizar: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra el menú de eventos para el perfil del organizador. Desde aquí, el organizador puede gestionar los eventos de FernanEvents pudiendo crear un evento nuevo, modificar un evento ya existente o eliminar un evento.
     */
    public static void menuOrganizadorEventos() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + "✦ MENÚ EVENTOS (ORGANIZADOR) ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Eventos disponibles");
        System.out.println("2. Crear evento");
        System.out.println("3. Modificar evento");
        System.out.println("4. Eliminar evento");
        System.out.println(estilo.getNEON_PINK() + "5. Volver atrás" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione la opción deseada: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra un menú con los tipos de detalles de los que cosnta un evento. Aparece cuando el organizador quiere modiciar un evento que ya se ha creado anteriormente.
     */
    public static void datosEventos() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + "✦ DATOS DEL EVENTO ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Nombre del evento");
        System.out.println("2. Descripción del evento");
        System.out.println("3. Categoría del evento");
        System.out.println("4. Fecha y hora");
        System.out.println("5. Aforo");
        System.out.println("6. Número de inscritos");
        System.out.println("7. Tipos de entrada");
        System.out.println(estilo.getNEON_PINK() + "8. Guardar datos y volver" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_GREEN() + "Elige el dato que deseas crear/modificar: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra un menú desde donde los asistentes eligen el evento al que desean inscribirse
     */
    public static void menuInscripcionAsistente() {
        System.out.println(estilo.getPASTEL_BLUE() + "¿En qué evento quieres inscribirte?" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_BLUE() + "1. Evento 1");
        System.out.println("2. Evento 2");
        System.out.println("3. Evento 3");
        System.out.println(estilo.getNEON_PINK() + "4. Volver atrás" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_GREEN() + "Elige la opción del evento correspondiente: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra un evento al que se hayan inscrito los asistentes con una vista previa del nombre, categoría y fecha del mismo
     */
    public static void mostrarEventoInscrito(String nombre, String categoria, String fecha, int numeroEvento) {
        System.out.println(estilo.getPASTEL_PURPLE() + "===Evento " + numeroEvento + "===" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_BLUE() + "Nombre: " + estilo.getANSI_RESET() + nombre);
        System.out.println(estilo.getPASTEL_BLUE() + "Categoria: " + estilo.getANSI_RESET() + categoria);
        System.out.println(estilo.getPASTEL_BLUE() + "Fecha:" + estilo.getANSI_RESET() + fecha);
        System.out.println();
    }

    /**
     * Muestra un menú donde el asistente elige qué tipo de entrada desea comprar a la hora de inscribirse en un evento
     */
    public static void menuTipoEntrada() {
        System.out.println(estilo.getPASTEL_BLUE() + "¿Qué tipo de entrada quieres comprar?" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_BLUE() + "1. Entrada general");
        System.out.println("2. Entrada VIP");
        System.out.println("3. Entrada infantil");
        System.out.println(estilo.getNEON_PINK() + "4. Volver atrás" + estilo.getANSI_RESET());
        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione una opción: " + estilo.getANSI_RESET());
    }

    /**
     * Muestra un menú desde donde los asistentes pueden comprobar una lista de amigos a los que han invitado a FernanEvents, así como enviar correos electrónicos con invitaciones para otros nuevos amigos
     */
    public static void menuInvitarAmigo() {
        System.out.println(estilo.getPASTEL_PURPLE() + estilo.getBOLD() + "✦ INVITA A TUS AMIGOS ✦" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_BLUE() + "1. Listado de referidos");
        System.out.println("2. Añadir nuevo amigo (email)");
        System.out.println(estilo.getNEON_PINK() + "3. Volver atrás" + estilo.getANSI_RESET());

        System.out.println(estilo.getPASTEL_GREEN() + "Seleccione la opción deseada: " + estilo.getANSI_RESET());
    }

    /**
     * Función que se encarga de mostrar de forma visual en consola el cierre de sesión de un perfil de usuario de FernanEvents. Aquí "thread.sleep" se encarga de hacer que aparezcan los puntos progresivamente con un tiempo ya predefinido.
     */
    public static void cerrarSesion(String rolUsuario) throws InterruptedException {
        System.out.print(estilo.getANSI_RED() + estilo.getWARNING() + estilo.getBOLD() +" Cerrando sesión como " + rolUsuario + estilo.getANSI_RESET() );
        for (int i = 1; i <=3 ; i++) {
            System.out.print(estilo.getANSI_RED() + "."+ estilo.getANSI_RESET());
            Thread.sleep(1400);
        }
        System.out.println("\n");
    }

    /**
     * Función que muestra una barra de progreso interactiva a la hora de iniciar el programa
     */
    public static void iniciarProgramaBarraProgreso() throws InterruptedException {
        int total = 20;
        char[] spinner = {'|', '/', '-', '\\'};
        int delay = 100;

        System.out.println();

        for (int i = 0; i <= total; i++) {
            int porcentaje = (i * 100) / total;

            StringBuilder barra = new StringBuilder();
            for (int j = 0; j < i; j++) {
                barra.append(estilo.getPASTEL_PURPLE() + estilo.getBLOCK() + estilo.getANSI_RESET());
            }
            for (int j = i; j < total; j++) {
                barra.append(" ");
            }

            System.out.print("\r" + estilo.getBOLD() + estilo.getPASTEL_BLUE() + "⏳ INICIANDO FERNANEVENTS " + estilo.getANSI_RESET() + estilo.getSILVER() + spinner[i % spinner.length] + " " + estilo.getANSI_RESET() + barra + " " + porcentaje + "%");
            Thread.sleep(delay);
        }

        Thread.sleep(1000);

        System.out.print("\r");
        System.out.print(" ".repeat(80));
        System.out.print("\r");

        System.out.println(estilo.getNEON_GREEN() + estilo.getBOLD() + "✔ Programa ejecutado correctamente." + estilo.getANSI_RESET());
        System.out.println();
    }

    /**
     * Muestra de forma muy visual el encabezado de los eventos que se han creado en FernanEvents. Dentro de la figura se puede ver el nombre del evento, la categoría a la que pertenece y la fecha para la que está programada.
     */
    public static void mostrarEventoTabla(String nombre, String categoria, String fecha) {
        System.out.println(estilo.getPASTEL_PURPLE() + "╔════════════════════════════════════════════════════╗");
        System.out.printf(estilo.getPASTEL_PURPLE() + "║ " + estilo.getPASTEL_BLUE() + "Evento" + estilo.getPASTEL_PURPLE() + ": %-" + (50 - "Evento".length() - 2) + "s ║%n", nombre);
        System.out.println(estilo.getPASTEL_PURPLE() + "╟────────────────────────────────────────────────────╢");
        System.out.printf(estilo.getPASTEL_PURPLE() + "║ " + estilo.getPASTEL_BLUE() + "Categoría" + estilo.getPASTEL_PURPLE() + ": %-" + (50 - "Categoría".length() - 2) + "s ║%n", categoria);
        System.out.printf(estilo.getPASTEL_PURPLE() + "║ " + estilo.getPASTEL_BLUE() + "Fecha" + estilo.getPASTEL_PURPLE() + ": %-" + (50 - "Fecha".length() - 2) + "s ║%n", fecha);
        System.out.println(estilo.getPASTEL_PURPLE() + "╚════════════════════════════════════════════════════╝");
    }

    /**
     * Muestra el logo de la plataforma de FernanEvents
     */
    public static void mostrarFERNANEVENTSASCII() {
        System.out.println(
                 estilo.getPASTEL_PURPLE()+
                        "███████╗███████╗██████╗ ███╗   ██╗ █████╗ ███╗   ██╗" + estilo.getPASTEL_BLUE() +
                        "███████╗██╗   ██╗███████╗███╗   ██╗████████╗███████╗\n" + estilo.getPASTEL_PURPLE() +
                        "██╔════╝██╔════╝██╔══██╗████╗  ██║██╔══██╗████╗  ██║" + estilo.getPASTEL_BLUE() +
                        "██╔════╝██║   ██║██╔════╝████╗  ██║╚══██╔══╝██╔════╝\n" + estilo.getPASTEL_PURPLE() +
                        "█████╗  █████╗  ██████╔╝██╔██╗ ██║███████║██╔██╗ ██║" + estilo.getPASTEL_BLUE() +
                        "█████╗  ██║   ██║█████╗  ██╔██╗ ██║   ██║   ███████╗\n" + estilo.getPASTEL_PURPLE() +
                        "██╔══╝  ██╔══╝  ██╔══██╗██║╚██╗██║██╔══██║██║╚██╗██║" + estilo.getPASTEL_BLUE() +
                        "██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║   ╚════██║\n" + estilo.getPASTEL_PURPLE() +
                        "██║     ███████╗██║  ██║██║ ╚████║██║  ██║██║ ╚████║" + estilo.getPASTEL_BLUE() +
                        "███████╗ ╚████╔╝ ███████╗██║ ╚████║   ██║   ███████║\n" + estilo.getPASTEL_PURPLE() +
                        "╚═╝     ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═══╝" + estilo.getPASTEL_BLUE() +
                        "╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝\n" + estilo.getANSI_RESET()
        );
    }

}
