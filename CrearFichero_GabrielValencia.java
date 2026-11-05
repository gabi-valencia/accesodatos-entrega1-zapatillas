import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearFichero_GabrielValencia {
    public static void main(String[] args) {
        /*
         * Tamaño de cada zapatilla en el fichero (129 bytes):
         * Código (int) -> 4 bytes, referencia de la zapatilla
         * Nombre (String, 20) -> 40 bytes, nombre de la zapatilla
         * Marca (String, 20) -> 40 bytes, marca de la zapatilla
         * Precio (float) -> 4 bytes, precio de la zapatilla
         * En stock (boolean) -> 1 byte, esta disponible?
         * Tienda (String, 20) -> 40 bytes, en que tienda se encuentra
         * Los numeros dentro de mis string es el maximo de letras
         */

        // Creamos los arrays con la informacion de las zapatillas y sus variables:
        int codigos[] = { 1, 2, 3, 4, 5 };
        String nombres[] = { "New Balance 740", "Adidas Campus", "Converse", "Air force 1", "Asic Gel" };
        String marcas[] = { "New Balance", "Adidas", "Converse", "Nike", "Asics" };
        float precios[] = { 119.99F, 99.99F, 79.99F, 119.99F, 129.99F }; // se pone la F al final para que se dectecte
                                                                         // como un float
        boolean enStock[] = { true, false, true, false, false };
        String tiendas[] = { "Jd Sports", "Nike", "Foot Locker", "Sprinter", "Snipes" };

        // Iniciamos Fichero:
        String enlaceFichero = "InformacionZapatillas.dat";
        File f = new File(enlaceFichero);

        // Validaciones a realizar en base a nuestro fichero:
        // En este caso se va a Crear y Escribir.
        if (f.exists()) {
            if (f.isDirectory()) {
                System.out.println("No es un fichero, por lo que no se trabajará con el.");
                return;

            }
            if (!f.canWrite()) {
                System.out.println("El fichero no cuenta con permisos de escritura");
                return;

            }
            System.out.println("Validaciones superadas, el fichero existe y se trabajará con el");

        } else {
            System.out.println("El fichero no existe, pero se creará y se trabajará con el");
        }

        // Se va a trabajar con un fichero aleatorio:
        try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
            for (int i = 0; i < codigos.length; i++) {
                raf.writeInt(codigos[i]); // Codigo

                StringBuffer sbNombres = new StringBuffer(nombres[i]); //Nombre
                // La longitud debe ser 20 en los Strings:
                sbNombres.setLength(20); //Longitud 20
                raf.writeChars(sbNombres.toString());

                StringBuffer sbMarcas = new StringBuffer(marcas[i]); //Marca
                sbMarcas.setLength(20);
                raf.writeChars(sbMarcas.toString());

                raf.writeFloat(precios[i]);// Precio
                raf.writeBoolean(enStock[i]);// Stock

                StringBuffer sbTiendas = new StringBuffer(tiendas[i]);
                sbTiendas.setLength(20);
                raf.writeChars(sbTiendas.toString());

                //Para mostrar si tenemos la zapatilla o no en stock, debemos realizar un if que lo compruebe:
                //Variable de texto que guarde nuestro resultado:
                boolean comprobarStock = enStock[i];
                String guardarResultado = "";

                if (comprobarStock == true ) {
                   guardarResultado = "Si hay unidades";     
                } else{
                    guardarResultado = "No tenemos en stock";
                }

              System.out.println("Guardada -> Código: " + codigos[i]
              + " | " + nombres[i]
              + " | " + marcas[i]
              + " | " + precios[i] + " euros"
              + " | En stock: " + guardarResultado 
              + " | " + tiendas[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("El fichero con el que se quiere trabajar no existe: " + e.getMessage());
            // Si vamos a trabajar con arrays tenemos que tratarlo con exception por si nos
            // salimos del indice
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Has salido fuera del indice indicado en el array: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error general de exception: " + e.getMessage());
        }
    }
}
