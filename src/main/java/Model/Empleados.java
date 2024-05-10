package Model;

import java.util.Date;

public class Empleados {
    private String _idEmpleado;
    private String _nombre;
    private String _apellido;
    private String _email;
    private String _telefono;
    private Date _fechaContrato;
    private String _rolComite;
    private double _salario;
    private int _estado;
    private String _idPuesto;
    private String _idUsuario;

    //Constructor
    public Empleados(String idEmpleado, String nombre, String apellido, String email, String telefono, Date fechaContrato, String rolComite, double salario, int estado, String idPuesto, String idUsuario) {
        _idEmpleado = idEmpleado;
        _nombre = nombre;
        _apellido = apellido;
        _email = email;
        _telefono = telefono;
        _fechaContrato = fechaContrato;
        _rolComite = rolComite;
        _salario = salario;
        _estado = estado;
        _idPuesto = idPuesto;
        _idUsuario = idUsuario;
    }
    public Empleados() {

    }

    //Getters y setters

    public String getIdEmpleado() {
        return _idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        _idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        _nombre = nombre;
    }

    public String getApellido() {
        return _apellido;
    }

    public void setApellido(String apellido) {
        _apellido = apellido;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getTelefono() {
        return _telefono;
    }

    public void setTelefono(String telefono) {
        _telefono = telefono;
    }

    public Date getFechaContrato() {
        return _fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        _fechaContrato = fechaContrato;
    }

    public String getRolComite() {
        return _rolComite;
    }

    public void setRolComite(String rolComite) {
        _rolComite = rolComite;
    }

    public double getSalario() {
        return _salario;
    }

    public void setSalario(double salario) {
        _salario = salario;
    }

    public int getEstado() {
        return _estado;
    }

    public void setEstado(int estado) {
        _estado = estado;
    }

    public String getIdPuesto() {
        return _idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        _idPuesto = idPuesto;
    }

    public String getIdUsuario() {
        return _idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        _idUsuario = idUsuario;
    }
}
