package Model;

public class Productos {
    private String _idProducto;
    private String _nombre;
    private String _descripcion;
    private double _precioVenta;
    private String _imagenRuta;
    private boolean _estado;
    private String _idCategoria;

    //Constructor
    public Productos(String idProducto, String nombre, double precioVenta, String imagenRuta, String descripcion, boolean estado, String idCategoria) {
        _idProducto = idProducto;
        _nombre = nombre;
        _descripcion = descripcion;
        _precioVenta = precioVenta;
        _imagenRuta = imagenRuta;
        _estado = estado;
        _idCategoria = idCategoria;
    }
    public Productos () {

    }

    //Getters y setters
    public String getIdProducto() {
        return _idProducto;
    }

    public void setIdProducto(String idProducto) {
        _idProducto = idProducto;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        _nombre = nombre;
    }
    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String descripcion) {
        _descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return _precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        _precioVenta = precioVenta;
    }
    public String getImagenRuta() {
        return _imagenRuta;
    }
    public void setImagenRuta(String imagenRuta) {
        _imagenRuta = imagenRuta;
    }

    public boolean getEstado() {
        return _estado;
    }

    public void setEstado(boolean estado) {
        _estado = estado;
    }

    public String getIdCategoria() {
        return _idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        _idCategoria = idCategoria;
    }
}
