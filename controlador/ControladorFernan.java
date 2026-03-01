package FernanEvents.controlador;

import FernanEvents.modelo.Usuario;
import FernanEvents.modelo.utilidades.Cadenas;
import FernanEvents.modelo.utilidades.EnvioGMail;
import FernanEvents.vista.VistaFernan;

import java.util.Scanner;

public class ControladorFernan {

    private GestionUsuario modelo;
    private VistaFernan vista;
    private Usuario usuarioLogueado;

    public ControladorFernan(GestionUsuario modelo, VistaFernan vista){
        this.modelo = modelo;
        this.vista = vista;
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
                    break;

                case 2:
                    break;

                case 3:
                    gestionaCarteraDigital();
                    break;

                case 4:
                    break;

                case 5:
                    break;

                default:
                    vista.opcionNoValida();

            }
        }while(opcionMenu != 5);
        vista.cerrarSesion(usuarioLogueado.getRol());
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
                    break;

                case 4:
                    break;

                default:
                    vista.opcionNoValida();
            }

        }while(opcionMenu != 4);
        vista.cerrarSesion(usuarioLogueado.getRol());
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
                    break;

                case 6:
                    break;

                default:
                    vista.opcionNoValida();
            }

        }while(opcionMenu != 6);
        vista.cerrarSesion(usuarioLogueado.getRol());
    }

    //*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.CARTERA DE USUARIOS.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*

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
                    sumaSaldo();
                    break;

                case 3:
                    retiraSaldo();
                    break;

                case 4:
                    break;

                default:
                    vista.opcionNoValida();
            }
        }while(opcionMenu != 4);
    }

    private void sumaSaldo(){
        Scanner s = new Scanner(System.in);
        vista.preguntaSumaSaldo();
        float saldoASumar = Float.parseFloat(s.nextLine());
        usuarioLogueado.setSaldo(usuarioLogueado.getSaldo() + saldoASumar);
        vista.sumaSaldoOK(usuarioLogueado.getSaldo());
    }

    private void retiraSaldo(){
        Scanner s = new Scanner(System.in);
        vista.preguntaRetiraSaldo();
        float saldoARetirar = Float.parseFloat(s.nextLine());

        if(usuarioLogueado.getSaldo() == 0 || saldoARetirar > usuarioLogueado.getSaldo()){
            vista.mensajeError();
        }else{
            usuarioLogueado.setSaldo(usuarioLogueado.getSaldo() - saldoARetirar);
            vista.retiraSaldoOK(usuarioLogueado.getSaldo());
        }
    }


}
