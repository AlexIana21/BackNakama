package Model;

public class Categoria {
    private String _idCategoria;
    private String _nombre;
    private String _descripcion;

    //Constructor

    public Categoria(String idCategoria, String nombre, String descripcion) {
        _idCategoria = idCategoria;
        _nombre = nombre;
        _descripcion = descripcion;
    }
    public Categoria () {
    }

    //Getters y setters
    public String getIdCategoria() {
        return _idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        _idCategoria = idCategoria;
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
}
