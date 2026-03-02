package FernanEvents.controlador;

import FernanEvents.modelo.Evento;
import FernanEvents.modelo.Usuario;
import FernanEvents.modelo.utilidades.Cadenas;
import FernanEvents.modelo.utilidades.EnvioGMail;
import FernanEvents.modelo.utilidades.FuncionesFechas;
import FernanEvents.vista.VistaFernan;

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
                    //REGISTRO DE USUARIOS
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
        Usuario usuario = null;

        while(true){
            vista.pedirCorreoLoguin();
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
            usuario.setBloqueado(true);
            vista.seBloqueaUsuario();
            return false;
        }

        String codigoVerificacion = Cadenas.generarCodigoVerificacion();
        String destinatario = "flenmar918@g.educaand.es";
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
        vista.cabeceraUsuariosBloqueados();
        for (int i = 0; i < modelo.getNumUsuarios(); i++) {
            if(listaUsuarios[i] != null && listaUsuarios[i].isBloqueado()){
                vista.mostrarUsuarioBloqueado(i, listaUsuarios[i].getNombre());
            }
        }
        vista.pideNumeroUsuario();
        int opcionPanelBloqueo = Integer.parseInt(s.nextLine());
        if(opcionPanelBloqueo > 0 && opcionPanelBloqueo < listaUsuarios.length &&
                listaUsuarios[opcionPanelBloqueo] != null){

            if(listaUsuarios[opcionPanelBloqueo].isBloqueado()){
                listaUsuarios[opcionPanelBloqueo].setBloqueado(false);
                return true;
            }
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
                        vista.sumaSaldoOK(usuarioLogueado.getSaldo());
                    }else{
                        vista.mensajeError();
                    }
                    break;

                case 3:
                    if(retiraSaldo()){
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
        usuarioLogueado.setSaldo(usuarioLogueado.getSaldo() + saldoASumar);
        return true;
    }

    /**
     * Función que retira la cantidad de saldo que introduzca el usuario actual a su cartera digital
     */
    private boolean retiraSaldo(){
        Scanner s = new Scanner(System.in);
        vista.preguntaRetiraSaldo();
        float saldoARetirar = Float.parseFloat(s.nextLine());

        if(usuarioLogueado.getSaldo() == 0 || saldoARetirar > usuarioLogueado.getSaldo()){
            return false;
        }else{
            usuarioLogueado.setSaldo(usuarioLogueado.getSaldo() - saldoARetirar);
            return true;

        }
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
        }else{
            vista.pedirNuevoNombre();
            String nuevoNombreUsuario = s.nextLine();

            if(modelo.buscarPorNombre(nuevoNombreUsuario) != null){
                vista.nombreYaEnUso(nuevoNombreUsuario);
            }else{
                usuarioCambio.setNombre(nuevoNombreUsuario);
                return true;
            }
        }
        return false;
    }

    private boolean cambiaPasswordAdmin(){
        Scanner s = new Scanner(System.in);
        vista.pedirNombreUsuario();
        String nombreUsuarioCambio = s.nextLine();

        Usuario usuarioCambio = modelo.buscarPorNombre(nombreUsuarioCambio);

        if(usuarioCambio == null){
            vista.errorAlBuscarNombre();
        }else{
            vista.pedirNuevaPassword();
            String nuevaPassword = s.nextLine();

            usuarioCambio.setPassword(nuevaPassword);
            return true;
        }
        return false;
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
        }else{
            usuarioLogueado.setNombre(nuevoNombre);
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
            usuarioLogueado.setPassword(nuevaPassword);
            return true;
        }
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
                case 1://ver eventos orgnizador
                    break;

                case 2://crear nuevo evento
                    break;

                case 3://modificar evento
                    break;

                case 4://eliminar evento
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
                vista.mostrarEventoTabla(evento.getNombre(), evento.getCategoria().toString(), evento.getFecha().toString());
                vista.mostrarEvento(evento.getNombre(), evento.getCategoria().toString(), evento.getFecha().toString());
            }
        }
    }

}
