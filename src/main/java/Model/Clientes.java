package Model;

public class Clientes {
    private String _idCliente;
    private String _nombre;
    private String _apellido;
    private String _email;
    private String _password;
    private String _telefono;
    private String _direccion;

    //Constructor
    public Clientes(String idCliente, String nombre, String apellido, String email, String password, String telefono, String direccion) {
        _idCliente = idCliente;
        _nombre = nombre;
        _apellido = apellido;
        _email = email;
        _password = password;
        _telefono = telefono;
        _direccion = direccion;
    }

    public Clientes() {

    }

    //Getters y setters

    public String getIdCliente() {
        return _idCliente;
    }

    public void setIdCliente(String idCliente) {
        _idCliente = idCliente;
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

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public String getTelefono() {
        return _telefono;
    }

    public void setTelefono(String telefono) {
        _telefono = telefono;
    }

    public String getDireccion() {
        return _direccion;
    }

    public void setDireccion(String direccion) {
        _direccion = direccion;
    }
}
