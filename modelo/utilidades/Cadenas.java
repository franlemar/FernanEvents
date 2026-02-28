package FernanEvents.modelo.utilidades;

public class Cadenas {

    /**
     * Función que comprueba que dos contraseñas sean iguales. Recibe por parámetro las dos contraseñas a comparar como String y devuelve true si ambas son iguales.
     */
    public static boolean esIgualContrasenia(String contrasenia1, String contrasenia2) {
        return contrasenia1.equals(contrasenia2);
    }

    /**
     * Función que comprueba la fortaleza de una contraseña y dice si una contraseña es segura o no. Recibe por parámetro la contraseña a comprobar y va analizando si la longitud es menor a 8, si tiene alguna minúscula, mayúscula, número o símbolos
     */
    public static boolean esContraseniaFuerte(String contrasenia) {
        int longitudMinima = 8;
        boolean tieneMinuscula = false;
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
        boolean tieneSimbolo = false;
        String simbolos = "-_.,*+@";

        if (contrasenia.length() < longitudMinima) return false;

        for (int i = 0; i < contrasenia.length(); i++) {
            char caracter = contrasenia.charAt(i);
            if(Character.isLowerCase(caracter)) tieneMinuscula = true;
            if(Character.isUpperCase(caracter)) tieneMayuscula = true;
            if(Character.isDigit(caracter)) tieneNumero = true;
            if(simbolos.indexOf(caracter) != -1) tieneSimbolo = true;

            if(tieneMinuscula && tieneMayuscula && tieneNumero && tieneSimbolo) break;
        }
        return tieneMinuscula && tieneMayuscula && tieneNumero && tieneSimbolo;
    }

    /**
     * Función que comprueba que la longitud de un texto sea válido, recibiendo por parámetro el texto para comprobar y los límites mínimos y máximos
     */
    public static boolean esTextoValido(String texto, int minimo, int maximo){
        if (texto.length() < minimo || texto.length() > maximo) return false;
        else return true;
    }

    static void main(String[] args) {

        System.out.println(esTextoValido("Esto es una prueba", 15, 300));
    }
}
