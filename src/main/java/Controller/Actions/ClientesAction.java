package Controller.Actions;

import Model.Clientes;
import Model.ClientesDao;
import Model.Factory.DatabaseFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ClientesAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "LOGIN":
                strReturn = login(request, response);
                break;
            case "REGISTER":
                strReturn = register(request, response);
                break;
            case "FIND":
               // strReturn = find(request, response);
                break;
        }
        return strReturn;
    }

    private String register(HttpServletRequest request, HttpServletResponse response) {
        String idCliente = request.getParameter("ID_CLIENTE");
        String nombre = request.getParameter("CL_NOMBRE");
        String apellido = request.getParameter("CL_APELLIDO");
        String email = request.getParameter("CL_EMAIL");
        String password = request.getParameter("CL_PASSWORD");

        Clientes cliente = new Clientes();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setPassword(password);

        ClientesDao clientesDao = new ClientesDao(DatabaseFactory.ORACLE);

        int result = clientesDao.add(cliente);

        if (result > 0) {
            return Clientes.fromObjectToJSON(cliente);
        } else {
            return "{\"message\":\"Error al registrar el cliente\"}";
        }
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        ClientesDao clientesDao = new ClientesDao(DatabaseFactory.ORACLE);
        Clientes cliente = clientesDao.login(email, password);

        return Clientes.fromObjectToJSON(cliente);
    }


   /* private String find(HttpServletRequest request, HttpServletResponse response) {
        String nombre = request.getParameter("CL_NOMBRE");
        String apellido = request.getParameter("CL_APELLIDO");
        String email = request.getParameter("CL_EMAIL");

        Clientes filter = new Clientes();
        filter.setNombre(nombre);
        filter.setApellido(apellido);
        filter.setEmail(email);

        ClientesDao clientesDao = new ClientesDao(DatabaseFactory.ORACLE);
        ArrayList<Clientes> clientes = clientesDao.find(filter);

        return Clientes.toArrayJSon(clientes);
    }*/
}