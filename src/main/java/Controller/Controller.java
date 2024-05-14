package Controller;

import Controller.Actions.CategoriaAction;
import Controller.Actions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //request.getMethod()
        //request.getQueryString()
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        PrintWriter out = response.getWriter();
        String strAction = request.getParameter("ACTION");
        //ACTION=PRODUCTOS.FIND_ALL --> HAMBURGUER.FIND_ALL // USER.FIND
        String[] arrayAction= new String[2];;
        if (strAction != "")
        {
            arrayAction = strAction.split("\\."); //[0] PRODUCTO <-> [1] FIND_ALL
        }
        switch (arrayAction[0].toUpperCase())
        {
            case "EMPLEADOS":
            {
                out.print(new EmpleadosAction().execute(request,response, arrayAction[1]));
                break;
            }
            case "PRODUCTOS":
            {
                out.print(new ProductosAction().execute(request,response, arrayAction[1]));
                break;
            }
            case "CATEGORIA":
            {
                out.print(new CategoriaAction().execute(request,response, arrayAction[1]));
                break;
            }
            case "DEPARTAMENTO":
            {
                out.print(new DepartamentoAction().execute(request,response, arrayAction[1]));
                break;
            }
            case "DESCUENTO":
            {
                out.print(new DescuentoAction().execute(request,response, arrayAction[1]));
                break;
            }
            case "PUESTO":
            {
                out.print(new PuestoAction().execute(request,response, arrayAction[1]));
                break;
            }
            default:
            {
                System.out.println(arrayAction[0]);
                throw new ServletException ("Accion " + arrayAction[0] +" no valida");
            }


        }
        System.out.println(strAction);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
