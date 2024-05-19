/*package Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Descuento {
    private String _idDescuento;
    private String _nombre;
    private Date _fechaInicio;
    private Date _fechaFinal;
    private int _cantidad;

    //Constructor

    public Descuento(String idDescuento, String nombre, Date fechaInicio, Date fechaFinal, int cantidad) {
        _idDescuento = idDescuento;
        _nombre = nombre;
        _fechaInicio = fechaInicio;
        _fechaFinal = fechaFinal;
        _cantidad = cantidad;
    }

    public Descuento() {

    }

    //Getters y setters

    public String getIdDescuento() {
        return _idDescuento;
    }

    public void setIdDescuento(String idDescuento) {
        _idDescuento = idDescuento;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        _nombre = nombre;
    }

    public Date getFechaInicio() {
        return _fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        _fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return _fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        _fechaFinal = fechaFinal;
    }

    public int getCantidad() {
        return _cantidad;
    }

    public void setCantidad(int cantidad) {
        _cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Descuneto{" +
                "idDescuento='" + _idDescuento + '\'' +
                ",nombre'" + _nombre + '\'' +
                ",fechaInicio'"  + _fechaInicio + '\'' +
                ",fechaFinal'"  + _fechaFinal + '\'' +
                ",cantidad'"  + -_cantidad + '\'' +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Descuento> descuento){
        String resp = "[";
        for (Descuento descuentos : descuento) {
            resp += "[" +
                    "'idDescuento':'" + descuentos.getIdDescuento()+ "', "
                    + "'nombre':'" + descuentos.getNombre() + "',"
                    + " 'fechaInicio':'" + descuentos.getFechaInicio() + "', "
                    + "'fechaFinal':" + descuentos.getFechaFinal() + ", "
                    + "'cantidad':" + descuentos.getCantidad() +
                    "}";
            resp+=",";

        }

        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;

    }

    public static String toArrayJSon(ArrayList<Descuento> descuentos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(descuentos);
    }






}*/
