package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        return "Departamento{" +
                "_idDepartamento='" + _idDepartamento + '\'' +
                ", _nombre='" + _nombre + '\'' +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Departamento> departamentos){
        String resp = "[";
        for (Departamento departamento : departamentos) {
            resp+= "{" +
                    "'idDepartamento':'" + departamento.getIdDepartamento()+ "', "
                    + "'nombre':'" + departamento.getNombre() + "',";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Departamento> departamentos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(departamentos);
    }
}
