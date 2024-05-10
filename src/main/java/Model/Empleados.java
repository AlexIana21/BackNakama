package Model;

import java.util.Date;

public class Empleados {
    private String idEmpleado;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Date fechaContrato;
    private String rolComite;
    private double salario;
    private int estado;
    private String idPuesto;
    private String idUsuario;

    //Constructor
    public Empleados(String idEmpleado, String nombre, String apellido, String email, String telefono, Date fechaContrato, String rolComite, double salario, int estado, String idPuesto, String idUsuario) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaContrato = fechaContrato;
        this.rolComite = rolComite;
        this.salario = salario;
        this.estado = estado;
        this.idPuesto = idPuesto;
        this.idUsuario = idUsuario;
    }
    public Empleados() {

    }

    //Getters y setters

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public String getRolComite() {
        return rolComite;
    }

    public void setRolComite(String rolComite) {
        this.rolComite = rolComite;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
