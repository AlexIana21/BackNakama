package Model;

public class Departamento {
    private String _idDepartamento;
    private String _nombre;

    //Constructor
    public Departamento(String idDepartamento, String nombre) {
        _idDepartamento = idDepartamento;
        _nombre = nombre;
    }
    public Departamento () {

    }
    //Getters y setters
    public String getIdDepartamento() {
        return _idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        _idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        _nombre = nombre;
    }
}
