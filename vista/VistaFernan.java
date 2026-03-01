package FernanEvents.vista;

import FernanEvents.modelo.Rol;

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
     * Muestra el menú principal cuando se inicia el programa enseñando los distintos tipos de perfiles con los que puede interactuar el usuario
     */
    public void menuOpcionesUsuario() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + estilo.ITALIC + "✦ ELIGE TU TIPO DE USUARIO ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Administrador");
        System.out.println("2. Organizador");
        System.out.println("3. Asistente");
        System.out.println(estilo.NEON_PINK + "4. Salir" + estilo.ANSI_RESET);

        System.out.println(estilo.SILVER + "────────────────────────────────────────────" + estilo.ANSI_RESET);
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
     * Muestra un menú que aparece cuando se intenta iniciar sesión como asistente, permitiendo al usuario elegir qué tipo de asistente es.
     */
    public void menuTipoAsistente() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ ¿QUÉ TIPO DE ASISTENTE ERES? ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Asistente 1");
        System.out.println("2. Asistente 2");
        System.out.println(estilo.NEON_PINK + "3. Volver atrás" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_GREEN + "Seleccione una opción: " + estilo.ANSI_RESET);
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
     * Muestra el menú de panel de control donde el administrador puede elegir sobre qué perfil trabajar pudiendo cambiar el nombre de usuario y contraseña de dicho perfil
     */
    public void OpcionesUsuariosPanelSoloAdmin() {
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ PANEL DE CONTROL ✦" + estilo.ANSI_RESET);

        System.out.println(estilo.PASTEL_BLUE + "1. Organizador");
        System.out.println("2. Asistente 1");
        System.out.println("3. Asistente 2" + estilo.ANSI_RESET);
        System.out.println(estilo.NEON_PINK + "4. Volver atrás" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_GREEN + "Seleccione una opción: " + estilo.ANSI_RESET);
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
     * Muestra un menú con los tipos de detalles de los que cosnta un evento. Aparece cuando el organizador quiere modiciar un evento que ya se ha creado anteriormente.
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

    public void mostrarDespedida(){
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "*** FIN DEL PROGRAMA ***" + estilo.ANSI_RESET);
        System.out.println(estilo.PASTEL_BLUE +
                "Gracias por utilizar FernanEvents. ¡Nos vemos en los escenarios! 😉" + estilo.ANSI_RESET);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.NOTIFICACIONES GENERALES.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    public void mensajeConfirmacion(){
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD +
                " ✅ Operación realizada correctamente" + estilo.ANSI_RESET + "\n");
    }

    public void mensajeError(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD +
                " ❌ ERROR, no se ha podido completar la operación solicitada" + estilo.ANSI_RESET  + "\n");
    }

    public void notificacion(String mensaje){
        System.out.println(mensaje);
    }

    public void opcionNoValida(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Opción no válida" + estilo.ANSI_RESET);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MENSAJES PARA LOGIN.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    public void pedirCorreoLoguin(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca su correo electrónico para iniciar sesión: "
                + estilo.ANSI_RESET);
    }

    public void pedirPasswordLoguin(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca su contraseña de acceso: " + estilo.ANSI_RESET);
    }

    public void noExisteCorreo(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "No existe ningún usuario con ese correo asociado"
                + estilo.ANSI_RESET);
    }

    public void usuarioBloqueado(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD +
                "Tu usuario está bloqueado, póngase en contacto con el administrador" + estilo.ANSI_RESET);
    }

    public void seBloqueaUsuario(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Has gastado los intentos, tu usuario ha sido bloqueado"
                + estilo.ANSI_RESET);
    }

    public void normalPassIncorrecta(int intentos){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "Los datos introducidos no son correctos. Le quedan "
                + intentos + " intentos restantes" + estilo.ANSI_RESET);
    }

    public void adminPassIncorrecta(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD +
                "Los datos introducidos no son correctos. Inténtelo de nuevo" + estilo.ANSI_RESET);
    }

    public void correoVerificacionEnviado(){
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD +
                "Código de verificación enviado a su correo electrónico." + estilo.ANSI_RESET);
    }

    public void pedirToken(){
        System.out.println(estilo.BG_AZUL_PASTEL + estilo.ANSI_BLACK + estilo.BOLD +
                "Introduzca el código 2FA enviado a su correo" + estilo.ANSI_RESET);
    }

    public void loginCorrecto(){
        System.out.println(estilo.PASTEL_GREEN + estilo.BOLD + "Has iniciado sesión correctamente \n"
                + estilo.ANSI_RESET);
    }

    public void tokenIncorrecto(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD +
                "El código introducido no es correcto. Inténtelo de nuevo \n" + estilo.ANSI_RESET);
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

    public void preguntaSumaSaldo(){
        System.out.println(estilo.PASTEL_BLUE + "¿Cuánto saldo quiere añadir a su cartera digital?"
                + estilo.ANSI_RESET);
    }

    public void preguntaRetiraSaldo(){
        System.out.println(estilo.PASTEL_BLUE + "¿Cuánto saldo quiere retirar de su cartera digital?"
                + estilo.ANSI_RESET);
    }

    public void sumaSaldoOK(float saldo){
        System.out.println(estilo.PASTEL_BLUE + "Saldo añadido con éxito. Su saldo actual es: " + estilo.ANSI_RESET +
                estilo.BOLD + estilo.PASTEL_YELLOW + saldo + "€ \n" + estilo.ANSI_RESET);
    }

    public void retiraSaldoOK(float saldo){
        System.out.println(estilo.PASTEL_BLUE + "Saldo retirado con éxito. Su saldo actual es: " + estilo.ANSI_RESET +
                estilo.BOLD + estilo.PASTEL_YELLOW + saldo + "€ \n" + estilo.ANSI_RESET);
    }


    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.PANEL DE CONTROL DE ADMINISTRADOR.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    public void noHayUsuariosBloqueados(){
        System.out.println(estilo.PASTEL_BLUE + "No hay ningún usuario bloqueado \n" + estilo.ANSI_RESET);
    }

    public void cabeceraUsuariosBloqueados(){
        System.out.println(estilo.PASTEL_PURPLE + estilo.BOLD + "✦ USUARIOS BLOQUEADOS ✦" + estilo.ANSI_RESET);
    }

    public void mostrarUsuarioBloqueado(int indice, String nombre){
        System.out.println(estilo.PASTEL_BLUE + nombre + " (" + indice + ")" + estilo.ANSI_RESET);
    }

    public void pideNumeroUsuario(){
        System.out.println(estilo.PASTEL_GREEN + "Pulse el número que corresponde a cada usuario para desbloquearlo. " +
                "Para salir, pulse cualquier otro numero: " + estilo.ANSI_RESET);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.OPCION CONFIGURACION DE ADMINISTRADOR.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    public void pedirNombreUsuario(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca el nombre del usuario al que desea cambiarle su nombre de usuario: " + estilo.ANSI_RESET);
    }

    public void pedirNuevoNombre(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca el nuevo nombre que tendrá a partir de ahora este usuario: " + estilo.ANSI_RESET);
    }

    public void nombreYaEnUso(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "El nuevo nombre que has introducido ya está en uso. Introduce otro distinto. " + estilo.ANSI_RESET);
    }

    public void errorAlBuscarNombre(){
        System.out.println(estilo.ANSI_RED + estilo.BOLD + "No se ha encontrado ningún usuario con el nombre que ha introducido" + estilo.ANSI_RESET);
    }

    public void pedirNuevaPassword(){
        System.out.print(estilo.PASTEL_BLUE + "Introduzca la nueva contraseña de acceso para el usuario que ha elegido: " + estilo.ANSI_RESET);

    }



}
