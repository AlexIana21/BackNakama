package Model;

public class DetallePedido {
    private String _idTicket;
    private double _precio;
    private String _idProducto;
    private String _idPedido;

    //Constructor

    public DetallePedido(String idTicket, double precio, String idProducto, String idPedido) {
        _idTicket = idTicket;
        _precio = precio;
        _idProducto = idProducto;
        _idPedido = idPedido;
    }

    public DetallePedido() {

    }

    //Getters y setters

    public String getIdTicket() {
        return _idTicket;
    }

    public void setIdTicket(String idTicket) {
        _idTicket = idTicket;
    }

    public double getPrecio() {
        return _precio;
    }

    public void setPrecio(double precio) {
        _precio = precio;
    }

    public String getIdProducto() {
        return _idProducto;
    }

    public void setIdProducto(String idProducto) {
        _idProducto = idProducto;
    }

    public String getIdPedido() {
        return _idPedido;
    }

    public void setIdPedido(String idPedido) {
        _idPedido = idPedido;
    }
}
