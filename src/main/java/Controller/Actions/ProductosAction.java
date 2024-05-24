package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Factory.DatabaseFactory;
import Controller.Controller;
import Model.*;
import com.google.gson.Gson;

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
                strReturn = deleteProduct(request,response);
                break;
            case "ADD":
            strReturn = addProduct(request, response);
            break;
            case "UPDATE":
                strReturn = update(request);
                break;

            default:
                strReturn = "ERROR. Invalid Action";
        }
        //lol

        return strReturn;
    }

    private String findAll() {
        ProductosDao productosDao = new ProductosDao(DatabaseFactory.ORACLE);
        ArrayList<Productos> productos = productosDao.findAll(null);
        return Productos.toArrayJSon(productos);
    }


    private String delete(HttpServletRequest request) {
        String idParam = request.getParameter("ID_PRODUCTO");
        if (idParam != null) {
            ProductosDao productosDao = new ProductosDao(DatabaseFactory.ORACLE);
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
        ProductosDao productosDao = new ProductosDao(DatabaseFactory.ORACLE);
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

    private String addProduct(HttpServletRequest request, HttpServletResponse response) {
        Productos producto = new Gson().fromJson(Controller.getBody(request), Productos.class);
        System.out.println(new Gson().toJson(producto));
        ProductosDao productosDao = new ProductosDao(DatabaseFactory.ORACLE);
        int result = productosDao.add(producto);

        if (result > 0) {
            return Productos.fromObjectToJSON(producto);
        } else {
            return "{\"message\":\"Error al registrar el producto\"}";
        }
    }

    private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        Productos producto = new Gson().fromJson(Controller.getBody(request), Productos.class);
        System.out.println(new Gson().toJson(producto));
        ProductosDao productosDao = new ProductosDao(DatabaseFactory.ORACLE);
        int result = productosDao.delete(String.valueOf(producto));

        if (result > 0) {
            return Productos.fromObjectToJSON(producto);
        } else {
            return "{\"message\":\"Error al borrar el producto\"}";
        }
    }

    private String update(HttpServletRequest request) {
        ProductosDao productosDao = new ProductosDao(DatabaseFactory.ORACLE);
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
