import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CrearFicheroXML_GabrielValencia {
    public static void main(String[] args) {

        // Rutabilidad hacia el fichero .dat:
        String fichero = "InformacionZapatillas.dat";
        File f = new File(fichero);

        // Validaciones --> En este caso vamos a realizar validaciones de lectura
        if (f.exists()) {
            if (f.isDirectory()) {
                System.out.println("Estas tratando con un directorio y no con un fichero");
                return;

            }
            if (!f.canRead()) {
                System.out.println("El fichero no tiene permisos de lectura");
                return;

            }
            if (f.length() == 0) {
                System.out.println("El fichero que intentar leer esta vacio");
                return;

            }
            System.out.println("El fichero existe y se trabajará con el");

        } else {
            System.out.println("El fichero con el que intentas trabajar no existe.");
            return;
        }
        // Array para poder leer la informacion

        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            while (true) {

            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido leer el fichero");

        } catch (EOFException e) {
            System.out.println("Se ha terminado de leer la informacion del fichero.");
        } catch (IOException e) {
            System.out.println("Error general de exception: " + e.getMessage());
        }
        // Xstring y lectura
    }
}
