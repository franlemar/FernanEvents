package FernanEvents.vista;

public class VistaFernan{

    private static Estilos estilo = new Estilos();
    //Métodos

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
