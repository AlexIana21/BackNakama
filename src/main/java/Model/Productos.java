package Model;

public class Productos {
    private String idProducto;
    private String nombre;
    private double precioVenta;
    private String descripcion;
    /*private int cantidadStock;
    private byte[] imagen;*/
    private int estado;
    private String idCategoria;

    //Constructor
    public Productos(String idProducto, String nombre, double precioVenta, String descripcion, int estado, String idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idCategoria = idCategoria;
    }
    public Productos () {

    }

    //Getters y setters
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}
