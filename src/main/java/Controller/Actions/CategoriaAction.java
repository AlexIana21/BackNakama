package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class CategoriaAction implements IAction {
    @Override
    //ACTION=CATEGORIA.FIND_ALL+Nombre="Burgers"+IdCategoria="CAT01"
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
        {
            case "FIND_FIRST":
                //strReturn
                break;
            case "FIND_ALL":
                Categoria cat = new Categoria();
                cat.setIdCategoria("CAT01");
                cat.setDescripcion("Hamburguesas");
                strReturn = findAll(cat);
                strReturn = findAll(cat);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll(Categoria cat) {

        CategoriaDao categoriaDao = new CategoriaDao();
        ArrayList<Categoria> categorias = CategoriaDao.findAll(cat);
        //ArrayList<Productos> peliculas = peliculaDao.findAll(null);
        return Productos.toArrayJSon(categorias);
    }
}
