import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ConvertirXSLT_GabrielValencia {
    public static void main(String[] args) {

        // Ruta para el xml
        String rutaXML = "InformacionZapatillas.xml";
        File xml = new File(rutaXML);

        // Ruta para xsl
        String rutaXSL = "plantillaZapatillas.xsl";
        File xsl = new File(rutaXSL);

        // Validaciones---> Ambos son de lectura
        if (!xml.exists() || !xsl.exists()) {
            System.out.println("Los ficheros no existen");
            return;
        }
        // Si no son un fichero, entonces son un directorio:
        if (!xml.isFile() || !xsl.isFile()) {
            System.out.println("No son ficheros, por lo que no se puede trabajar con ellos");
            return;
        }
        if (xml.length() == 0 || xsl.length() == 0) {
            System.out.println("Los ficheros se encuentran vacios y no cuentan con contenido para poder leerlos");
            return;
        }
        if (!xml.canRead() || !xsl.canRead()) {
            System.out.println("Los ficheros no cuentan con permisos de lectura");
            return;
        }
        StreamSource datos = new StreamSource(xml);
        StreamSource estilos = new StreamSource(xsl);

        // Generamos el fichero HTML:
        StreamResult resultadoHTML = new StreamResult(new File("tablaZapatillas.html"));

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, resultadoHTML);
            System.out.println("Fichero tablaZapatillas.html creado correctamente");
        } catch (TransformerException e) {
            System.out.println("No se ha podido realizar la conversion con exito");
        }
    }
}
