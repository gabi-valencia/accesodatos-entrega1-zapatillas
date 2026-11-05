import java.io.File;
import java.util.Scanner;

public class ModificarFichero_GabrielValencia {
    public static void main(String[] args) {

        // Ruta hacia nuestro fichero ya creado:
        String conexionFichero = "InformacionZapatillas.dat";
        File f = new File(conexionFichero);

        // Validaciones a realizar, en este caso tenemos que leer nuestros datos y
        // escribir sobre ellos.

        if (f.exists()) {
            if (f.isDirectory()) {
                System.out.println("Se esta trabajando con un directorio y no sobre un fichero");
                return;
            }
            if (!f.canRead()) {
                System.out.println("El fichero no cuenta con permisos de lectura");
                return;
            }
            if (!f.canWrite()) {
                System.out.println("El fichero no tiene permisos de escritura");
                return;
            }
            if (f.length() == 0) {
                System.out.println("El fichero no cuenta con informacion para trabajar");
                return;
            }

        } else {
            System.out.println("El fichero no existe y no ha pasado las validaciones correspondientes");
            return;
        }

        // Debemos pedir al usuario que nos introduzca el codigo de la zapatilla que
        // quiere modificar:
        System.out.println("Introduce el codigo de la zapatilla: ");
        Scanner sc = new Scanner(System.in);
        int leerNumero = sc.nextInt();
        System.out.println(leerNumero);

        // Debemos crear una variable donde guardemos cuantas zapatillas tenemos:

        // Lo que ocupa cada zapatilla en el fichero
        int tamañoZapatilla = 129;

        // Lo que ocupa el fichero entero. length() devuelve un long,
        // así que lo paso a int poniendo (int) delante
        int bytesFichero = (int) f.length();

        // Si divido lo que ocupa el fichero entre lo que ocupa una zapatilla,
        // me sale cuántas zapatillas hay guardadas
        int totalZapatillas = bytesFichero / tamañoZapatilla;

        // Compruebo que el código existe. Tiene que estar entre 1 y el total
        // de zapatillas, si no, aviso y salgo del programa
        if (leerNumero < 1 || leerNumero > totalZapatillas) {
            System.out.println("No existe el codigo de zapatilla que estas buscando en nuestra informacion");
            return;
        }
    }
}
