package Model;

import java.util.Date;
public class Pedidos {
    private String _idPedido;
    private Date _hora;
    private Date _fecha;
    private String _idCliente;
    private String _idEmpleado;
    private String _idDescuento;

    //Constructor
    public Pedidos(String idPedido, Date hora, Date fecha, String idCliente, String idEmpleado, String idDescuento) {
        _idPedido = idPedido;
        _hora = hora;
        _fecha = fecha;
        _idCliente = idCliente;
        _idEmpleado = idEmpleado;
        _idDescuento = idDescuento;
    }

    public Pedidos() {

    }

    //Getters y setters

    public String getIdPedido() {
        return _idPedido;
    }

    public void setIdPedido(String idPedido) {
        _idPedido = idPedido;
    }

    public Date getHora() {
        return _hora;
    }

    public void setHora(Date hora) {
        _hora = hora;
    }

    public Date getFecha() {
        return _fecha;
    }

    public void setFecha(Date fecha) {
        _fecha = fecha;
    }

    public String getIdCliente() {
        return _idCliente;
    }

    public void setIdCliente(String idCliente) {
        _idCliente = idCliente;
    }

    public String getIdEmpleado() {
        return _idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        _idEmpleado = idEmpleado;
    }

    public String getIdDescuento() {
        return _idDescuento;
    }

    public void setIdDescuento(String idDescuento) {
        _idDescuento = idDescuento;
    }
}
