package Controller.Actions;

import Model.LoginPrivado;
import Model.LoginPrivadoDao;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class LoginPrivadoAction implements IAction{
    @Override
    //ACTION=LOGIN_PRIVADO.FIND_ALL+ID_USUARIO="A001"+LOG_PASSWORD="admin1234"
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
        {
            case "FIND_FIRST":
                //strReturn
                break;
            case "FIND_ALL":
                LoginPrivado log = new LoginPrivado();
                log.setIdUsuario("A001");
                log.setPassword("admin1234");
                strReturn = findAll(log);
                strReturn = findAll(log);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll(LoginPrivado log) {

        LoginPrivadoDao loginPrivadoDao = new LoginPrivadoDao();
        ArrayList<LoginPrivado> loginPrivados = loginPrivadoDao.findAll(log);
        //ArrayList<LoginPrivado> loginPrivados = loginPrivadoDao.findAll(null);
        return LoginPrivado.toArrayJSon(loginPrivados);
    }
}
