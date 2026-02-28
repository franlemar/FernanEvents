package FernanEvents.controlador;

import FernanEvents.modelo.Usuario;
import FernanEvents.vista.VistaFernan;

import java.util.Scanner;

public class ControladorFernan {

    private GestionUsuario modelo;
    private VistaFernan vista;

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
                    break;

                case 2:
                    break;

                case 3:
                    break;

                default:
                    vista.notificacion("Opción introducida no válida \n");
            }

        }while(opcionMenu != 3);
        vista.mostrarDespedida();
    }

    private void LoginUsuario(){
        Scanner s = new Scanner(System.in);
        int intentosRestantes = 3;
        boolean passwordCorrecta = false;
        Usuario usuario = null;

        while(true){
            vista.pedirCorreoLoguin();
            String correoUsuario = s.nextLine().toLowerCase();

            usuario = modelo.buscaUsuarioPorCorreo(correoUsuario);

            if(usuario == null){
                vista.mensajeNoExisteCorreo();
                continue;
            }

            if(usuario.isBloqueado()){
                vista.
                return;
            }
        }
    }
}
