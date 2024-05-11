package Controller.Actions;

import Model.Departamento;
import Model.DepartamentoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DepartamentoAction implements IAction{
    @Override
    //ACTION=DEPARTAMENTO.FIND_ALL+Nombre="Sala"+IdDepartamento="DEP01"
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
        {
            case "FIND_FIRST":
                //strReturn
                break;
            case "FIND_ALL":
                Departamento dep = new Departamento();
                dep.setIdDepartamento("DEP01");
                dep.setNombre("Sala");
                strReturn = findAll(dep);
                strReturn = findAll(dep);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll(Departamento departamento) {
        DepartamentoDao departamentoDao = new DepartamentoDao();
        ArrayList<Departamento> departamentos = departamentoDao.findAll(null); // Usando instancia para llamar a findAll
        return Departamento.toArrayJSon(departamentos); // Asumiendo que toArrayJSon debe ser estático correctamente
    }
}
