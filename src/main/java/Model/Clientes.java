package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Clientes {
    private String _idCliente;
    private String _nombre;
    private String _apellido;
    private String _email;
    private String _password;


    //Constructor
    public Clientes(String idCliente, String nombre, String apellido, String email, String password) {
        _idCliente = idCliente;
        _nombre = nombre;
        _apellido = apellido;
        _email = email;
        _password = password;
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


    public static String fromObjectToJSON(Clientes cliente) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(cliente);

        return resp;
    }

    public static String toArrayJSon(ArrayList<Clientes> cliente) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(cliente);
    }
}
