package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Date;

public class Pedidos {
    private String _idPedido;
    private Date _hora;
    private Date _fecha;
    private String _tlf;
    private String _direccion;
    private boolean _estado;
    private Double _precio;
    private String _idCliente;
    private String _idEmpleado;

    public Pedidos() { }

    // Getters y setters

    public String getIdPedido() { return _idPedido; }
    public void setIdPedido(String idPedido) { _idPedido = idPedido; }
    public Date getHora() { return _hora; }
    public void setHora(Date hora) { _hora = hora; }
    public Date getFecha() { return _fecha; }
    public void setFecha(Date fecha) { _fecha = fecha; }
    public String getTlf() { return _tlf; }
    public void setTlf(String telefono) { _tlf = telefono; }
    public String getDireccion() { return _direccion; }
    public void setDireccion(String direccion) { _direccion = direccion; }
    public Boolean getEstado() { return _estado; }
    public void setEstado(Boolean estado) { _estado = estado; }
    public Double getPrecio() { return _precio; }
    public void setPrecio(Double precio) { _precio = precio; }
    public String getIdCliente() { return _idCliente; }
    public void setIdCliente(String idCliente) { _idCliente = idCliente; }
    public String getIdEmpleado() { return _idEmpleado; }
    public void setIdEmpleado(String idEmpleado) { _idEmpleado = idEmpleado; }

    public Pedidos(String idPedido, Date hora, Date fecha, String telefono, String direccion, Boolean estado, Double precio, String idCliente, String idEmpleado) {
        _idPedido = idPedido;
        _hora = hora;
        _fecha = fecha;
        _tlf = telefono;
        _direccion = direccion;
        _estado = estado;
        _precio = precio;
        _idCliente = idCliente;
        _idEmpleado = idEmpleado;

    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "idPedido='" + _idPedido + '\'' +
                ", hora='" + _hora + '\'' +
                ", fecha='" + _fecha + '\'' +
                ", telefono='" + _tlf + '\'' +
                ", direccion='" + _direccion + '\'' +
                ", estado='" + _estado + '\'' +
                ", precio=" + _precio +
                ", idCliente='" + _idCliente + '\'' +
                ", idEmpleado='" + _idEmpleado + '\'' +
                '}';
    }

    public static String toArrayJSon(ArrayList<Pedidos> pedido) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(pedido);
    }

    /*public void aplicarDescuento(Descuento descuento) {
        if (descuento != null && _fecha != null && _fecha.after(descuento.getFechaInicio()) && _fecha.before(descuento.getFechaFinal())) {
            _precio = _precio - (_precio * (descuento.getCantidad() / 100.0));
        }
    }
*/

}
