package Controller;

import Controller.Actions.CategoriaAction;
import Controller.Actions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

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
            case "PUESTO":
            {
                out.print(new PuestoAction().execute(request,response, arrayAction[1]));
                break;
            }
            case "LOGIN_PRIVADO":
            {
                out.print(new LoginPrivadoAction().execute(request,response, arrayAction[1]));
                break;
            }

            case "CLIENTES":
            {
                out.print(new ClientesAction().execute(request,response, arrayAction[1]));
                break;
            }

            case "PEDIDOS":
            {
                out.print(new PedidosAction().execute(request,response, arrayAction[1]));
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setContentType("text/plain;charset=UTF-8");

        String strAction = request.getParameter("ACTION");
        String[] arrayAction = new String[2];
        if (!strAction.isEmpty()) {
            arrayAction = strAction.split("\\.");

            switch (arrayAction[0].toUpperCase()) {
                case "PRODUCTOS": {
                    response.getWriter().print(new ProductosAction().execute(request, response, arrayAction[1]));
                    break;
                }

            }
        }
    }

    public static String getBody(HttpServletRequest request) {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[256];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            // throw ex; return "";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {

                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}

// ENDPOINTS DE EJEMPLOS PARA QUE VEÁIS LA ESTRUCTURA
        //http://localhost:8080/untitled/Controller?ACTION=PELICULA.FIND_ALL
        // http://localhost:8080/API_JAVA_MYSQL/Controller?ACTION=PELICULA.FILTER&FILTRO=DRAMA
//


