package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        return "Puesto{" +
                "_idPuesto='" + _idPuesto + '\'' +
                ", _nombre='" + _nombre + '\'' +
                ", _descripcion='" + _descripcion + '\'' +
                ", _idDepartamento='" + _idDepartamento + '\'' +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Puesto> puestos){
        String resp = "[";
        for (Puesto puesto : puestos) {
            resp+= "{" +
                    "'idPuesto':'" + puesto.getIdPuesto()+ "', "
                    + "'nombre':'" + puesto.getNombre() + "',"
                    + "'descripcion':'" + puesto.getDescripcion() + "', "
                    + "'idDepartamento':'" + puesto.getIdDepartamento();

            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Puesto> puestos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(puestos);
    }
}
