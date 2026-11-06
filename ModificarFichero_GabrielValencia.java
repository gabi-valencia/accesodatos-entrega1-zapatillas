import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
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
        try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {

            // ---------- SALTAR A LA ZAPATILLA ----------
            // Calculo dónde empieza la zapatilla y salto a esa posición
            int posicionZapatilla = (leerNumero - 1) * tamañoZapatilla;
            raf.seek(posicionZapatilla);

            // Leo los campos en el mismo orden en el que los guardé en CrearFichero

            // ---------- CÓDIGO ----------
            int leerCodigos = raf.readInt();

            // ---------- NOMBRE ----------
            // Leo las 20 letras una a una y las paso a texto, quitando los huecos del final
            char nombre[] = new char[20];
            for (int i = 0; i < nombre.length; i++) {
                nombre[i] = raf.readChar();
            }
            String conversionNombre = new String(nombre).trim();

            // ---------- MARCA ----------
            // Igual que nombre array de 20, bucle y conversión
            char marca[] = new char[20];
            for (int i = 0; i < marca.length; i++) {
                marca[i] = raf.readChar();
            }
            String conversionMarca = new String(marca).trim();

            // ---------- PRECIO ----------
            // Un float, como el código pero con su método de leer float
            float leerPrecio = raf.readFloat();

            // ---------- EN STOCK ----------
            // Un boolean, con su método de leer boolean
            boolean verStock = raf.readBoolean();

            // ---------- TIENDA ----------
            // Igual que el nombre y la marca
            char tienda[] = new char[20];
            for (int i = 0; i < tienda.length; i++) {
                tienda[i] = raf.readChar();
            }
            String conversionTienda = new String(tienda).trim();

            // ---------- MOSTRAR LA ZAPATILLA (ANTES DE LA MODIFICACION)----------
            // Un println con todos los datos, como el de CrearFichero
            System.out.println("Datos actuales -> Código: " + leerCodigos
                    + " | " + conversionNombre
                    + " | " + conversionMarca
                    + " | " + leerPrecio + " euros"
                    + " | En stock: " + verStock
                    + " | " + conversionTienda);

            // ------------ SCANNER CON NUEVOS DATOS ---------------
            // Los unicos datos que se pueden modificar es el precio y el stock, el resto
            // son datos fijos: Precio.
            System.out.println("¿Qué nuevo precio quieres ponerle al producto? (usa una coma(,)): ");
            float nuevoPrecio = sc.nextFloat();
            System.out.println(nuevoPrecio);

            // Stock:
            System.out.println("¿Hay stock de esta zapatilla? (escribe true o false): ");
            boolean cambioStock = sc.nextBoolean();
            System.out.println(cambioStock);

            // Tenemos que saltar directamente al dato que queremos modificar, en este caso
            // a precio
            // Nuestro cursor se encuentra en la ultima posicion osea, en la tienda:
            raf.seek(posicionZapatilla + 84); // --> ponemos 84 ya que antes de llegar a la zapatilla tenemos 84 bytes
            // Escribimos los nuevos precios en nuestro fichero:
            raf.writeFloat(nuevoPrecio); // Lo escrito en el scanner se guarda
            raf.writeBoolean(cambioStock); // Lo escrito en el scanner se guarda

        } catch (FileNotFoundException e) {
            System.out.println("El fichero con el que se quiere trabajar no existe: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("El dato que has introducido no es compatible, con la respuesta esperada");

        } catch (IOException e) {
            System.out.println("Error general de exception: " + e.getMessage());
        }
    }
}
