package Model;

import java.util.ArrayList;

public class DetallePedidoDao implements  IDao <DetallePedido, Integer>{

    private final String SQL_FIND_ALL = "SELECT * FROM DESCUENTO WHERE 1=1 ";

    @Override
    public int add(DetallePedido bean) {
        return 0;
    }

    @Override
    public int delete(Integer bean) {
        return 0;
    }

    @Override
    public int update(DetallePedido bean) {
        return 0;
    }

    @Override
    public ArrayList<DetallePedido> findAll(DetallePedido bean) {
        return null;
    }
}
