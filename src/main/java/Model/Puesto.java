package Model;

public class Puesto {
    private String idPuesto;
    private String nombre;
    private String descripcion;
    private String idDepartamento;

    //Constructor
    public Puesto(String idPuesto, String nombre, String descripcion, String idDepartamento) {
        this.idPuesto = idPuesto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idDepartamento = idDepartamento;
    }
    public Puesto() {
    }

    //Getters y setters
    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
