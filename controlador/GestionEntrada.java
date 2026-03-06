package FernanEvents.controlador;

import FernanEvents.modelo.Entrada;
import FernanEvents.modelo.EntradasTipo;

public class GestionEntrada {
    private final EntradasTipo[] Entradas;
    EntradasTipo[] tiposEntrada;
    int contadorEntradas;
    Entrada entrada;

    public GestionEntrada(Entrada entrada){
        this.Entradas=new EntradasTipo[3];
        this.entrada= entrada;
        this.contadorEntradas = 0;
    }

    public EntradasTipo[] getEntradas() {return Entradas;}

    public EntradasTipo[] getTipoEntradas() {return tiposEntrada;}

    public void setTipoEntradas(EntradasTipo[] tipoEntradas) {this.tiposEntrada = tipoEntradas;}

    public int getContadorEntradas() {return contadorEntradas;}

    public void setContadorEntradas(int contadorEntradas) {this.contadorEntradas = contadorEntradas;}

    public Entrada getEntrada() {return entrada;}

    public void setEntrada(Entrada entrada) {this.entrada = entrada;}

//    public void aniadirTipoEntrada(String nombre, float precio, int cantidad){
//        if (numTipos < 3){
//            tiposEntrada[numTipos++] = new EntradasTipo(nombre, precio, cantidad);
//        }
//    }
//
//    public EntradasTipo getTipoEntrada(String nombreTipo) {
//        for (int i = 0; i < numTipos; i++) {
//            if (tiposEntrada[i].getNombre().equalsIgnoreCase(nombreTipo)) {
//                return tiposEntrada[i];
//            }
//        }
//        return null;
//    }
//
//    public boolean venderEntradas(String nombreTipo, int cantidad){
//        EntradasTipo tipo = getTipoEntrada(nombreTipo);
//        if (tipo != null && tipo.getCantidadDisponible() >= cantidad){
//            tipo.setCantidadDisponible(tipo.getCantidadDisponible() - cantidad);
//
//            this.personasInscritas = personasInscritas + cantidad;
//            return true;
//        }
//        return false;
//    }
}
