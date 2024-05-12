package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class ProductosAction implements IAction {
    @Override
    //ACTION=PRODUCTOS.FIND_ALL+Nombre="Teriyaki"+Precio="10.7"
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
        {
            case "FIND_FIRST":
                //strReturn
                break;
            case "FIND_ALL":
                Productos prod = new Productos();
                prod.setNombre("Teriyaki");
                prod.setPrecioVenta(10.7);
                strReturn = findAll(prod);
                strReturn = findAll(prod);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll(Productos prod) {

        ProductosDao productosDao = new ProductosDao();
        ArrayList<Productos> productos = productosDao.findAll(prod);
        //ArrayList<Productos> productos = productosDao.findAll(null);
        return Productos.toArrayJSon(productos);
    }
}

