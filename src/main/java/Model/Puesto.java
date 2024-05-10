package Model;

public class Puesto {
    private String _idPuesto;
    private String _nombre;
    private String _descripcion;
    private String _idDepartamento;

    //Constructor
    public Puesto(String idPuesto, String nombre, String descripcion, String idDepartamento) {
        _idPuesto = idPuesto;
        _nombre = nombre;
        _descripcion = descripcion;
        _idDepartamento = idDepartamento;
    }
    public Puesto() {
    }

    //Getters y setters
    public String getIdPuesto() {
        return _idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        _idPuesto = idPuesto;
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

    public String getIdDepartamento() {
        return _idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        _idDepartamento = idDepartamento;
    }
}
