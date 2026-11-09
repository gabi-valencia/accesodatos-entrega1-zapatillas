import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class CrearFicheroXML_GabrielValencia {
    public static void main(String[] args) {

        // Ruta hacia el fichero .dat:
        String fichero = "InformacionZapatillas.dat";
        File f = new File(fichero);

        // Validaciones de lectura
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
                System.out.println("El fichero que intentas leer esta vacio");
                return;
            }
            System.out.println("El fichero existe y se trabajará con el");

        } else {
            System.out.println("El fichero con el que intentas trabajar no existe.");
            return;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {

            // Lista donde voy guardando las zapatillas que leo del .dat.
            // Es como un array, pero crece sola y no hace falta decirle el tamaño
            List<Zapatilla> zapatillas = new ArrayList<>();

            // El while va dentro de su propio try porque es un bucle infinito que solo
            // termina cuando se acaba el fichero y salta un EOFException.
            // Así el error se queda aquí y el programa sigue hasta crear el XML
            try {
                while (true) {

                    // ---------- CÓDIGO ----------
                    int codigo = dis.readInt();

                    // ---------- NOMBRE ----------
                    char nombre[] = new char[20];
                    for (int i = 0; i < nombre.length; i++) {
                        nombre[i] = dis.readChar();
                    }
                    String conversionNombre = new String(nombre).trim();

                    // ---------- MARCA ----------
                    char marca[] = new char[20];
                    for (int i = 0; i < marca.length; i++) {
                        marca[i] = dis.readChar();
                    }
                    String conversionMarca = new String(marca).trim();

                    // ---------- PRECIO ----------
                    float leerPrecio = dis.readFloat();

                    // ---------- EN STOCK ----------
                    boolean verStock = dis.readBoolean();

                    // ---------- TIENDA ----------
                    char tienda[] = new char[20];
                    for (int i = 0; i < tienda.length; i++) {
                        tienda[i] = dis.readChar();
                    }
                    String conversionTienda = new String(tienda).trim();

                    // ---------- MOSTRAR LA ZAPATILLA ----------
                    System.out.println("Zapatilla -> Código: " + codigo
                            + " | " + conversionNombre
                            + " | " + conversionMarca
                            + " | " + leerPrecio + " euros"
                            + " | En stock: " + verStock
                            + " | " + conversionTienda);

                    // ---------- GUARDAR LA ZAPATILLA EN LA LISTA ----------
                    // Con los datos leídos creo un objeto Zapatilla y lo añado a la lista
                    zapatillas.add(new Zapatilla(codigo, conversionNombre, conversionMarca,
                            leerPrecio, verStock, conversionTienda));
                }

            } catch (EOFException e) { // End of File, salta cuando ya no quedan zapatillas
                System.out.println("Se ha terminado de leer la informacion del fichero.");
            }

            // ---------- CREAR EL XML CON XSTREAM ----------
            // DomDriver hace que el XML salga ordenado con saltos de línea
            XStream xstream = new XStream(new DomDriver());

            // Los alias cambian el nombre de las etiquetas. Sin ellos, XStream pondría
            // el nombre de las clases de Java (por ejemplo "list" o "Zapatilla")
            xstream.alias("zapatillas", List.class);
            xstream.alias("zapatilla", Zapatilla.class);

            // Guardo la lista entera en el fichero XML
            try (FileWriter fw = new FileWriter("InformacionZapatillas.xml")) {
                xstream.toXML(zapatillas, fw);
            }
            System.out.println("Fichero InformacionZapatillas.xml creado correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido leer el fichero");
        } catch (IOException e) {
            System.out.println("Error general de exception: " + e.getMessage());
        }
    }
}