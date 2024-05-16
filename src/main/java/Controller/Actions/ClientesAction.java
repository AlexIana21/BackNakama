package Controller.Actions;

import Model.Clientes;
import Model.ClientesDao;
import Model.Departamento;
import Model.Factory.DatabaseFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
public class ClientesAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
         {
           /* case "LOGIN":
                //strReturn = login(request, response);
                break;*/
            case "REGISTER":
                strReturn = register(request, response);
                break;
            /*case "FIND":
                //cadDestino = findByFilter(request, response);
                break;*/

        }
        return strReturn;
    }

    private String register(HttpServletRequest request, HttpServletResponse response) {
        String idCliente = request.getParameter("ID_CLIENTE ");
        String nombre = request.getParameter("CL_NOMBRE ");
        String apellido = request.getParameter("CL_APELLIDO ");
        String email = request.getParameter("CL_EMAIL ");
        String password = request.getParameter("CL_PASSWORD ");

        Clientes cliente = new Clientes();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setPassword(password);

        ClientesDao clientesDao = new ClientesDao(DatabaseFactory.ORACLE);

        int result = clientesDao.add(cliente);

        if (result > 0) {
            return Clientes.fromObjectToJSON(cliente);
        } else {
            return "{\"message\":\"Error al registrar el cliente\"}";
        }
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        // SELECT * FROM USUARIO WHERE EMAIL = 'a@svalero.com'  AND PASSWORD='1234'
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        //String nombre = request.getParameter("NOMBRE");

       /* Usuario usuario =new Usuario();
            usuario.setEmail(email);
            usuario.setPassword(password);*/
        //usuario.setNombre(nombre);

        ClientesDao clientesDao = new ClientesDao(DatabaseFactory.ORACLE);

        //int numUsuarios = usuarioDAO.add(usuario);

        Clientes cliente = clientesDao.login(email, password);

        // String resp = "['message:'good', 'usuario':{email:'"+email+"', password:'1234'}]";

        return Clientes.fromObjectToJSON(cliente) ;
    }


}
