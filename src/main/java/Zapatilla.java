
public class Zapatilla {

    private int codigo;
    private String nombre;
    private String marca;
    private float precio;
    private boolean stock;
    private String tienda;

    // Constructor con todos los datos de la zapatilla
    public Zapatilla(int codigo, String nombre, String marca, float precio, boolean stock, String tienda) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.tienda = tienda;
    }

    // Getters para poder consultar cada dato desde fuera de la clase
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public float getPrecio() {
        return precio;
    }

    public boolean isStock() {
        return stock;
    }

    public String getTienda() {
        return tienda;
    }

    // Para mostrar la zapatilla por pantalla de forma ordenada
    @Override
    public String toString() {
        return "Código: " + codigo
                + " | " + nombre
                + " | " + marca
                + " | " + precio + " euros"
                + " | En stock: " + stock
                + " | " + tienda;
    }
}