import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu_GabrielValencia {
    public static void main(String[] args) {

        // Scanner que lea el numero para usar en el menu
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("¿Que parte del programa quieres utilizar?");
            System.out.println("1. Crear fichero de zapatillas");
            System.out.println("2. Modificar una zapatilla");
            System.out.println("3. Crear XML");
            System.out.println("4. Leer XML");
            System.out.println("5. Convertir XML a HTML (XSLT)");
            System.out.println("0. Salir");

            // Leo la opcion dentro de un try por si el usuario escribe una letra
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Tienes que escribir un numero entre el 0 y el 5");
                sc.nextLine(); // Quito la letra que ha escrito para que no se quede atascada
                opcion = -1; // Le doy un valor que no es 0 para que el menu vuelva a salir
                continue; // Salto directamente a la siguiente vuelta del menu
            }

            // Switch con las opciones del menu:
            switch (opcion) {
                case 1:
                    CrearFichero_GabrielValencia.main(null);
                    break;
                case 2:
                    ModificarFichero_GabrielValencia.main(null);
                    break;
                case 3:
                    CrearFicheroXML_GabrielValencia.main(null);
                    break;
                case 4:
                    LeerFicheroXML_GabrielValencia.main(null);
                    break;
                case 5:
                    ConvertirXSLT_GabrielValencia.main(null);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println(
                            "Has introducido una opcion no valida para el menu, introduce un numero entre el 0 y el 5");
            }

        } while (opcion != 0);
        sc.close();
    }
}