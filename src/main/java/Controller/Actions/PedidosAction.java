package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Model.*;
import Model.Factory.DatabaseFactory;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PedidosAction implements IAction {
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "ADD":
                strReturn = addPedido(request,response);
                break;
            case "FIND_ALL":
                strReturn = findAll();
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String add(HttpServletRequest request) {
        PedidosDao pedidosDao = new PedidosDao(DatabaseFactory.ORACLE);
        Pedidos pedido = new Pedidos();

        try {
            // Obtener los parámetros del request y establecerlos en el objeto Pedidos
            pedido.setIdPedido(request.getParameter("ID_PEDIDO"));

            // Convertir la hora y fecha de String a Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            pedido.setFecha(dateFormat.parse(request.getParameter("PED_FECHA")));

            pedido.setTlf(request.getParameter("PED_TELEFONO"));
            pedido.setDireccion(request.getParameter("PED_DIRECCION"));
            pedido.setEstado(Boolean.parseBoolean(request.getParameter("PED_ESTADO")));
            pedido.setPrecio(Double.parseDouble(request.getParameter("PED_PRECIO")));
            pedido.setIdCliente(request.getParameter("ID_CLIENTE_PED").trim());
            pedido.setIdEmpleado(request.getParameter("ID_EMPLEADO_PED").trim());

            int result = pedidosDao.add(pedido);
            if (result > 0) {
                return "Pedido agregado con éxito";
            } else {
                return "ERROR. No se pudo agregar el pedido";
            }
        } catch (NumberFormatException e) {
            return "ERROR. Invalid number format for PED_PRECIO";
        } catch (Exception e) {
            return "ERROR. " + e.getMessage();
        }
    }

    private String addPedido(HttpServletRequest request, HttpServletResponse response) {
        Pedidos pedidos = new Gson().fromJson(Controller.getBody(request), Pedidos.class);
        System.out.println(new Gson().toJson(pedidos));
        PedidosDao pedidosDao = new PedidosDao(DatabaseFactory.ORACLE);
        int result = pedidosDao.add(pedidos);

        if (result > 0) {
            return Pedidos.fromObjectToJSON(pedidos);
        } else {
            return "{\"message\":\"Error al registrar el producto\"}";
        }
    }

    private String findAll() {
        PedidosDao pedidosDao = new PedidosDao(DatabaseFactory.ORACLE);
        ArrayList<Pedidos> pedidos = pedidosDao.findAll(null);
        return Pedidos.toArrayJSon(pedidos);
    }
}
