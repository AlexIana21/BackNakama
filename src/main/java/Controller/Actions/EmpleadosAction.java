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
            case "FIND_BY_ID":
                strReturn = findEmpleadoById(request, response);
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
        EmpleadosDao empleadosDao = new EmpleadosDao(DatabaseFactory.ORACLE);
        Empleados empleado = new Empleados();

        try {
            empleado.setIdEmpleado(request.getParameter("ID_EMPLEADO"));
            empleado.setNombre(request.getParameter("EMP_NOMBRE"));
            empleado.setApellido(request.getParameter("EMP_APELLIDO"));
            empleado.setEmail(request.getParameter("EMP_EMAIL"));
            empleado.setTelefono(request.getParameter("EMP_TELEFONO"));
            empleado.setRolComite(request.getParameter("EMP_ROL_COMITE"));
            empleado.setSalario(Double.parseDouble(request.getParameter("EMP_SALARY")));
            empleado.setEstado(Boolean.parseBoolean(request.getParameter("EMP_ESTADO")));
            empleado.setIdPuesto(request.getParameter("ID_PUESTO_EMP"));
            empleado.setIdUsuario(request.getParameter("ID_USUARIO_EMP"));

            int result = empleadosDao.update(empleado);
            if (result > 0) {
                return Empleados.fromObjectToJSON(empleado);
            } else {
                return "ERROR. No se pudo actualizar el empleado";
            }
        } catch (NumberFormatException e) {
            return "ERROR. Formato de número inválido para EMP_SALARY";
        } catch (Exception e) {
            return "ERROR. " + e.getMessage();
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


    private String findEmpleadoById(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("ID_EMPLEADO");
        if (idParam != null) {
            EmpleadosDao empleadosDao = new EmpleadosDao(DatabaseFactory.ORACLE);
            Empleados empleado = empleadosDao.findByIdEmpleados(idParam);
            if (empleado != null) {
                return Empleados.fromObjectToJSON(empleado);
            } else {
                return "{\"message\":\"ERROR. Empleado no encontrado\"}";
            }
        } else {
            return "{\"message\":\"ERROR. Falta el parámetro ID\"}";
        }
    }
}
