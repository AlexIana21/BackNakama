package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Model.*;
import Model.Factory.DatabaseFactory;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EmpleadosAction implements IAction {
    @Override
    //ACTION=PRODUCTOS.FIND_ALL+Nombre="Alex"+Salario="5000"
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
        {
            case "DELETE":
                strReturn = deleteEmpleado(request,response);
                break;
            case "ADD":
                strReturn = addEmpleado(request, response);
                break;
            case "UPDATE":
                strReturn = updateEmpleado(request, response);
                break;
            case "FIND_ALL":
                strReturn = findAll();
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        EmpleadosDao empleadosDao = new EmpleadosDao(DatabaseFactory.ORACLE);
        ArrayList<Empleados> empleados = empleadosDao.findAll(null);
        return Empleados.toArrayJson(empleados);
    }

    private String addEmpleado(HttpServletRequest request, HttpServletResponse response) {
        Empleados empleados = new Gson().fromJson(Controller.getBody(request), Empleados.class);
        System.out.println(new Gson().toJson(empleados));
        EmpleadosDao empleadosDao = new EmpleadosDao(DatabaseFactory.ORACLE);
        int result = empleadosDao.add(empleados);

        if (result > 0) {
            return Empleados.fromObjectToJSON(empleados);
        } else {
            return "{\"message\":\"Error al registrar el producto\"}";
        }
    }

    private String updateEmpleado(HttpServletRequest request, HttpServletResponse response) {
        Empleados empleados = new Gson().fromJson(Controller.getBody(request), Empleados.class);
        System.out.println(new Gson().toJson(empleados));
        EmpleadosDao empleadosDao = new EmpleadosDao(DatabaseFactory.ORACLE);
        int result = empleadosDao.update(empleados);

        if (result > 0) {
            return Empleados.fromObjectToJSON(empleados);
        } else {
            return "{\"message\":\"Error al registrar el producto\"}";
        }
    }
    private String deleteEmpleado(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("ID_EMPLEADO");
        if (idParam != null) {
            EmpleadosDao empleadosDao = new EmpleadosDao(DatabaseFactory.ORACLE);
            int result = empleadosDao.delete(idParam);
            if (result > 0) {
                return "{\"message\":\"Producto eliminado con éxito\"}";
            } else {
                return "{\"message\":\"ERROR. No se pudo eliminar el producto\"}";
            }
        } else {
            return "{\"message\":\"ERROR. Missing ID parameter\"}";
        }
    }
}
