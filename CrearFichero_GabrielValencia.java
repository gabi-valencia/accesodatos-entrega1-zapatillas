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
        float precios[] = { 119.99F, 99.99F, 79.99F, 119.99F, 129.99F }; // se pone la F al final para que se dectecte como un float
        boolean enStock[] = { true, false, true, false, false };
        String tiendas[] = { "Jd Sports", "Nike", "Foot Locker", "Sprinter", "Snipes" };
    }
}
