package Model;

import java.util.Date;

public class Descuento {
    private String _idDescuento;
    private Date _fechaInicio;
    private Date _fechaFinal;
    private int _cantidad;

    //Constructor

    public Descuento(String idDescuento, Date fechaInicio, Date fechaFinal, int cantidad) {
        _idDescuento = idDescuento;
        _fechaInicio = fechaInicio;
        _fechaFinal = fechaFinal;
        _cantidad = cantidad;
    }

    public Descuento () {

    }

    //Getters y setters

    public String getIdDescuento() {
        return _idDescuento;
    }

    public void setIdDescuento(String idDescuento) {
        _idDescuento = idDescuento;
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
}
