package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Categoria  {
    private String _idCategoria;
    private String _nombre;
    private String _descripcion;

    //Constructor

    public Categoria(String idCategoria, String nombre, String descripcion) {
        _idCategoria = idCategoria;
        _nombre = nombre;
        _descripcion = descripcion;
    }
    public Categoria () {
    }

    //Getters y setters
    public String getIdCategoria() {
        return _idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        _idCategoria = idCategoria;
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

    @Override
    public String toString() {
        return "Producto{" +
                "idCategoria='" + _idCategoria + '\'' +
                ", nombre='" + _nombre + '\'' +
                ", descripcion='" + _descripcion + '\'' +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Categoria> categorias){
        String resp = "[";
        for (Categoria categoria : categorias) {
            resp+= "{" +
                    "'idCategoria':'" + categoria.getIdCategoria()+ "', "
                    + "'nombre':'" + categoria.getNombre() + "',"
                    + "'descripcion':'" + categoria.getDescripcion() + "', ";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Categoria> categorias) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(categorias);
    }
}
