
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

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

        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            // Generamos la creacion del XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "zapatillas", null);
            document.setXmlVersion("1.0"); // asignamos la version de nuestro XML

            try {
                // El while va dentro de su propio try porque es un bucle infinito que solo
                // termina cuando se acaba el fichero en ese momento salta un EOFException.
                // Si el error lo cogiera el try de fuera, el programa saltaria directamente
                // a los catch del final y nunca llegaría a guardar el XML
                while (true) {
                    int codigo = dis.readInt();
                    // Tenemos que leer las zapatillas con todos sus datos asi que copiamos el
                    // bloque de Modificar fichero, modificando unicamente la variable raf, por dis
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
                    // Un boolean, con su método de leer boolean
                    boolean verStock = dis.readBoolean();

                    // ---------- TIENDA ----------
                    char tienda[] = new char[20];
                    for (int i = 0; i < tienda.length; i++) {
                        tienda[i] = dis.readChar();
                    }
                    String conversionTienda = new String(tienda).trim();

                    // ---------- MOSTRAR LA ZAPATILLA----------
                    System.out.println("Zapatilla -> Código: " + codigo
                            + " | " + conversionNombre
                            + " | " + conversionMarca
                            + " | " + leerPrecio + " euros"
                            + " | En stock: " + verStock
                            + " | " + conversionTienda);

                    // ---------- METER LA ZAPATILLA EN EL XML ----------
                    Element zapatilla = document.createElement("zapatilla");
                    document.getDocumentElement().appendChild(zapatilla);
                    CrearElemento("codigo", Integer.toString(codigo), zapatilla, document);
                    CrearElemento("nombre", conversionNombre, zapatilla, document);
                    CrearElemento("marca", conversionMarca, zapatilla, document);
                    CrearElemento("precio", Float.toString(leerPrecio), zapatilla, document);
                    CrearElemento("stock", Boolean.toString(verStock), zapatilla, document);
                    CrearElemento("tienda", conversionTienda, zapatilla, document);
                }

            } catch (EOFException e) { // End of File, salta cuando ya no quedan zapatillas
                System.out.println("Se ha terminado de leer la informacion del fichero.");
            }

            // ---------- GUARDAR EL XML ----------
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("zapatillas.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // para que no salga solo en una línea el xml sino que le de formato hay que
            // poner:
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            transformer.transform(source, result);
            System.out.println("Fichero zapatillas.xml creado correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido leer el fichero");
        } catch (IOException e) {
            System.out.println("Error general de exception: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("Error al preparar el documento XML: " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("Error al guardar el fichero XML: " + e.getMessage());
        }
    } // fin del main

    // Vamos a crear un metodo aparte para poder añadir los elementos que queramos a
    // nuestro XML y asi poder llamarlos y utilizarlos desde donde queramos:
    static void CrearElemento(String nombreEtiqueta, String valor, Element raiz, Document document) {
        Element elem = document.createElement(nombreEtiqueta); // creamos hijo
        Text text = document.createTextNode(valor); // damos valor
        raiz.appendChild(elem); // pegamos el elemento hijo a la raiz
        elem.appendChild(text); // pegamos el valor
    }
}