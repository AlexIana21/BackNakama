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
            case "FILTER":
                strReturn = findByFilter(request, response);
                break;
            case "FIND_ALL":
                Productos prod = new Productos();
                //prod.setNombre(null);
                //prod.setPrecioVenta(10.7);
                strReturn = findAll();
                //strReturn = findAll(prod);
                break;
            case "DELETE":
                String idParam = request.getParameter("ID_PRODUCTO");
                if (idParam != null) {
                    try {
                        int id = Integer.parseInt(idParam);
                        strReturn = delete(id);
                    } catch (NumberFormatException e) {
                        strReturn = "ERROR. Invalid ID format";
                    }
                } else {
                    strReturn = "ERROR. Missing ID parameter";
                }
                break;
            case "ADD":
                strReturn = add(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll(/*Productos prod*/) {

        ProductosDao productosDao = new ProductosDao();
        //ArrayList<Productos> productos = productosDao.findAll(prod);
        ArrayList<Productos> productos = productosDao.findAll(null);
        return Productos.toArrayJSon(productos);
    }

    private String delete(int id) {
        ProductosDao productosDao = new ProductosDao();
        int result = productosDao.delete(id);
        if (result > 0) {
            return "Producto eliminado con éxito";
        } else {
            return "ERROR. No se pudo eliminar el producto";
        }
    }

    private String findByFilter(HttpServletRequest request, HttpServletResponse response) {
        ProductosDao productosDao = new ProductosDao();
        String ID_CATEGORIA_PRD = request.getParameter("FILTRO");
        Productos productos = new Productos();
        productos.setIdCategoria(ID_CATEGORIA_PRD );
        ArrayList<Productos> producto = productosDao.findAllByCategory(null);
        return Productos.toArrayJSon(producto);
    }

    private String add(HttpServletRequest request) {
        ProductosDao productosDao = new ProductosDao();
        Productos producto = new Productos();

        try {
            // Obtener los parámetros del request y establecerlos en el objeto Productos
            producto.setIdProducto(request.getParameter("ID_PRODUCTO"));
            producto.setNombre(request.getParameter("PRD_NOMBRE"));
            producto.setPrecioVenta(Double.parseDouble(request.getParameter("PRD_PRECIO_VENTA")));
            producto.setDescripcion(request.getParameter("PRD_DESCRIPCION"));
            producto.setImagenRuta(request.getParameter("PRD_IMAGEN_RUTA"));
            producto.setEstado(Boolean.parseBoolean(request.getParameter("PRD_ESTADO")));
            producto.setIdCategoria(request.getParameter("ID_CATEGORIA_PRD"));

            int result = productosDao.add(producto);
            if (result > 0) {
                return "Producto agregado con éxito";
            } else {
                return "ERROR. No se pudo agregar el producto";
            }
        } catch (NumberFormatException e) {
            return "ERROR. Invalid number format for PRD_PRECIO_VENTA";
        } catch (Exception e) {
            return "ERROR. " + e.getMessage();
        }
    }
}

