package FernanEvents.controlador;

import FernanEvents.modelo.*;
import FernanEvents.modelo.utilidades.Cadenas;
import FernanEvents.modelo.utilidades.EnvioGMail;
import FernanEvents.modelo.utilidades.FuncionesFechas;
import FernanEvents.vista.VistaFernan;

import java.time.LocalDate;
import java.util.Scanner;

public class ControladorFernan {

    private GestionUsuario modelo;
    private VistaFernan vista;
    private GestionEvento evento;
    private Usuario usuarioLogueado;

    public ControladorFernan(GestionUsuario modelo, VistaFernan vista, GestionEvento evento){
        this.modelo = modelo;
        this.vista = vista;
        this.evento = evento;
    }

    public void iniciarFernan() throws InterruptedException {
        Scanner s = new Scanner(System.in);
        int opcionMenu;
        do{
            vista.iniciarProgramaBarraProgreso();
            vista.mostrarFERNANEVENTSASCII();
            vista.menuLogin();
            opcionMenu = Integer.parseInt(s.nextLine());

            switch(opcionMenu){
                case 1:
                    if(LoginUsuario()){
                        muestraMenuPorRol();
                    }
                    break;

                case 2:
                    if(registrarUsuario()){
                        vista.registroCorrecto();
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 3:
                    //Rompe el bucle y lleva al mensaje de fuera del do-while
                    break;

                default:
                    vista.opcionNoValida();
            }

        }while(opcionMenu != 3);
        vista.mostrarDespedida();
    }

    private boolean LoginUsuario(){
        Scanner s = new Scanner(System.in);
        int intentosRestantes = 3;
        boolean passwordCorrecta = false;
        Usuario usuario;

        while(true){
            vista.pedirCorreo();
            String correo = s.nextLine().toLowerCase();
            usuario = modelo.buscaUsuarioPorCorreo(correo);

            if(usuario == null){
                vista.noExisteCorreo();
                continue;
            }

            if(usuario.isBloqueado()){
                vista.usuarioBloqueado();
                return false;
            }
            break;
        }

        while(intentosRestantes > 0 && !passwordCorrecta){
            vista.pedirPasswordLoguin();
            String password = s.nextLine();
            if(usuario.getPassword().equals(password)){
                passwordCorrecta = true;
            }else{
                if(!usuario.getRol().name().equals("ADMINISTRADOR")){
                    intentosRestantes--;
                    vista.normalPassIncorrecta(intentosRestantes);
                }else{
                    vista.adminPassIncorrecta();
                }
            }
        }

        if(!passwordCorrecta){
            modelo.actualizaEstadoBloqueo(usuario.getCorreo(), true);
            vista.seBloqueaUsuario();
            return false;
        }

        String codigoVerificacion = Cadenas.generarCodigoVerificacion();
        String destinatario = "jmorcam520@g.educaand.es";
        String asunto = "Código de verificación - Inicio de sesión";
        String cuerpo = EnvioGMail.plantillaLoginAdmin(usuario.getNombre(), codigoVerificacion);

        EnvioGMail.enviarConGMail(destinatario, asunto, cuerpo);
        vista.correoVerificacionEnviado();

        boolean logueado = false;
        while(!logueado){
            vista.pedirToken();
            String entradaCodigo = s.nextLine();

            if(entradaCodigo.equals(codigoVerificacion)){
                logueado = true;
                this.usuarioLogueado = usuario;
                vista.loginCorrecto();
                return true;

            }else{
                vista.tokenIncorrecto();
            }
        }
        return false;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.REGISTRO DE NUEVOS USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    private boolean registrarUsuario(){
        Scanner s = new Scanner(System.in);
        vista.tituloRegistro();
        vista.pedirNombreRegistro();
        String nombreRegistro = s.nextLine();
        String correoRegistro = registroCorreo();
        String passwordRegistro = obtenerPasswordValida();
        Rol rolCorrecto = registroRol();

        if(modelo.buscarPorNombre(nombreRegistro) != null || modelo.buscaUsuarioPorCorreo(correoRegistro) != null){
            vista.nombreOCorreoEnUso();
            return false;
        }
        String codigoVerificacion = Cadenas.generarCodigoVerificacion();
        enviarTokenRegistro(nombreRegistro, correoRegistro, codigoVerificacion);
        return verificaTokenYRegistro(codigoVerificacion, nombreRegistro, correoRegistro, passwordRegistro, rolCorrecto);
    }

    private String registroCorreo(){
        Scanner s = new Scanner(System.in);
        String correo = "";
        boolean valido = false;
        while (!valido) {
            vista.pedirCorreo();
            correo = s.nextLine();
            if (!correo.contains("@")) {
                vista.errorArroba();
            } else {
                valido = true;
            }
        }
        return correo;
    }

    private Rol registroRol(){
        Scanner s = new Scanner(System.in);
        while(true){
            vista.preguntaRol();
            String entrada = s.nextLine().toUpperCase();
            if(entrada.equals("ORGANIZADOR")) {
                return Rol.ORGANIZADOR;
            }else if(entrada.equals("ASISTENTE")){
                return Rol.ASISTENTE;
            }else{
                vista.rolNoValido();
            }
        }
    }

    private void enviarTokenRegistro(String nombreRegistro, String correoRegistro, String codigoVerificacion){
        String cuerpo = EnvioGMail.plantillaRegistroUsuario(nombreRegistro, codigoVerificacion);
        EnvioGMail.enviarConGMail(correoRegistro, "Token único de inicio de sesión", cuerpo);
        vista.correoVerificacionEnviado();
    }

    private boolean verificaTokenYRegistro(String codigoVerificacion, String nombreRegistro, String correoRegistro, String passwordRegistro, Rol rolCorrecto){
        Scanner s = new Scanner(System.in);
        boolean tokenVerificado = false;
        while (!tokenVerificado) {
            vista.pedirToken();
            String tokenRegistro = s.nextLine();

            if (!tokenRegistro.equals(codigoVerificacion)) {
                vista.tokenIncorrecto();
            } else {
                if (modelo.getNumUsuarios() == modelo.getUsuarios().length) {
                    modelo.aumentarCapacidad();
                }

                Usuario nuevoUsuario;
                if (rolCorrecto.equals(Rol.ORGANIZADOR)) {
                    nuevoUsuario = new Organizador(nombreRegistro, correoRegistro, passwordRegistro);
                } else {
                    nuevoUsuario = new Asistente(nombreRegistro, correoRegistro, passwordRegistro);
                }

                modelo.aniadirUsuario(nuevoUsuario);
                tokenVerificado = true;
            }
        }
        return true;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MENÚS PRINCIPALES DE USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    public void muestraMenuPorRol() throws InterruptedException {
        if(usuarioLogueado == null){ return; }
        switch(usuarioLogueado.getRol()){
            case ADMINISTRADOR:
                menuPrincipalAdmin();
                break;

            case ORGANIZADOR:
                menuPrincipalOrganizador();
                break;

            case ASISTENTE:
                menuPrincipalAsistente();
                break;
        }
    }

    private void menuPrincipalAdmin() throws InterruptedException {
        Scanner s = new Scanner(System.in);
        int opcionMenu;

        do{
            vista.menuAdministrador();
            opcionMenu = Integer.parseInt(s.nextLine());

            switch(opcionMenu){
                case 1:
                    panelControlAdmin();
                    break;

                case 2:
                    verEventosAdmin();
                    break;

                case 3:
                    gestionaCarteraDigital();
                    break;

                case 4:
                    configuracionAdmin();
                    break;

                case 5:
                    break;

                default:
                    vista.opcionNoValida();

            }
        }while(opcionMenu != 5);
        vista.cerrarSesion(usuarioLogueado.getNombre());
    }

    private void menuPrincipalOrganizador() throws InterruptedException {
        Scanner s = new Scanner(System.in);
        int opcionMenu;

        do{
            vista.menuOrganizador();
            opcionMenu = Integer.parseInt(s.nextLine());

            switch(opcionMenu){
                case 1:
                    menuEventosOrganizador();
                    break;

                case 2:
                    gestionaCarteraDigital();
                    break;

                case 3:
                    configuracionUsuario();
                    break;

                case 4:
                    break;

                default:
                    vista.opcionNoValida();
            }

        }while(opcionMenu != 4);
        vista.cerrarSesion(usuarioLogueado.getNombre());
    }

    private void menuPrincipalAsistente() throws InterruptedException {
        Scanner s = new Scanner(System.in);
        int opcionMenu;

        do{
            vista.menuAsistente();
            opcionMenu = Integer.parseInt(s.nextLine());

            switch(opcionMenu){
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    gestionaCarteraDigital();
                    break;

                case 4:
                    break;

                case 5:
                    configuracionUsuario();
                    break;

                case 6:
                    break;

                default:
                    vista.opcionNoValida();
            }

        }while(opcionMenu != 6);
        vista.cerrarSesion(usuarioLogueado.getNombre());
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.PANEL DE CONTROL DE ADMINISTRADOR.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    private void panelControlAdmin(){
        if(!modelo.confirmaUsuariosBloqueados()){
            vista.noHayUsuariosBloqueados();
        }else{
            if(gestionaUsuariosBloqueados()){
                vista.mensajeConfirmacion();
            }else{
                vista.mensajeError();
            }
        }
    }

    private boolean gestionaUsuariosBloqueados(){
        Scanner s = new Scanner(System.in);
        Usuario[] listaUsuarios = modelo.getUsuarios();
        vista.tituloUsuariosBloqueados();
        for (int i = 0; i < modelo.getNumUsuarios(); i++) {
            if(listaUsuarios[i] != null && listaUsuarios[i].isBloqueado()){
                vista.mostrarUsuarioBloqueado(i, listaUsuarios[i].getNombre());
            }
        }
        vista.pideNumeroUsuario();
        int opcionPanelBloqueo = Integer.parseInt(s.nextLine());
        if(opcionPanelBloqueo > 0 && opcionPanelBloqueo < listaUsuarios.length &&
                listaUsuarios[opcionPanelBloqueo] != null){

            Usuario usuarioQuitarBloqueo = listaUsuarios[opcionPanelBloqueo];
            return modelo.actualizaEstadoBloqueo(usuarioQuitarBloqueo.getCorreo(), false);
        }
        return false;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.CARTERA DE USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    /**
     * Función que haciendo uso de otras subfunciones(consultaSaldo, sumaSaldo, retiraSaldo) permite al usuario gestionar las diferentes opciones que ofrece la cartera digital de FernanEvents
     */
    private void gestionaCarteraDigital(){
        Scanner s = new Scanner(System.in);
        int opcionMenu;

        do{
            vista.menucarteraDigital();
            opcionMenu = Integer.parseInt(s.nextLine());

            switch(opcionMenu){
                case 1:
                    vista.consultaSaldo(usuarioLogueado.getSaldo());
                    break;

                case 2:
                    if(sumaSaldo()){
                        usuarioLogueado = modelo.buscaUsuarioPorCorreo(usuarioLogueado.getCorreo());
                        vista.sumaSaldoOK(usuarioLogueado.getSaldo());
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 3:
                    if(retiraSaldo()){
                        usuarioLogueado = modelo.buscaUsuarioPorCorreo(usuarioLogueado.getCorreo());
                        vista.retiraSaldoOK(usuarioLogueado.getSaldo());
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 4:
                    break;

                default:
                    vista.opcionNoValida();
            }
        }while(opcionMenu != 4);
    }

    /**
     * Función que añade la cantidad de saldo que introduzca el usuario actual a su cartera digital
     */
    private boolean sumaSaldo(){
        Scanner s = new Scanner(System.in);
        vista.preguntaSumaSaldo();
        float saldoASumar = Float.parseFloat(s.nextLine());
        return modelo.aniadirSaldo(usuarioLogueado.getCorreo(), saldoASumar);
    }

    /**
     * Función que retira la cantidad de saldo que introduzca el usuario actual a su cartera digital
     */
    private boolean retiraSaldo(){
        Scanner s = new Scanner(System.in);
        vista.preguntaRetiraSaldo();
        float saldoARetirar = Float.parseFloat(s.nextLine());
        return modelo.quitarSaldo(usuarioLogueado.getCorreo(), saldoARetirar);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.OPCION CONFIGURACION DE ADMINISTRADOR.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    private void configuracionAdmin(){
        Scanner s = new Scanner(System.in);
        int opcionMenuConfig;
        do{
            vista.menuConfiguracionGeneral();
            opcionMenuConfig = Integer.parseInt(s.nextLine());

            switch(opcionMenuConfig){
                case 1:
                    if(cambiaNombreAdmin()){
                        vista.mensajeConfirmacion();
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 2:
                    if(cambiaPasswordAdmin()){
                        vista.mensajeConfirmacion();
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 3:
                    break;

                default:
                    vista.opcionNoValida();
            }
        }while(opcionMenuConfig != 3);
    }

    private boolean cambiaNombreAdmin(){
        Scanner s = new Scanner(System.in);
        vista.pedirNombreUsuario();
        String nombreUsuarioCambio = s.nextLine();
        Usuario usuarioCambio = modelo.buscarPorNombre(nombreUsuarioCambio);

        if(usuarioCambio == null){
            vista.errorAlBuscarNombre();
            return false;
        }

        vista.pedirNuevoNombre();
        String nuevoNombreUsuario = s.nextLine();

        if(modelo.buscarPorNombre(nuevoNombreUsuario) != null){
            vista.nombreYaEnUso(nuevoNombreUsuario);
            return false;
        }
        return modelo.actualizarNombre(usuarioCambio.getCorreo(), nuevoNombreUsuario);
    }

    private boolean cambiaPasswordAdmin(){
        Scanner s = new Scanner(System.in);
        vista.pedirNombreUsuario();
        String nombreUsuarioCambio = s.nextLine();
        Usuario usuarioCambio = modelo.buscarPorNombre(nombreUsuarioCambio);

        if(usuarioCambio == null){
            vista.errorAlBuscarNombre();
            return false;
        }

        vista.pedirNuevaPassword();
        String nuevaPassword = s.nextLine();
        return modelo.actualizarContrasena(usuarioCambio.getCorreo(), nuevaPassword);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.OPCION CONFIGURACION RESTO DE USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    private void configuracionUsuario(){
        Scanner s = new Scanner(System.in);
        int opcionMenuConfig;
        do{
            vista.menuConfiguracionGeneral();
            opcionMenuConfig = Integer.parseInt(s.nextLine());
            switch(opcionMenuConfig){
                case 1:
                    if(cambiaNombreUsuario()){
                        vista.mensajeConfirmacion();
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 2:
                    if(cambiaPasswordUsuario()){
                        vista.mensajeConfirmacion();
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 3:
                    break;

                default:
                    vista.opcionNoValida();
            }

        }while(opcionMenuConfig != 3);
    }

    private boolean cambiaNombreUsuario(){
        Scanner s = new Scanner(System.in);
        vista.pedirNuevoNombre();
        String nuevoNombre = s.nextLine();
        if(modelo.buscarPorNombre(nuevoNombre) != null){
            vista.nombreYaEnUso(nuevoNombre);
            return false;
        }

        if(modelo.actualizarNombre(usuarioLogueado.getCorreo(), nuevoNombre)){
            usuarioLogueado = modelo.buscaUsuarioPorCorreo(usuarioLogueado.getCorreo());
            return true;
        }
        return false;
    }

    private boolean cambiaPasswordUsuario(){
        Scanner s = new Scanner(System.in);
        vista.pedirPasswordActual();
        String passwordActual = s.nextLine();

        if(!usuarioLogueado.getPassword().equals(passwordActual)){
            vista.passwordActualIncorrecta();
            return false;
        }else{
            String nuevaPassword = obtenerPasswordValida();

            if(modelo.actualizarContrasena(usuarioLogueado.getCorreo(), nuevaPassword)){
                this.usuarioLogueado = modelo. buscaUsuarioPorCorreo(usuarioLogueado.getCorreo());
                return true;
            }
        }
        return false;
    }

    private String obtenerPasswordValida(){
        Scanner s = new Scanner(System.in);
        String nuevaPassword = "";
        boolean passwordValida = false;

        while(!passwordValida){
            vista.pedirNuevaPassword();
            nuevaPassword = s.nextLine();

            if(!Cadenas.esContraseniaFuerte(nuevaPassword)){
                vista.requisitosPassSegura();
            }else{
                vista.confirmarNuevaPassword();
                String confirmacionPassword = s.nextLine();

                if(!Cadenas.esIgualContrasenia(nuevaPassword, confirmacionPassword)){
                    vista.noCoincidenPassword();
                }else{
                    passwordValida = true;
                }
            }
        }
        return nuevaPassword;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.EVENTOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    // PARA ADMIN
    private void verEventosAdmin(){
        Evento[] eventos = this.evento.obtenerTodosLosEventos();

        if (eventos.length == 0){
            vista.noHayEventos();
            return;
        }

        for (int i = 0; i < eventos.length; i++) {
            Evento evento = eventos[i];
            if (evento != null){
                String fechaformateada = FuncionesFechas.convertirLocalDateString(evento.getFecha());
                vista.mostrarEventoTabla(evento.getNombre(), evento.getCategoria().toString(), fechaformateada);
            }
        }
    }

    //PARA ORGANIZADOR
    private void menuEventosOrganizador() throws InterruptedException{
        Scanner s = new Scanner(System.in);
        int opcionEvento;

        do {
            vista.menuOrganizadorEventos();
            opcionEvento = Integer.parseInt(s.nextLine());

            switch (opcionEvento){
                case 1:
                    verEventosOrganizador();
                    break;

                case 2:
                    crearNuevoEvento();
                    break;

                case 3:
                    modificarEvento();
                    break;

                case 4:
                    eliminarEvento();
                    break;

                case 5:
                    break;

                default:
                    vista.opcionNoValida();
            }
        }while (opcionEvento !=5);
    }

    private void verEventosOrganizador() {
        Evento[] eventos = this.evento.obtenerTodosLosEventos();
        if (eventos.length == 0) {
            vista.noHayEventos();
            return;
        }

        for (int i = 0; i < eventos.length; i++) {
            Evento evento = eventos[i];
            if (evento!= null) {
                String fechaFormateada = FuncionesFechas.convertirLocalDateString(evento.getFecha());
                vista.mostrarEventoTabla(evento.getNombre(), evento.getCategoria().toString(), fechaFormateada);
            }
        }
    }

    /**
     * Función para crear un evento nuevo
     */

    private void crearNuevoEvento(){
        Scanner s = new Scanner(System.in);
        vista.pedirDatosEvento("Nombre");
        String nombre = s.nextLine();

        if (evento.buscarEventoPorNombre(nombre) != null){
            vista.eventoYaExiste();
            return;
        }

        vista.pedirDatosEvento("Descripción");
        String descripcion = s.nextLine();

        //categorías disponibles
        vista.mostrarCategorias();
        vista.pedirCategoria();

        int opcionCategoria =Integer.parseInt(s.nextLine()) -1;

        if (opcionCategoria < 0 || opcionCategoria >= CategoriaEvento.values().length) {
            vista.categoriaNoValida();
            return;
        }

        CategoriaEvento categoria = CategoriaEvento.values()[opcionCategoria];

        vista.pedirDatosEvento("fecha (dd/mm/aaaa)");
        String fechaString = s.nextLine();

        LocalDate fecha = FuncionesFechas.convertirStringEnFecha(fechaString);

        if (fecha == null) {
            vista.fechaNoValida();
            return;
        }

        // validar que la fecha no se haya pasado
        long diasRestantes = FuncionesFechas.diasRestantes(fecha);
        if (diasRestantes < 0) {
            vista.error("No se pueden crear eventos con fecha pasada");
            return;
        }

        vista.pedirDatosEvento("aforo");
        int aforo = Integer.parseInt(s.nextLine());

        vista.pedirDatosEvento("número de inscritos");
        int inscritos = Integer.parseInt(s.nextLine());

        if (inscritos > aforo) {
            vista.inscritosSuperanAforo();
            return;
        }

        Evento nuevoEvento = evento.crearEvento(nombre, descripcion, categoria, fechaString, aforo, inscritos);

        if (nuevoEvento != null) {
            vista.eventoGuardado();
        } else {
            vista.mensajeError();
        }
    }

    /**
     * Función para modificar un evento
     */

    private void modificarEvento() {
    Scanner s = new Scanner(System.in);

    Evento[] eventos = this.evento.obtenerTodosLosEventos();
    if (eventos.length == 0) {
        vista.noHayEventos();
        return;
    }

    String[] nombresEventos = new String[eventos.length];
    for (int i = 0; i < eventos.length; i++) {
        if (eventos[i] != null) {
            nombresEventos[i] = eventos[i].getNombre();
        }
    }

    vista.listarEventosParaModificar(nombresEventos, eventos.length);
    vista.pedirNumeroEvento();
    int opcion = Integer.parseInt(s.nextLine());

    if (opcion < 0 || opcion >= eventos.length || eventos[opcion] == null) {
        vista.eventoNoValido();
        return;
    }

    Evento eventoModificar = eventos[opcion];
    vista.tituloModificarEvento(eventoModificar.getNombre());
    vista.mostrarOpcionesEvento();

    int opcionModif = Integer.parseInt(s.nextLine());

    switch (opcionModif) {
        case 1:
            vista.pedirDatosEvento("nuevo nombre");
            String nuevoNombre = s.nextLine();
            if (evento.actualizarNombre(eventoModificar.getNombre(), nuevoNombre)) {
                vista.mensajeConfirmacion();
            } else {
                vista.mensajeError();
            }
            break;

        case 2:
            vista.pedirDatosEvento("nueva descripción");
            String nuevaDesc = s.nextLine();
            if (evento.actualizarDescripcion(eventoModificar.getNombre(), nuevaDesc)) {
                vista.mensajeConfirmacion();
            } else {
                vista.mensajeError();
            }
            break;

        case 3:
            vista.mostrarCategorias();
            vista.pedirCategoria();
            int opcionCategoria = Integer.parseInt(s.nextLine()) - 1;

            if (opcionCategoria < 0 || opcionCategoria >= CategoriaEvento.values().length) {
                vista.categoriaNoValida();
                break;
            }

            if (evento.actualizarCategoria(eventoModificar.getNombre(), CategoriaEvento.values()[opcionCategoria])) {
                vista.mensajeConfirmacion();
            } else {
                vista.mensajeError();
            }
            break;

        case 4:
            vista.pedirDatosEvento("nueva fecha (dd/mm/aaaa)");
            String nuevafechaString = s.nextLine();

            // validar la fecha antes de poder actualizar
            LocalDate nuevaFecha = FuncionesFechas.convertirStringEnFecha(nuevafechaString);
            if (nuevaFecha == null) {
                vista.fechaNoValida();
                break;
            }

            if (evento.actualizarFecha(eventoModificar.getNombre(), nuevafechaString)) {
                vista.mensajeConfirmacion();
            } else {
                vista.mensajeError();
            }
            break;

        case 5:
            vista.pedirDatosEvento("nuevo aforo");
            int nuevoAforo = Integer.parseInt(s.nextLine());
            if (evento.actualizarAforo(eventoModificar.getNombre(), nuevoAforo)) {
                vista.mensajeConfirmacion();
            } else {
                vista.mensajeError();
            }
            break;

        case 6:
            vista.pedirDatosEvento("nuevo número de inscritos");
            int nuevosInscritos = Integer.parseInt(s.nextLine());

            int diferencia = nuevosInscritos - eventoModificar.getPersonasInscritas();

            if (diferencia > 0) {
                if (evento.actualizarInscritos(eventoModificar.getNombre(), diferencia)) {
                    vista.mensajeConfirmacion();
                } else {
                    vista.aforoInsuficiente();
                }
            } else {
                eventoModificar.setPersonasInscritas(nuevosInscritos);
                vista.mensajeConfirmacion();
            }
            break;

        case 7:
            vista.operacionCancelada();
            break;

        default:
            vista.opcionNoValida();
        }
    }

    private void eliminarEvento() {
        Scanner s = new Scanner(System.in);

        Evento[] eventos = this.evento.obtenerTodosLosEventos();
        if (eventos.length == 0) {
            vista.noHayEventos();
            return;
        }

        vista.tituloEventosDisponibles();
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null) {

                String fechaFormateada = FuncionesFechas.convertirLocalDateString(eventos[i].getFecha());
                vista.mostrarEventoTabla(eventos[i].getNombre(), eventos[i].getCategoria().toString(), fechaFormateada);
            }
        }

        vista.pedirNombreEvento();
        String nombreEvento = s.nextLine();

        if (evento.eliminarEvento(nombreEvento)) {
            vista.eventoEliminado();
        } else {
            vista.eventoNoEncontrado();
        }
    }

    // PARA ASISTENTES
    private void menuMisEventosAsistente() throws InterruptedException{
        Scanner s = new Scanner(System.in);
        int opcion;

        do {
            vista.menuOrganizadorEventos();
            opcion = Integer.parseInt(s.nextLine());

            switch (opcion){
                case 1:
                    verEventosOrganizador();
                    break;

                case 2:
                    crearNuevoEvento();
                    break;

                case 3:
                    modificarEvento();
                    break;

                case 4:
                    eliminarEvento();
                    break;

                case 5:
                    break;

                default:
                    vista.opcionNoValida();
            }
        }while (opcionEvento !=5);
    }



}
