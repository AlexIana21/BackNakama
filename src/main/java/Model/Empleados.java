package Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Date;

public class Empleados {
    private String _idEmpleado;
    private String _nombre;
    private String _apellido;
    private String _email;
    private String _telefono;
    private String _rolComite;
    private double _salario;
    private boolean _estado;
    private String _idPuesto;
    private String _idUsuario;

    //Constructor
    public Empleados(String idEmpleado, String nombre, String apellido, String email, String telefono, String rolComite, double salario, boolean estado, String idPuesto, String idUsuario) {
        _idEmpleado = idEmpleado;
        _nombre = nombre;
        _apellido = apellido;
        _email = email;
        _telefono = telefono;
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

    public boolean getEstado() {
        return _estado;
    }

    public void setEstado(boolean estado) {
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




    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado='" + _idEmpleado + '\'' +
                ", nombre='" + _nombre + '\'' +
                ", apellido='" + _apellido + '\'' +
                ", email='" + _email + '\'' +
                ", telefono='" + _telefono + '\'' +
                ", rolComite='" + _rolComite + '\'' +
                ", salario=" + _salario +
                ", estado=" + _estado +
                ", idPuesto='" + _idPuesto + '\'' +
                ", idUsuario='" + _idUsuario + '\'' +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Empleados> empleados) {
        String resp = "[";
        for (Empleados empleado : empleados) {
            resp += "{" +
                    "'idEmpleado':'" + empleado.getIdEmpleado() + "', " +
                    "'nombre':'" + empleado.getNombre() + "', " +
                    "'apellido':'" + empleado.getApellido() + "', " +
                    "'email':'" + empleado.getEmail() + "', " +
                    "'telefono':'" + empleado.getTelefono() + "', " +
                    "'rolComite':'" + empleado.getRolComite() + "', " +
                    "'salario':" + empleado.getSalario() + ", " +
                    "'estado':" + empleado.getEstado() + ", " +
                    "'idPuesto':'" + empleado.getIdPuesto() + "', " +
                    "'idUsuario':'" + empleado.getIdUsuario() + "'}";
            resp += ",";
        }
        if (!empleados.isEmpty()) {
            resp = resp.substring(0, resp.length() - 1);
        }
        resp += "]";
        return resp;
    }



    public static String fromObjectToJSON(Empleados empleados) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(empleados);

        return resp;
    }


    public static String toArrayJson(ArrayList<Empleados> empleados) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(empleados);
    }
}
