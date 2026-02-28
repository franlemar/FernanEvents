package FernanEvents.modelo;

public class Asistente extends Usuario{

    private Entrada[] misEntradas;
    private int numEntradas;

    public Asistente(String nombre, String correo, String password){
        super(nombre, correo, password, Rol.ASISTENTE);
        misEntradas = new Entrada[10];
        numEntradas = 0;
    }

    public Entrada[] getMisEntradas() {
        return misEntradas;
    }

    public void setMisEntradas(Entrada[] misEntradas) {
        this.misEntradas = misEntradas;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }
}
