package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class EmpleadosAction implements IAction {
    @Override
    //ACTION=PRODUCTOS.FIND_ALL+Nombre="Alex"+Salario="5000"
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
        {
            case "FIND_FIRST":
                //strReturn
                break;
            case "FIND_ALL":
                Empleados emp = new Empleados();
                emp.setNombre("Alex");
                emp.setSalario(5000);
                strReturn = findAll(emp);
                strReturn = findAll(emp);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll(Empleados emp) {

        EmpleadosDao empleadosDao = new EmpleadosDao();
        ArrayList<Empleados> empleados = empleadosDao.findAll(emp);
        //ArrayList<Empleados> empleados = empleadosDao.findAll(null);
        return Empleados.toArrayJson(empleados);
    }
}
