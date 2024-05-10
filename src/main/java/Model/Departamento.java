package Model;

public class Departamento {
    private String idDepartamento;
    private String nombre;

    //Constructor
    public Departamento(String idDepartamento, String nombre) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }
    public Departamento () {

    }
    //Getters y setters
    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
