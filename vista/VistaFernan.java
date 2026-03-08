package FernanEvents.vista;
import FernanEvents.modelo.CategoriaEvento;
import FernanEvents.modelo.EntradasTipo;
import FernanEvents.modelo.Evento;

import java.util.Scanner;

public class VistaFernan{

    private  Estilos estilo = new Estilos();
    //Métodos
    /**
     * Muestra el primer menú que aparece nada más iniciar FernanEvents donde la persona elige si desea iniciar sesión con sus credenciales o registrarse como un nuevo usuario en la plataforma
     */
    public void menuLogin() {
        System.out.println(estilo.PASTEL_BLUE + "Bienvenido a FernanEvents. Seleccione qué desea hacer: " + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE + "1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println(estilo.NEON_PINK + "3. Salir" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_GREEN + "Seleccione una opción: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra el menú de opciones disponibles que tiene el usuario cuando accede a FernanEvents como administrador
     */
    public void menuAdministrador() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + estilo.UNDERLINE + "✦ MENÚ ADMINISTRADOR ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Panel de Control");
        System.out.println("2. Eventos");
        System.out.println("3. Cartera digital");
        System.out.println("4. Configuración");
        System.out.println(estilo.NEON_PINK + "5. Cerrar sesión" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_GREEN + "Seleccione la opción deseada: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra el menú de opciones disponibles que tiene el usuario cuando accede a FernanEvents como organizador
     */
    public void menuOrganizador() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + estilo.UNDERLINE + "✦ MENÚ ORGANIZADOR ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Mis eventos");
        System.out.println("2. Cartera digital");
        System.out.println("3. Configuración");
        System.out.println(estilo.NEON_PINK + "4. Cerrar sesión" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_GREEN + "Seleccione la opción deseada: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra el menú de opciones disponibles que tiene el usuario cuando accede a FernanEvents como asistente
     */
    public void menuAsistente() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + estilo.UNDERLINE + "✦ MENÚ ASISTENTE ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Mis eventos");
        System.out.println("2. Eventos");
        System.out.println("3. Cartera digital");
        System.out.println("4. Invita a un amigo");
        System.out.println("5. Configuración");
        System.out.println(estilo.NEON_PINK + "6. Cerrar sesión" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_GREEN + "Seleccione la opción deseada: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra el menú de panel de configuración para el organizador y los asistente. Desde aquí pueden cambiar el nombre de su usuario y su contraseña de acceso
     */
    public void menuConfiguracionGeneral() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + estilo.UNDERLINE + "✦ PANEL DE CONFIGURACIÓN ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Cambiar nombre de usuario");
        System.out.println("2. Cambiar contraseña" + estilo.ANSI_RESET);
        System.out.println(estilo.NEON_PINK + "3. Volver atrás" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_GREEN + "Elige la opción que deseas realizar: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra el menú de eventos para el perfil del organizador. Desde aquí, el organizador puede gestionar los eventos de FernanEvents pudiendo crear un evento nuevo, modificar un evento ya existente o eliminar un evento.
     */
    public void menuOrganizadorEventos() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ MENÚ EVENTOS (ORGANIZADOR) ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Eventos disponibles");
        System.out.println("2. Crear evento");
        System.out.println("3. Modificar evento");
        System.out.println("4. Eliminar evento");
        System.out.println(estilo.NEON_PINK + "5. Volver atrás" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_GREEN + "Seleccione la opción deseada: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra un menú con los tipos de detalles de los que consta un evento. Aparece cuando el organizador quiere modificar un evento que ya se ha creado anteriormente.
     */
    public void datosEventos() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ DATOS DEL EVENTO ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Nombre del evento");
        System.out.println("2. Descripción del evento");
        System.out.println("3. Categoría del evento");
        System.out.println("4. Fecha y hora");
        System.out.println("5. Aforo");
        System.out.println("6. Número de inscritos");
        System.out.println("7. Tipos de entrada");
        System.out.println(estilo.NEON_PINK + "8. Guardar datos y volver" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_GREEN + "Elige el dato que deseas crear/modificar: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra un menú desde donde los asistentes eligen el evento al que desean inscribirse
     */
    public void menuInscripcionAsistente() {
        System.out.println(estilo.PASTEL_BLUE + "¿En qué evento quieres inscribirte?" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE + "1. Evento 1");
        System.out.println("2. Evento 2");
        System.out.println("3. Evento 3");
        System.out.println(estilo.NEON_PINK + "4. Volver atrás" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_GREEN + "Elige la opción del evento correspondiente: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra un evento al que se hayan inscrito los asistentes con una vista previa del nombre, categoría y fecha del mismo
     */
    public void mostrarEventoInscrito(String nombre, String categoria, String fecha, int numeroEvento) {
        System.out.println(estilo.PASTEL_PURPLE + "===Evento " + numeroEvento + "===" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE + "Nombre: " + estilo.ANSI_RESET + nombre);
        System.out.println(estilo.PASTEL_BLUE + "Categoria: " + estilo.ANSI_RESET + categoria);
        System.out.println(estilo.PASTEL_BLUE + "Fecha:" + estilo.ANSI_RESET + fecha);
        System.out.println();
    }

    /**
     * Muestra un menú donde el asistente elige qué tipo de entrada desea comprar a la hora de inscribirse en un evento
     */
    public void menuTipoEntrada() {
        System.out.println(estilo.PASTEL_BLUE + "¿Qué tipo de entrada quieres comprar?" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE + "1. Entrada general");
        System.out.println("2. Entrada VIP");
        System.out.println("3. Entrada infantil");
        System.out.println(estilo.NEON_PINK + "4. Volver atrás" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_GREEN + "Seleccione una opción: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra un menú desde donde los asistentes pueden comprobar una lista de amigos a los que han invitado a FernanEvents, así como enviar correos electrónicos con invitaciones para otros nuevos amigos
     */
    public void menuInvitarAmigo() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ INVITA A TUS AMIGOS ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Listado de referidos");
        System.out.println("2. Añadir nuevo amigo (email)");
        System.out.println(estilo.NEON_PINK + "3. Volver atrás" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_GREEN + "Seleccione la opción deseada: " + estilo.ANSI_RESET);
    }

    /**
     * Función que se encarga de mostrar de forma visual en consola el cierre de sesión de un perfil de usuario de FernanEvents. Aquí "thread.sleep" se encarga de hacer que aparezcan los puntos progresivamente con un tiempo ya predefinido.
     */
    public void cerrarSesion(String nombreUsuario) throws InterruptedException {
        System.out.print(estilo.ANSI_RED + estilo.WARNING + estilo.BOLD +" Cerrando sesión como " + nombreUsuario + estilo.ANSI_RESET );
        for (int i = 1; i <=3 ; i++) {
            System.out.print(estilo.ANSI_RED + "."+ estilo.ANSI_RESET);
            Thread.sleep(1400);
        }
        System.out.println("\n");
    }

    /**
     * Función que muestra una barra de progreso interactiva a la hora de iniciar el programa
     */
    public void iniciarProgramaBarraProgreso() throws InterruptedException {
        int total = 20;
        char[] spinner = {'|', '/', '-', '\\'};
        int delay = 100;

        System.out.println();

        for (int i = 0; i <= total; i++) {
            int porcentaje = (i * 100) / total;

            StringBuilder barra = new StringBuilder();
            for (int j = 0; j < i; j++) {
                barra.append(estilo.PASTEL_PURPLE + estilo.BLOCK + estilo.ANSI_RESET);
            }
            for (int j = i; j < total; j++) {
                barra.append(" ");
            }

            System.out.print("\r" + estilo.BOLD + estilo.PASTEL_BLUE + "⏳ INICIANDO FERNANEVENTS " + estilo.ANSI_RESET + estilo.SILVER + spinner[i % spinner.length] + " " + estilo.ANSI_RESET + barra + " " + porcentaje + "%");
            Thread.sleep(delay);
        }

        Thread.sleep(1000);

        System.out.print("\r");
        System.out.print(" ".repeat(80));
        System.out.print("\r");

        System.out.println(estilo.NEON_GREEN + estilo.BOLD + "✔ Programa ejecutado correctamente." + estilo.ANSI_RESET);
        System.out.println();
    }

    /**
     * Muestra de forma muy visual el encabezado de los eventos que se han creado en FernanEvents. Dentro de la figura se puede ver el nombre del evento, la categoría a la que pertenece y la fecha para la que está programada.
     */
    public void mostrarEventoTabla(String nombre, String categoria, String fecha) {
        System.out.println(estilo.PASTEL_PURPLE + "╔════════════════════════════════════════════════════╗");
        System.out.printf(estilo.PASTEL_PURPLE + "║ " + estilo.PASTEL_BLUE + "Evento" + estilo.PASTEL_PURPLE + ": %-" + (50 - "Evento".length() - 2) + "s ║%n", nombre);
        System.out.println(estilo.PASTEL_PURPLE + "╟────────────────────────────────────────────────────╢");
        System.out.printf(estilo.PASTEL_PURPLE + "║ " + estilo.PASTEL_BLUE + "Categoría" + estilo.PASTEL_PURPLE + ": %-" + (50 - "Categoría".length() - 2) + "s ║%n", categoria);
        System.out.printf(estilo.PASTEL_PURPLE + "║ " + estilo.PASTEL_BLUE + "Fecha" + estilo.PASTEL_PURPLE + ": %-" + (50 - "Fecha".length() - 2) + "s ║%n", fecha);
        System.out.println(estilo.PASTEL_PURPLE + "╚════════════════════════════════════════════════════╝");
    }

    /**
     * Muestra todos los detalles de los eventos que han sido creados en FernanEvents así como un pequeño gráfico de barra horizontal donde poder ver de forma gráfica la cantidad de inscritos a los eventos en función del aforo disponible.
     */
    public void mostrarVistaDetalladaEvento(String nombre, String categoria, String fecha, String descripcion, int aforo, int inscritos) {

        System.out.println(estilo.PASTEL_PURPLE + "\n===== VISTA DETALLADA DEL EVENTO =====" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "Nombre del evento: " + estilo.ANSI_RESET + nombre);
        System.out.println(estilo.PASTEL_BLUE + "Categoría: " + estilo.ANSI_RESET + categoria);
        System.out.println(estilo.PASTEL_BLUE + "Fecha: " + estilo.ANSI_RESET + fecha);
        System.out.println(estilo.PASTEL_BLUE + "Descripción: " + estilo.ANSI_RESET + descripcion);

        int aforoRestante = aforo - inscritos;
        System.out.println(estilo.PASTEL_BLUE + "Aforo total: " + estilo.ANSI_RESET + aforo + " personas");
        System.out.println(estilo.PASTEL_BLUE + "Inscritos actualmente: " + estilo.ANSI_RESET + inscritos + " personas");
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "Aforo disponible (Total): " + estilo.ANSI_RESET + aforoRestante + " plazas libres");


        double porcentaje = 0;
        if (aforo > 0) {
            porcentaje = ((double) inscritos / aforo) * 100;
        }

        int barras = (int) (porcentaje / 5); // Máx. 20 barras

        String grafico = "";
        for (int i = 0; i < barras; i++) {
            grafico += estilo.PASTEL_PURPLE + "█" + estilo.ANSI_RESET;
        }

        System.out.println(estilo.PASTEL_BLUE + "\nGráfico de inscritos:" + estilo.ANSI_RESET);
        System.out.println("[" + grafico + estilo.ANSI_RESET + "] " + String.format("%.1f", porcentaje) + "%\n");
    }

    //MODIFICAR CUANDO TENGAMOS ENTRADAS LOS VALORES QUE LE LLEGAN POR PARÁMETRO
    public void mostrarVistaDetalladaEntradas(EntradasTipo[] tipoEntrada){
        System.out.println(estilo.PASTEL_YELLOW + "\n=== ENTRADAS DISPONIBLES ===" + estilo.ANSI_RESET);

        for (int i = 0; i < 3; i++) {
            System.out.print(estilo.PASTEL_BLUE + "Tipo: " + estilo.ANSI_RESET + tipoEntrada[i].getCategoria());
            System.out.print(" | " + estilo.PASTEL_BLUE + "Precio: " + estilo.ANSI_RESET + tipoEntrada[i].getPrecio() + " euros");

            if (tipoEntrada[i].getCantidadDisponible() <= 0) {
                System.out.println(" | " + estilo.ANSI_RED + "AGOTADAS" + estilo.ANSI_RESET);
            } else {
                System.out.println(" | " + estilo.PASTEL_GREEN + "Disponibles: " + estilo.ANSI_RESET + tipoEntrada[i].getCantidadDisponible());
            }
        }
        System.out.println(" ");
    }

    /**
     * Muestra el logo de la plataforma de FernanEvents
     */
    public void mostrarFERNANEVENTSASCII() {
        System.out.println(
                 estilo.PASTEL_PURPLE+
                        "███████╗███████╗██████╗ ███╗   ██╗ █████╗ ███╗   ██╗" + estilo.PASTEL_BLUE +
                        "███████╗██╗   ██╗███████╗███╗   ██╗████████╗███████╗\n" + estilo.PASTEL_PURPLE +
                        "██╔════╝██╔════╝██╔══██╗████╗  ██║██╔══██╗████╗  ██║" + estilo.PASTEL_BLUE +
                        "██╔════╝██║   ██║██╔════╝████╗  ██║╚══██╔══╝██╔════╝\n" + estilo.PASTEL_PURPLE +
                        "█████╗  █████╗  ██████╔╝██╔██╗ ██║███████║██╔██╗ ██║" + estilo.PASTEL_BLUE +
                        "█████╗  ██║   ██║█████╗  ██╔██╗ ██║   ██║   ███████╗\n" + estilo.PASTEL_PURPLE +
                        "██╔══╝  ██╔══╝  ██╔══██╗██║╚██╗██║██╔══██║██║╚██╗██║" + estilo.PASTEL_BLUE +
                        "██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║   ╚════██║\n" + estilo.PASTEL_PURPLE +
                        "██║     ███████╗██║  ██║██║ ╚████║██║  ██║██║ ╚████║" + estilo.PASTEL_BLUE +
                        "███████╗ ╚████╔╝ ███████╗██║ ╚████║   ██║   ███████║\n" + estilo.PASTEL_PURPLE +
                        "╚═╝     ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═══╝" + estilo.PASTEL_BLUE +
                        "╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝\n" + estilo.ANSI_RESET
        );
    }

    /**
     * Muestra un mensaje de despedida al finalizar el programa
     */
    public void mostrarDespedida(){
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "*** FIN DEL PROGRAMA ***" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE +
                "Gracias por utilizar FernanEvents. ¡Nos vemos en los escenarios! 😉" + estilo.ANSI_RESET);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.NOTIFICACIONES GENERALES.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Muestra un mensaje de confirmacion de operación exitosa
     */
    public void mensajeConfirmacion(){
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD +
                " ✅ Operación realizada correctamente" + estilo.ANSI_RESET + "\n");
    }

    /**
     * Muestra un mensaje de error en la operación
     */
    public void mensajeError(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD +
                " ❌ ERROR, no se ha podido completar la operación solicitada" + estilo.ANSI_RESET  + "\n");
    }

    /**
     * Muestra un mensaje personalizado
     */
    public void notificacion(String mensaje){
        System.out.println(mensaje);
    }

    /**
     * Muestra un mensaje indicando que la opción seleccionada no es válida
     */
    public void opcionNoValida(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Opción no válida" + estilo.ANSI_RESET + "\n");
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MENSAJES PARA LOGIN.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Solicita al usuario que introduzca su correo
     */
    public void pedirCorreo(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca su correo electrónico: "
                + estilo.ANSI_RESET);
    }

    /**
     * Solicita al usuario que introduzca su contraseña
     */
    public void pedirPasswordLoguin(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca su contraseña de acceso: " + estilo.ANSI_RESET);
    }

    /**
     * Informa que no existe ningun usuario con el correo que ha proporcionado
     */
    public void noExisteCorreo(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "No existe ningún usuario con ese correo asociado"
                + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica al usuario que su cuenta está bloqueada
     */
    public void usuarioBloqueado(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD +
                "Tu usuario está bloqueado, póngase en contacto con el administrador" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el usuario ha sido bloqueado por superar los intentos
     */
    public void seBloqueaUsuario(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Has gastado los intentos, tu usuario ha sido bloqueado"
                + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que la contraseña es incorrecta y muestra los intentos restantes
     */
    public void normalPassIncorrecta(int intentos){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Los datos introducidos no son correctos. Le quedan "
                + intentos + " intentos restantes" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que la contraseña para administrador es incorrecta
     */
    public void adminPassIncorrecta(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD +
                "Los datos introducidos no son correctos. Inténtelo de nuevo" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el codigo de verificacion ha sido enviado al correo
     */
    public void correoVerificacionEnviado(){
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD +
                "Código de verificación enviado a su correo electrónico." + estilo.ANSI_RESET);
    }

    /**
     * Solicita al usuario que introduzca el código de verificación
     */
    public void pedirToken(){
        System.out.println(estilo.BG_AZUL_PASTEL + estilo.ANSI_BLACK + estilo.BOLD +
                "Introduzca el código de verificación enviado a su correo" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el inicio de sesion ha sido correcto
     */
    public void loginCorrecto(){
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "Has iniciado sesión correctamente \n"
                + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el código de verificación es incorrecto
     */
    public void tokenIncorrecto(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD +
                "El código introducido no es correcto. Inténtelo de nuevo \n" + estilo.ANSI_RESET);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.REGISTRO DE NUEVOS USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Mensaje que muestra el título de la seccion de registro de usuarios
     */
    public void tituloRegistro(){
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + estilo.UNDERLINE + "✦ REGISTRO DE NUEVO USUARIO ✦" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que solicita al usuario que introduzca un nombre para su nueva cuenta
     */
    public void pedirNombreRegistro(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca un nombre de usuario para su nueva cuenta: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el correo debe contener el símbolo '@'
     */
    public void errorArroba(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Error: El correo electrónico debe contener el símbolo '@'" + estilo.ANSI_RESET + "\n");
    }

    /**
     * Mensaje que indica que tipo de rol desea para su cuenta
     */
    public void preguntaRol(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca el tipo de usuario que desea para su cuenta (ORGANIZADOR o ASISTENTE): " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el correo o el nombre de usuario ya están en uso
     */
    public void nombreOCorreoEnUso(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "El usuario o el correo que has introducido ya existe. Inténtelo de nuevo" + estilo.ANSI_RESET + "\n");
    }

    /**
     * Mensaje que confirma que el registro se ha completado correctamente
     */
    public void registroCorrecto(){
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "¡Te has registrado en FernanEvents correctamente!¡BIENVENIDO!" + estilo.ANSI_RESET + "\n");
    }

    /**
     * Mensaje que indica que el rol introducido no es válido
     */
    public void rolNoValido(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "El rol que ha introducido no es válido" + estilo.ANSI_RESET + "\n");

    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.CARTERA DE USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Muestra el menú de cartera digital donde el usuario puede consultar el saldo que tiene actualmente, así como añadir o retirar saldo de su cartera
     */
    public void menucarteraDigital() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + estilo.UNDERLINE + "✦ CARTERA DIGITAL ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Mostrar saldo actual");
        System.out.println("2. Añadir saldo");
        System.out.println("3. Retirar saldo");
        System.out.println(estilo.NEON_PINK + "4. Volver atrás" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_GREEN + "Elige la opción que deseas realizar: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra el saldo disponible de la cartera digital del usuario actual en FernanEvents
     */
    public void consultaSaldo(float saldo){
        System.out.println(estilo.PASTEL_BLUE + "El saldo actual de su cartera digital es de: " + estilo.ANSI_RESET +
                estilo.BOLD + estilo.PASTEL_GREEN + saldo + "€ \n" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que pregunta cuanto saldo se quiere añadir a la cartera digital
     */
    public void preguntaSumaSaldo(){
        System.out.println(estilo.PASTEL_BLUE + "¿Cuánto saldo quiere añadir a su cartera digital?"
                + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que pregunta cuanto saldo se quiere retirar de la cartera digital
     */
    public void preguntaRetiraSaldo(){
        System.out.println(estilo.PASTEL_BLUE + "¿Cuánto saldo quiere retirar de su cartera digital?"
                + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el saldo se ha añadido correctamente mostrando el nuevo saldo
     */
    public void sumaSaldoOK(float saldo){
        System.out.println(estilo.PASTEL_BLUE + "Saldo añadido con éxito. Su saldo actual es: " + estilo.ANSI_RESET +
                estilo.BOLD + estilo.PASTEL_YELLOW + saldo + "€ \n" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el saldo se ha retirado correctamente mostrando el saldo actualizado
     */
    public void retiraSaldoOK(float saldo){
        System.out.println(estilo.PASTEL_BLUE + "Saldo retirado con éxito. Su saldo actual es: " + estilo.ANSI_RESET +
                estilo.BOLD + estilo.PASTEL_YELLOW + saldo + "€ \n" + estilo.ANSI_RESET);
    }


    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.PANEL DE CONTROL DE ADMINISTRADOR.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Mensaje que indica que no hay usuarios bloqueados en el sistema
     */
    public void noHayUsuariosBloqueados(){
        System.out.println(estilo.PASTEL_BLUE + "No hay ningún usuario bloqueado \n" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica el título de la seccion de usuarios bloqueados
     */
    public void tituloUsuariosBloqueados(){
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + estilo.UNDERLINE + "✦ USUARIOS BLOQUEADOS ✦" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica un usuario bloqueado con su índice y nombre
     */
    public void mostrarUsuarioBloqueado(int indice, String nombre){
        System.out.println(estilo.PASTEL_BLUE + nombre + " (" + indice + ")" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que se pulse en el número correspondiente al usuario para desbloquearlo
     */
    public void pideNumeroUsuario(){
        System.out.println(estilo.PASTEL_GREEN + "Pulse el número que corresponde a cada usuario para desbloquearlo. " +
                "Para salir, pulse cualquier otro numero: " + estilo.ANSI_RESET);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.OPCION DE CONFIGURACION.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Mensaje que indica que escribamos el nombre de usuario de la persona a la que se quiere cambiar el nombre
     */
    public void pedirNombreUsuario(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca el nombre del usuario al que desea cambiarle su nombre de usuario: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que escribamos el nuevo nombre para el usuario
     */
    public void pedirNuevoNombre(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca el nuevo nombre que tendrá a partir de ahora este usuario: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el nombre introducido ya está en uso
     */
    public void nombreYaEnUso(String nuevoNombre){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Error: El nombre " + nuevoNombre + " ya está en uso. Introduce otro distinto." + estilo.ANSI_RESET + "\n");
    }

    /**
     * Mensaje que indica que no se ha encontrado ningun usuario con el nombre proporcionado
     */
    public void errorAlBuscarNombre(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "No se ha encontrado ningún usuario con el nombre que ha introducido" + estilo.ANSI_RESET + "\n");
    }

    /**
     * Mensaje que indica que escribamos la nueva contraseña de acceso
     */
    public void pedirNuevaPassword(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca la nueva contraseña de acceso: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que confirmemos de nuevo la contraseña
     */
    public void confirmarNuevaPassword(){
        System.out.print(estilo.PASTEL_BLUE + "Confirme la nueva contraseña: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que escribamos la contraseña actual
     */
    public void pedirPasswordActual(){
        System.out.print(estilo.BOLD + estilo.PASTEL_BLUE + "Introduce la contraseña actual: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que la contraseña actual es incorrecta
     */
    public void passwordActualIncorrecta(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Contraseña actual incorrecta. \n" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que no coinciden las contraseñas
     */
    public void noCoincidenPassword(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "¡ERROR! Las contraseñas no coinciden \n" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que requisitos debe cumplir la contraseña para que sea segura
     */
    public void requisitosPassSegura(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "La contraseña no es suficientemente segura. Debe tener:" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_YELLOW + "- Mínimo 8 caracteres");
        System.out.println("- Al menos una letra mayúscula y una minúscula");
        System.out.println("- Al menos un número");
        System.out.println("- Al menos un símbolo (- _ . , * + @)" + estilo.ANSI_RESET + "\n");
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MENSAJES INVITAR UN AMIGO (ASISTENTE)   .*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Mensaje que indica que el usuario no tiene amigos referidos en su lista
     */
    public void noHayAmigosReferidos(){
        System.out.println(estilo.PASTEL_BLUE + "No tienes ningún amigo referido añadido en tu lista \n" + estilo.ANSI_RESET);
    }
    /**
     * Mensaje que muestra la cabecera del listado de amigos referidos con el total de ellos
     */
    public void cabeceraListadoAmigosReferidos(int totalAmigos){
        System.out.println(estilo.PASTEL_BLUE + "Tus amigos referidos (" + totalAmigos + "): " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que muestra un amigo referido con su índice y correo
     */
    public void listarAmigo(int indice, String correo){
        System.out.println(estilo.PASTEL_BLUE + "  " + indice + ". " + correo + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que escribamos el correo del amigo referido
     */
    public void pedirCorreoAmigoReferido(){
        System.out.print(estilo.PASTEL_BLUE + "Escribe el correo electrónico del amigo que quieres añadir: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el formato del correo no es válido
     */
    public void correoMalEscrito(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Has escrito mal el correo electrónico. Asegúrate de que " +
                "tiene un @ en la dirección y sigue la estructura ejemplo@dominio.com \n" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el amigo referido se ha añadido correctamente a la lista
     */
    public void registroAmigoReferidoOK(String correo){
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "Has añadido a " + correo + " a tu lista de amigos" +
                " correctamente \n" + "Hemos enviado un correo electrónico notificando a tu amigo/a \n"  + estilo.ANSI_RESET);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MENSAJES PARA EVENTOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Mensaje que indica que no hay eventos disponibles
     */
    public void noHayEventos(){
        System.out.println(estilo.ANSI_RED + estilo.WARNING + " Actualmente no hay ningún evento disponible \n" + estilo.ANSI_RESET);
    }

    /**
     * solicita un dato para el evento
     */
    public void pedirDatosEvento(String dato) {
        System.out.print(estilo.PASTEL_BLUE + dato  + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que escribamos la categoria del evento mostrando las opciones disponibles
     */
    public void pedirDatosEventoCategoria(String dato){
        System.out.print(estilo.PASTEL_BLUE + "Introduce " + dato + " del evento (Arte, Tecnologia, Cine, Musica, Moda, " +
                "Juegos): " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que ya existe un evento con ese nombre
     */
    public void eventoYaExiste() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Ya existe un evento con ese nombre" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el evento se ha guardado correctamente
     */
    public void eventoGuardado() {
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "Evento guardado correctamente \n" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica las opciones disponibles para modificar un evento
     */
    public void mostrarOpcionesEvento() {
        System.out.println(estilo.PASTEL_BLUE + "1. Nombre\n2. Descripción\n3. Categoría\n4. Fecha\n5. Aforo\n" +
                "6. Inscritos\n" + "7. Modificar entradas\n" + estilo.NEON_PINK + "8. Cancelar" + estilo.ANSI_RESET);
        System.out.print(estilo.PASTEL_GREEN + "Seleccione una opción: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que escribamos el nombre del evento
     */
    public void pedirNombreEvento() {
        System.out.print(estilo.PASTEL_BLUE + "Nombre del evento: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica que el evento se ha eliminado
     */
    public void eventoEliminado() {
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "Evento eliminado" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica un mensaje de error personalizado
     */
    public void error(String mensaje) {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + mensaje + estilo.ANSI_RESET);
    }

    /**
     * Mensaje que indica información del total de la compra y pide confirmación
     */
    public void infoCompra(float total) {
        System.out.println(estilo.PASTEL_BLUE + "Total: " + estilo.ANSI_RESET + total + "€");
        System.out.print(estilo.PASTEL_GREEN + "¿Confirmar? (si/no): " + estilo.ANSI_RESET);
    }

    /**
     * Muestra las categorías disponibles del enum
     */
    public void mostrarCategorias() {
        System.out.println(estilo.PASTEL_YELLOW + "Categorías disponibles: " + estilo.ANSI_RESET);
        CategoriaEvento[] categorias = CategoriaEvento.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println(estilo.PASTEL_BLUE + "  " + (i+1) + ". " + categorias[i] + estilo.ANSI_RESET);
        }
    }

    /**
     * Pide seleccionar categoría por número
     */
    public void pedirCategoria() {
        System.out.print(estilo.PASTEL_BLUE + "Selecciona categoría (número): " + estilo.ANSI_RESET);
    }

    /**
     * Muestra el título de creación de evento
     */
    public void tituloCrearEvento() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "* DATOS DE EVENTO NUEVO *" + estilo.ANSI_RESET + "\n");
    }

    /**
     * Muestra el título de modificación de evento
     */
    public void tituloModificarEvento(String nombreEvento) {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "\n ✦ MODIFICANDO: " + nombreEvento + " ✦ " + estilo.ANSI_RESET);
    }

    /**
     * Muestra el título de eventos disponibles
     */
    public void tituloEventosDisponibles() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ EVENTOS DISPONIBLES ✦" + estilo.ANSI_RESET);
    }

    /**
     * Listar eventos para modificar
     */
    public void listarEventosParaModificar(String[] nombres, int total) {
        System.out.println(estilo.PASTEL_BLUE + "Elige el evento del que deseas modificar los datos: " + estilo.ANSI_RESET);
        for (int i = 0; i < total; i++) {
            if (nombres[i] != null) {
                System.out.println(estilo.PASTEL_BLUE + "(" + i + ") " + nombres[i] + estilo.ANSI_RESET);
            }
        }
        System.out.println();
    }

    /**
     * Pide el número del evento
     */
    public void pedirNumeroEvento() {
        System.out.print(estilo.PASTEL_GREEN + "Pulse el número del evento: " + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de evento no válido
     */
    public void eventoNoValido() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Evento no válido" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de evento no encontrado
     */
    public void eventoNoEncontrado() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "No se ha encontrado el evento" + estilo.ANSI_RESET + "\n");
    }

    /**
     * Mensaje de aforo insuficiente
     */
    public void aforoInsuficiente() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "No hay suficiente aforo disponible" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de inscritos superan aforo
     */
    public void inscritosSuperanAforo() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Los inscritos no pueden superar el aforo" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de categoría no válida
     */
    public void categoriaNoValida() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Categoría no válida" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de fecha no válida
     */
    public void fechaNoValida() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Formato de fecha no válido" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de cantidad no válida
     */
    public void cantidadNoValida() {
        System.out.println(estilo.ANSI_RED + "La cantidad introducida no es válida" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de límite de entradas excedido
     */
    public void limiteEntradasExcedido() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "No puede comprar más de 4 entradas" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de entradas insuficientes
     */
    public void entradasInsuficientes(int disponibles) {
        System.out.println(estilo.ANSI_RED + "Lo sentimos, solo hay " + disponibles + " entradas disponibles" + estilo.ANSI_RESET);
    }

    /**
     * Muestra el total de la compra
     */
    public void mostrarTotalCompra(int cantidad, float total) {
        System.out.println(estilo.PASTEL_BLUE + "Ha elegido comprar " + cantidad + " entradas. El precio total es de: " + estilo.ANSI_YELLOW + total + "€" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de compra exitosa
     */
    public void compraExitosa() {
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "¡Compra realizada con éxito!" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de operación cancelada
     */
    public void operacionCancelada() {
        System.out.println(estilo.ANSI_RED + "Operación cancelada" + estilo.ANSI_RESET + "\n");
    }

    /**
     * Título de mis eventos inscritos
     */
    public void tituloMisEventosInscritos() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ MIS EVENTOS INSCRITOS ✦" + estilo.ANSI_RESET);
    }

    /**
     * Mensaje de no inscrito en eventos
     */
    public void noInscritoEnEventos() {
        System.out.println(estilo.PASTEL_BLUE + "No te has inscrito en ningún evento todavía" + estilo.ANSI_RESET);
    }

    /**
     * Pide nombre del evento para inscribirse
     */
    public void pedirNombreEventoInscribir() {
        System.out.print(estilo.PASTEL_BLUE + "Escriba el nombre del evento al que desea inscribirse: " + estilo.ANSI_RESET);
    }

    /**
     * Muestra información de la entrada seleccionada
     */
    public void infoEntradaSeleccionada(String tipo, float precio) {
        System.out.println(estilo.PASTEL_BLUE + "Has elegido la entrada: " + estilo.ANSI_RESET + estilo.PASTEL_PURPLE + estilo.BOLD + tipo + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE + "El precio por entrada es de: " + estilo.ANSI_RESET + estilo.PASTEL_YELLOW + estilo.BOLD + precio + "€" + estilo.ANSI_RESET);
    }

    /**
     * Pide cantidad de entradas
     */
    public void pedirCantidadEntradas() {
        System.out.println("¿Cuántas entradas deseas comprar? (Máximo 4 entradas)");
        System.out.print(estilo.PASTEL_GREEN + "Cantidad: " + estilo.ANSI_RESET);
    }

    public void mostrarListaEventos(Evento[] eventos, int total) {
        System.out.println(estilo.PASTEL_BLUE + "Eventos disponibles:" + estilo.ANSI_RESET);
        for (int i = 0; i < total; i++) {
            System.out.println(" - " + eventos[i].getNombre());
        }
    }

    /**
     *Pide confirmacion con un mensaje y espera respuesta de si -> "s" o no -> "n"
     */
    public boolean pedirConfirmacion(String mensaje) {
        Scanner s = new Scanner(System.in);
        System.out.print(estilo.ANSI_RED + mensaje + " (s/n): " + estilo.ANSI_RESET);
        return s.nextLine().equalsIgnoreCase("s");
    }

    public void aforoCompleto(String tipo) {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Aforo completo. No se pueden asignar entradas de tipo: " + tipo + estilo.ANSI_RESET);
    }

    public void preguntaCantidadEntradasPorTipo(String tipo, int aforoDisponible) {
        System.out.println(estilo.PASTEL_BLUE + "¿Cuántas entradas de tipo " + tipo + " va a tener este evento? " +
                "(Aforo disponible: " + aforoDisponible + ")" + estilo.ANSI_RESET);
    }

    public void errorCantidadNoValida() {
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Cantidad no válida, no se pueden vender más entradas de " +
                "la cantidad de aforo disponible" + estilo.ANSI_RESET + "\n");
    }

    public void preguntaPrecioEntrada(String tipo) {
        System.out.println(estilo.PASTEL_BLUE + "¿Qué precio van a tener las entradas de tipo " + tipo + "? " +
                "(introduzca la cantidad en euros)" + estilo.ANSI_RESET);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.COMPRA DE ENTRADAS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    public void cabeceraMisEventos() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ MIS EVENTOS INSCRITOS ✦" + estilo.ANSI_RESET);
    }

    public void mensajeEntradasCompradas(int cantidad) {
        String texto = (cantidad == 1) ? "entrada comprada" : "entradas compradas";
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "Tiene " + cantidad + " " + texto + " para este evento"
                + estilo.ANSI_RESET + "\n");
    }

    public void mensajeNoInscrito() {
        System.out.println(estilo.ANSI_RED + "No se ha inscrito en ningún evento todavía" + estilo.ANSI_RESET + "\n");
    }

    public void menuEntradaTipo() {
        System.out.println(estilo.PASTEL_BLUE + "\nSeleccione el tipo de entrada:");
        System.out.println("1. General \n2. VIP \n3. Infantil" + estilo.ANSI_RESET);
        System.out.print(estilo.PASTEL_GREEN + "Seleccione una opción: " + estilo.ANSI_RESET);
    }

    public void mostrarDetallePreCompra(String tipo, float precio) {
        System.out.println(estilo.PASTEL_BLUE + "Has elegido la entrada: " + estilo.ANSI_RESET + estilo.PASTEL_PURPLE + estilo.BOLD + tipo + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE + "El precio por entrada es de: " + estilo.ANSI_RESET + estilo.PASTEL_YELLOW + estilo.BOLD + precio + " euros" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE + "¿Cuántas entradas deseas comprar? (Máximo 4 entradas)" + estilo.ANSI_RESET);
    }

    public void errorLimiteEntradas(int entradas) {
        if (entradas >= 4) {
            System.out.println(estilo.ANSI_RED + estilo.BOLD + "No puede comprar más entradas para este evento" + estilo.ANSI_RESET);
        } else {
            System.out.println(estilo.ANSI_RED + "No puede adquirir más de 4 entradas en total (Ya tiene: " + entradas + ")" + estilo.ANSI_RESET + "\n");
        }
    }

    public void preguntaConfirmacionCompra(float saldoActual) {
        System.out.println(estilo.PASTEL_BLUE + "Su saldo actual es de " + estilo.ANSI_RESET + estilo.PASTEL_GREEN +
                estilo.BOLD + saldoActual + " euros" + estilo.ANSI_RESET + estilo.PASTEL_BLUE +
                ". ¿Desea realizar la compra?" + estilo.ANSI_RESET);
        System.out.print(estilo.PASTEL_GREEN + "SI/NO: " + estilo.ANSI_RESET);
    }

    public void noHayStockEntradas(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Lo sentimos, no quedan entradas disponibles de este tipo" + estilo.ANSI_RESET);
    }

    public void saldoInsuficiente(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "No dispone de saldo suficiente en su cartera digital para realizar esta operación" + estilo.ANSI_RESET + "\n");
    }

    public void avisoPrecioTotal(int cantidadCompraEntrada, float precioTotal){
        System.out.println(estilo.PASTEL_BLUE + "Ha elegido comprar " + cantidadCompraEntrada + " entradas. " +
                "El precio total de la compra es de: " + estilo.ANSI_YELLOW + precioTotal + estilo.PASTEL_BLUE +
                " euros" + estilo.ANSI_RESET);

    }
}
