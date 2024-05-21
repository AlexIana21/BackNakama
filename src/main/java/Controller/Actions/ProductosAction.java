package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import java.util.ArrayList;

public class ProductosAction implements IAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "FILTER":
                // strReturn = findByFilter(request, response);
                break;
            case "FIND_ALL":
                strReturn = findAll();
                break;
            case "DELETE":
                strReturn = delete(request);
                break;
            case "ADD":
                strReturn = add(request);
                break;
            case "UPDATE":
                strReturn = update(request);
                break;

            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        ProductosDao productosDao = new ProductosDao();
        ArrayList<Productos> productos = productosDao.findAll(null);
        return Productos.toArrayJSon(productos);
    }


    private String delete(HttpServletRequest request) {
        String idParam = request.getParameter("ID_PRODUCTO");
        if (idParam != null) {
            ProductosDao productosDao = new ProductosDao();
            int result = productosDao.delete(idParam);
            if (result > 0) {
                return "Producto eliminado con éxito";
            } else {
                return "ERROR. No se pudo eliminar el producto";
            }
        } else {
            return "ERROR. Missing ID parameter";
        }
    }



    private String add(HttpServletRequest request) {
        ProductosDao productosDao = new ProductosDao();
        Productos producto = new Productos();

        try {
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

    private String update(HttpServletRequest request) {
        ProductosDao productosDao = new ProductosDao();
        Productos producto = new Productos();

        try {
            producto.setIdProducto(request.getParameter("ID_PRODUCTO"));
            producto.setNombre(request.getParameter("PRD_NOMBRE"));
            producto.setPrecioVenta(Double.parseDouble(request.getParameter("PRD_PRECIO_VENTA")));
            producto.setDescripcion(request.getParameter("PRD_DESCRIPCION"));
            producto.setImagenRuta(request.getParameter("PRD_IMAGEN_RUTA"));
            producto.setEstado(Boolean.parseBoolean(request.getParameter("PRD_ESTADO")));
            producto.setIdCategoria(request.getParameter("ID_CATEGORIA_PRD"));

            int result = productosDao.update(producto);
            if (result > 0) {
                return "Producto actualizado con éxito";
            } else {
                return "ERROR. No se pudo actualizar el producto";
            }
        } catch (NumberFormatException e) {
            return "ERROR. Invalid number format for PRD_PRECIO_VENTA";
        } catch (Exception e) {
            return "ERROR. " + e.getMessage();
        }
    }
}
