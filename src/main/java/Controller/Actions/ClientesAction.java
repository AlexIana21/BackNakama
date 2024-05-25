package Controller.Actions;

import Controller.Controller;
import Model.Clientes;
import Model.ClientesDao;
import Model.Empleados;
import Model.EmpleadosDao;
import Model.Factory.DatabaseFactory;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ClientesAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "ADD":
                strReturn = addClient(request, response);
                break;
            case "LOGIN":
                strReturn = login(request, response);
                break;
            case "REGISTER":
                strReturn = registerClient(request, response);
                break;
            case "FIND_ALL":
                strReturn = findAll();
                break;
        }
        return strReturn;
    }

    private String addClient(HttpServletRequest request, HttpServletResponse response) {
        Clientes cliente = new Gson().fromJson(Controller.getBody(request), Clientes.class);
        System.out.println(new Gson().toJson(cliente));
        ClientesDao clienteDao = new ClientesDao(DatabaseFactory.ORACLE);
        int result = clienteDao.add(cliente);

        if (result > 0) {
            return Clientes.fromObjectToJSON(cliente);
        } else {
            return "{\"message\":\"Error al registrar el cliente\"}";
        }
    }

    private String registerClient(HttpServletRequest request, HttpServletResponse response) {
        Clientes cliente = new Gson().fromJson(Controller.getBody(request), Clientes.class);
        System.out.println(new Gson().toJson(cliente));
        ClientesDao clienteDao = new ClientesDao(DatabaseFactory.ORACLE);
        int result = clienteDao.register(cliente);

        if (result > 0) {
            return Clientes.fromObjectToJSON(cliente);
        } else {
            return "{\"message\":\"Error al registrar el cliente\"}";
        }
    }



    private String login(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("CL_EMAIL");
        String password = request.getParameter("CL_PASSWORD");

        ClientesDao clientesDao = new ClientesDao(DatabaseFactory.ORACLE);
        Clientes cliente = clientesDao.login(email, password);

        if (cliente != null) {
            return Clientes.fromObjectToJSON(cliente);
        } else {
            return "{\"message\":\"Login failed. Invalid email or password.\"}";
        }
    }


    private String findAll() {
        ClientesDao clientesDao = new ClientesDao();
        ArrayList<Clientes> clientes = clientesDao.findAll(null);
        return Clientes.toArrayJSon(clientes);
    }
}
