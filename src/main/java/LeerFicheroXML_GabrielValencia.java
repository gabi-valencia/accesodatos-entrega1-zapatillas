import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeerFicheroXML_GabrielValencia {
    public static void main(String[] args) {
        // Ruta hacia nuestro fichero de zapatillas:
        String rutaZapatillas = "zapatillas.xml";
        File f = new File(rutaZapatillas);

        // Validaciones --> En este caso vamos a realizar de lectura:
        if (f.exists()) {
            if (f.isDirectory()) {
                System.out.println("No es un fichero, si no un directorio.");
                return;
            }
            if (!f.canRead()) {
                System.out.println("El fichero no cuenta con permisos de lectura");
                return;
            }
            if (f.length() == 0) {
                System.out.println("El fichero no cuenta con contenido para poder leerlo");
                return;
            }
            System.out.println("El fichero existe y se podrá trabajar con el");

        } else {
            System.out.println("El fichero no existe");
            return;
        }
        // ---------- LEER XML ----------
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("zapatillas", List.class);
        xstream.alias("zapatilla", Zapatilla.class);

        // Tenemos que dar permiso a XStream para crear objetos de la clase zapatilla
        xstream.allowTypes(new Class[] { Zapatilla.class });
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(f))) {
            // fromXML devuelve un Object con (List<Zapatilla>) le digo a Java
            // que lo trate como una lista de zapatillas para poder usarla.
            List<Zapatilla> guardaDatos = (List<Zapatilla>) xstream.fromXML(isr);
            for (int i = 0; i < guardaDatos.size(); i++) {
                // Sacamos cuantas zapatillas hay en la lista
                System.out.println(guardaDatos.get(i)); // get(i) saca la zapatilla de la posición i de la lista.

            }
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("Error general de exception");
        }
    }
}
