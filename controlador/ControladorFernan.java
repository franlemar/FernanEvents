package FernanEvents.controlador;

import FernanEvents.modelo.*;
import FernanEvents.modelo.utilidades.Cadenas;
import FernanEvents.modelo.utilidades.EnvioGMail;
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

        if(modeloUsu.buscarPorNombre(nombreRegistro) != null || modeloUsu.buscaUsuarioPorCorreo(correoRegistro) != null){
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
                    //eliminar un evento --> llamar a métodos de gestión de eventos (CRUD)
                    // en vista hay un metodo

                    //reordenar eventos al eliminar uno. tema 5 hay ejemplos
                    break;

                case 5:
                    break;

                default:
                    vista.opcionNoValida();
            }
        }while(opcionMenu != 5);
    }








}
