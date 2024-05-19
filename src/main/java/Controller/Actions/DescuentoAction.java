/*package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class DescuentoAction implements IAction {
    @Override
    //ACTION=DESCUENTO.FIND_ALL+Nombre="DE01"+Cantidad="5%"
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
        {
            case "FIND_FIRST":
                //strReturn
                break;
            case "FIND_ALL":
                Descuento des = new Descuento();
                //des.setNombre("Teriyaki");
                //.setCantidad(5);
                //strReturn = findAll(des);
                strReturn = findAll();
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll(Descuento des) {

        DescuentoDao descuentoDao = new DescuentoDao();
        ArrayList<Descuento> descuentos = descuentoDao.findAll(null);
        return Descuento.toArrayJSon(descuentos);
    }
}*/
