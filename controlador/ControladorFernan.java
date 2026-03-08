package FernanEvents.controlador;

import FernanEvents.modelo.*;
import FernanEvents.modelo.utilidades.Cadenas;
import FernanEvents.modelo.utilidades.EnvioGmail;
import FernanEvents.modelo.utilidades.FuncionesFechas;
import FernanEvents.vista.VistaFernan;

import java.util.Scanner;

public class ControladorFernan {

    private GestionUsuario modeloUsu;
    private VistaFernan vista;
    private GestionEvento modeloEve;
    private Usuario usuarioLogueado;

    public ControladorFernan(GestionUsuario modeloUsu, VistaFernan vista, GestionEvento modeloEve){
        this.modeloUsu = modeloUsu;
        this.vista = vista;
        this.modeloEve = modeloEve;
    }

    /**
     * Inicia FernanEvents mostrando las animaciones de carga del programa
     */
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

    /**
     * Gestiona el proceso de login del Usuario y envia el correspondiente correo de verificacion con el token
     */
    private boolean LoginUsuario(){
        Scanner s = new Scanner(System.in);
        int intentosRestantes = 3;
        boolean passwordCorrecta = false;
        Usuario usuario;

        while(true){
            vista.pedirCorreo();
            String correo = s.nextLine().toLowerCase();
            usuario = modeloUsu.buscaUsuarioPorCorreo(correo);

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
            modeloUsu.actualizaEstadoBloqueo(usuario.getCorreo(), true);
            vista.seBloqueaUsuario();
            return false;
        }

        String codigoVerificacion = Cadenas.generarCodigoVerificacion();
        String destinatario = "flenmar918@g.educaand.es";
        String asunto = "Código de verificación - Inicio de sesión";
        String cuerpo = EnvioGmail.plantillaLoginAdmin(usuario.getNombre(), codigoVerificacion);

        EnvioGmail.enviarConGMail(destinatario, asunto, cuerpo);
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
    /**
     * Gestiona el proceso necesario para registrar un usuario nuevo pidiendo los datos necesarios
     */
    private boolean registrarUsuario(){
        Scanner s = new Scanner(System.in);
        vista.tituloRegistro();
        vista.pedirNombreRegistro();
        String nombreRegistro = s.nextLine();
        String correoRegistro = registroCorreo();
        String passwordRegistro = obtenerPasswordValida();
        Rol rolCorrecto = registroRol();

        if(modeloUsu.buscarPorNombre(nombreRegistro) != null || modeloUsu.buscaUsuarioPorCorreo(correoRegistro) != null){
            vista.nombreOCorreoEnUso();
            return false;
        }
        String codigoVerificacion = Cadenas.generarCodigoVerificacion();
        enviarTokenRegistro(nombreRegistro, correoRegistro, codigoVerificacion);
        return verificaTokenYRegistro(codigoVerificacion, nombreRegistro, correoRegistro, passwordRegistro, rolCorrecto);
    }

    /**
     * Pide y valida el correo durante el registro del usuario
     */
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

    /**
     * Pide el rol del usuario durante el registro
     */
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

    /**
     * Se encarga de enviar el token de verificacion por correo necesario para el registro
     */
    private void enviarTokenRegistro(String nombreRegistro, String correoRegistro, String codigoVerificacion){
        String cuerpo = EnvioGmail.plantillaRegistroUsuario(nombreRegistro, codigoVerificacion);
        EnvioGmail.enviarConGMail(correoRegistro, "Token único de inicio de sesión", cuerpo);
        vista.correoVerificacionEnviado();
    }

    /**
     * Verifica el token y completa el registro del usuario
     */
    private boolean verificaTokenYRegistro(String codigoVerificacion, String nombreRegistro, String correoRegistro, String passwordRegistro, Rol rolCorrecto){
        Scanner s = new Scanner(System.in);
        boolean tokenVerificado = false;
        while (!tokenVerificado) {
            vista.pedirToken();
            String tokenRegistro = s.nextLine();

            if (!tokenRegistro.equals(codigoVerificacion)) {
                vista.tokenIncorrecto();
            } else {
                if (modeloUsu.getNumUsuarios() == modeloUsu.getUsuarios().length) {
                    modeloUsu.aumentarCapacidad();
                }

                Usuario nuevoUsuario;
                if (rolCorrecto.equals(Rol.ORGANIZADOR)) {
                    nuevoUsuario = new Organizador(nombreRegistro, correoRegistro, passwordRegistro);
                } else {
                    nuevoUsuario = new Asistente(nombreRegistro, correoRegistro, passwordRegistro);
                }

                modeloUsu.aniadirUsuario(nuevoUsuario);
                tokenVerificado = true;
            }
        }
        return true;
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.MENÚS PRINCIPALES DE USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

    /**
     * Según el rol del usuario logueado muestra su menú correspondiente
     */
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

    /**
     * Muestra el menú principal del usuario Administrador
     */
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
                    modeloEve.mostrarEventos();
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

    /**
     * Muestra el menú principal del usuario Organizador
     */
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

    /**
     * Muestra el menú principal del usuario Asistente
     */
    private void menuPrincipalAsistente() throws InterruptedException {
        Scanner s = new Scanner(System.in);
        int opcionMenu;

        do{
            vista.menuAsistente();
            opcionMenu = Integer.parseInt(s.nextLine());

            switch(opcionMenu){
                case 1:
                    Asistente asistenteActual = (Asistente) usuarioLogueado;
                    vista.cabeceraMisEventos();
                    int totalEventosInscritos = asistenteActual.getContadorInscripciones();

                    if(totalEventosInscritos == 0){
                        vista.mensajeNoInscrito();
                    }else{
                        for (int i = 0; i < totalEventosInscritos; i++) {
                            String nombreEvento = asistenteActual.getEventosInscrito()[i];
                            int cantidadEntradas = asistenteActual.getCantidadEntradasEvento()[i];

                            Evento evento = modeloEve.buscarEventoPorNombre(nombreEvento);

                            if(evento != null){
                                vista.mostrarEventoTabla(nombreEvento, evento.getCategoria().toString(),
                                        FuncionesFechas.convertirLocalDateString(evento.getFecha()));
                                vista.mensajeEntradasCompradas(cantidadEntradas);
                            }
                        }
                    }

                    break;

                case 2:
                    modeloEve.mostrarEventos();
                    vista.pedirNombreEventoInscribir();
                    String eventoAInscribir = s.nextLine();
                    Evento eventoSeleccionado = modeloEve.buscarEventoPorNombre(eventoAInscribir);

                    if(eventoSeleccionado != null){
                        vista.menuEntradaTipo();
                        int opcionTipoEntrada = Integer.parseInt(s.nextLine()) - 1;

                        if(opcionTipoEntrada < 0 || opcionTipoEntrada > 3){
                            vista.opcionNoValida();
                        }else{
                            Entrada tipoEntradaElegido = eventoSeleccionado.getTiposDeEntrada()[opcionTipoEntrada];
                            vista.mostrarDetallePreCompra(tipoEntradaElegido.getCategoria().toString(), tipoEntradaElegido.getPrecio());
                            int cantidadEntradas = Integer.parseInt(s.nextLine());

                            Asistente asistente = (Asistente) usuarioLogueado;
                            int entradasYaCompradas = asistente.getNumEntradasEvento(eventoSeleccionado.getNombre());

                            if(entradasYaCompradas + cantidadEntradas > 4){
                                vista.errorLimiteEntradas(entradasYaCompradas);
                            }else if(cantidadEntradas <= 0){
                                vista.errorCantidadNoValida();
                            }else{
                                float precioTotal = cantidadEntradas * tipoEntradaElegido.getPrecio();

                                if(tipoEntradaElegido.getCantidadDisponible() < cantidadEntradas){
                                    vista.noHayStockEntradas();
                                }else if(usuarioLogueado.getSaldo() < precioTotal){
                                    vista.saldoInsuficiente();
                                }else{
                                    vista.avisoPrecioTotal(cantidadEntradas, precioTotal);
                                    vista.preguntaConfirmacionCompra(usuarioLogueado.getSaldo());
                                    String mensajeConfirmaCompra = s.nextLine();

                                    if(mensajeConfirmaCompra.equalsIgnoreCase("si")){
                                        modeloUsu.quitarSaldo(usuarioLogueado.getCorreo(), precioTotal);
                                        modeloUsu.aniadirSaldo(eventoSeleccionado.getOrganizador().getCorreo(), precioTotal * 0.90f);
                                        modeloUsu.aniadirSaldo("admin@fernanevents.com", precioTotal * 0.10f);
                                        modeloEve.controlaStockCorrecto(eventoSeleccionado, opcionTipoEntrada, cantidadEntradas);
                                        asistente.registraCompraEntrada(eventoSeleccionado.getNombre(), cantidadEntradas);
                                        vista.mensajeConfirmacion();

                                    }else{
                                        vista.operacionCancelada();
                                    }
                                }
                            }
                        }
                    }else{
                        vista.eventoNoEncontrado();
                    }
                    break;

                case 3:
                    gestionaCarteraDigital();
                    break;

                case 4:
                    invitarAmigos();
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
    /**
     * Controla el panel de administrador para usuarios bloqueados
     */
    private void panelControlAdmin(){
        if(!modeloUsu.confirmaUsuariosBloqueados()){
            vista.noHayUsuariosBloqueados();
        }else{
            if(gestionaUsuariosBloqueados()){
                vista.mensajeConfirmacion();
            }else{
                vista.mensajeError();
            }
        }
    }

    /**
     * Permite gestionar el desbloqueo de usuarios
     */
    private boolean gestionaUsuariosBloqueados(){
        Scanner s = new Scanner(System.in);
        Usuario[] listaUsuarios = modeloUsu.getUsuarios();
        vista.tituloUsuariosBloqueados();
        for (int i = 0; i < modeloUsu.getNumUsuarios(); i++) {
            if(listaUsuarios[i] != null && listaUsuarios[i].isBloqueado()){
                vista.mostrarUsuarioBloqueado(i, listaUsuarios[i].getNombre());
            }
        }
        vista.pideNumeroUsuario();
        int opcionPanelBloqueo = Integer.parseInt(s.nextLine());
        if(opcionPanelBloqueo > 0 && opcionPanelBloqueo < listaUsuarios.length &&
                listaUsuarios[opcionPanelBloqueo] != null){

            Usuario usuarioQuitarBloqueo = listaUsuarios[opcionPanelBloqueo];
            return modeloUsu.actualizaEstadoBloqueo(usuarioQuitarBloqueo.getCorreo(), false);
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
                        usuarioLogueado = modeloUsu.buscaUsuarioPorCorreo(usuarioLogueado.getCorreo());
                        vista.sumaSaldoOK(usuarioLogueado.getSaldo());
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 3:
                    if(retiraSaldo()){
                        usuarioLogueado = modeloUsu.buscaUsuarioPorCorreo(usuarioLogueado.getCorreo());
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
        return modeloUsu.aniadirSaldo(usuarioLogueado.getCorreo(), saldoASumar);
    }

    /**
     * Función que retira la cantidad de saldo que introduzca el usuario actual a su cartera digital
     */
    private boolean retiraSaldo(){
        Scanner s = new Scanner(System.in);
        vista.preguntaRetiraSaldo();
        float saldoARetirar = Float.parseFloat(s.nextLine());
        return modeloUsu.quitarSaldo(usuarioLogueado.getCorreo(), saldoARetirar);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.OPCION CONFIGURACION DE ADMINISTRADOR.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Gestiona el menú de configuración del administrador
     */
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

    /**
     * Función que nos permite cambiar el nombre de un usuario
     */
    private boolean cambiaNombreAdmin(){
        Scanner s = new Scanner(System.in);
        vista.pedirNombreUsuario();
        String nombreUsuarioCambio = s.nextLine();
        Usuario usuarioCambio = modeloUsu.buscarPorNombre(nombreUsuarioCambio);

        if(usuarioCambio == null){
            vista.errorAlBuscarNombre();
            return false;
        }

        vista.pedirNuevoNombre();
        String nuevoNombreUsuario = s.nextLine();

        if(modeloUsu.buscarPorNombre(nuevoNombreUsuario) != null){
            vista.nombreYaEnUso(nuevoNombreUsuario);
            return false;
        }
        return modeloUsu.actualizarNombre(usuarioCambio.getCorreo(), nuevoNombreUsuario);
    }

    /**
     * Función que nos permite cambiar el nombre de un usuario
     */
    private boolean cambiaPasswordAdmin(){
        Scanner s = new Scanner(System.in);
        vista.pedirNombreUsuario();
        String nombreUsuarioCambio = s.nextLine();
        Usuario usuarioCambio = modeloUsu.buscarPorNombre(nombreUsuarioCambio);

        if(usuarioCambio == null){
            vista.errorAlBuscarNombre();
            return false;
        }

        vista.pedirNuevaPassword();
        String nuevaPassword = s.nextLine();
        return modeloUsu.actualizarContrasena(usuarioCambio.getCorreo(), nuevaPassword);
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.OPCIÓN CONFIGURACIÓN RESTO DE USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Gestiona el menú de configuracion del usuario logueado
     */
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

    /**
     * Función que le permite al usuario cambiar su propio nombre
     */
    private boolean cambiaNombreUsuario(){
        Scanner s = new Scanner(System.in);
        vista.pedirNuevoNombre();
        String nuevoNombre = s.nextLine();
        if(modeloUsu.buscarPorNombre(nuevoNombre) != null){
            vista.nombreYaEnUso(nuevoNombre);
            return false;
        }

        if(modeloUsu.actualizarNombre(usuarioLogueado.getCorreo(), nuevoNombre)){
            usuarioLogueado = modeloUsu.buscaUsuarioPorCorreo(usuarioLogueado.getCorreo());
            return true;
        }
        return false;
    }

    /**
     * Función que le permite al usuario cambiar su contraseña
     */
    private boolean cambiaPasswordUsuario(){
        Scanner s = new Scanner(System.in);
        vista.pedirPasswordActual();
        String passwordActual = s.nextLine();

        if(!usuarioLogueado.getPassword().equals(passwordActual)){
            vista.passwordActualIncorrecta();
            return false;
        }else{
            String nuevaPassword = obtenerPasswordValida();

            if(modeloUsu.actualizarContrasena(usuarioLogueado.getCorreo(), nuevaPassword)){
                this.usuarioLogueado = modeloUsu. buscaUsuarioPorCorreo(usuarioLogueado.getCorreo());
                return true;
            }
        }
        return false;
    }

    /**
     * Función que pide una contraseña que sea segura
     */
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

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.INVITAR A UN AMIGO A FERNANEVENTS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Gestiona el menú para invitar amigos a la plataforma
     */
    private void invitarAmigos(){
        Scanner s = new Scanner(System.in);
        int opcionMenu;

        do{
            vista.menuInvitarAmigo();
            opcionMenu = Integer.parseInt(s.nextLine());
            switch(opcionMenu){
                case 1:
                    if(!modeloUsu.tieneAmigosReferidos(usuarioLogueado)){
                        vista.noHayAmigosReferidos();
                    }else{
                        listarAmigosReferidos();
                    }
                    break;

                case 2:
                    vista.pedirCorreoAmigoReferido();
                    String correoAmigoReferido = s.nextLine().trim();
                    if(!modeloUsu.aniadirAmigoReferido(usuarioLogueado, correoAmigoReferido)){
                        vista.correoMalEscrito();
                    }else{
                        String destinatario = correoAmigoReferido;
                        String asunto = "Un amigo/a te ha invitado a formar parte de FernanEvents";
                        String cuerpo = EnvioGmail.plantillaInvitarAmigo(correoAmigoReferido, usuarioLogueado.getNombre());
                        EnvioGmail.enviarConGMail(destinatario, asunto, cuerpo);
                        vista.registroAmigoReferidoOK(correoAmigoReferido);
                    }
                    break;

                case 3:
                    break;

                default:
                    vista.opcionNoValida();
            }
        }while(opcionMenu != 3);
    }

    /**
     * Muestra la lista de amigos referidos
     */
    private void listarAmigosReferidos(){
        Asistente asistente = (Asistente) usuarioLogueado;
        String[] listadoAmigosReferidos = asistente.getAmigosReferidos();
        int totalAmigos = asistente.getNumAmigosReferidos();
        vista.cabeceraListadoAmigosReferidos(totalAmigos);
        for (int i = 0; i < totalAmigos; i++) {
            vista.listarAmigo(i + 1, listadoAmigosReferidos[i]);
        }
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.EVENTOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*
    /**
     * Gestiona el menú de eventos para el organizador
     */
    private void menuEventosOrganizador(){
        Scanner s = new Scanner(System.in);
        int opcionMenu;
        do{
            vista.menuOrganizadorEventos();
            opcionMenu = Integer.parseInt(s.nextLine());
            switch(opcionMenu){
                case 1:
                    modeloEve.mostrarEventos();
                    break;

                case 2:
                    Evento nuevoEvento = modeloEve.crearEvento();
                    nuevoEvento.setOrganizador(usuarioLogueado);

                    if(modeloEve.aniadirEvento(nuevoEvento)){
                        vista.mensajeConfirmacion();
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 3:
                    modeloEve.modificarEvento();
                    break;

                case 4:
                    modeloEve.eliminarEvento();
                    break;

                case 5:
                    break;

                default:
                    vista.opcionNoValida();
            }
        }while(opcionMenu != 5);
    }








}
