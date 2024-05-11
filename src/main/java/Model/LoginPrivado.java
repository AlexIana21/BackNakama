package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class LoginPrivado {
    private String _idUsuario;
    private String _password;

    //Constructor
    public LoginPrivado(String idUsuario, String password) {
        _idUsuario = idUsuario;
        _password = password;
    }
    public LoginPrivado() {
    }

    //Getters y setters
    public String getIdUsuario() {
        return _idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        _idUsuario = idUsuario;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    @Override
    public String toString() {
        return "LoginPrivado{" +
                "_idUsuario='" + _idUsuario + '\'' +
                ", _password='" + _password + '\'' +
                '}';
    }

    public static String fromArrayToJson(ArrayList<LoginPrivado> loginPrivados){
        String resp = "[";
        for (LoginPrivado loginPrivado : loginPrivados) {
            resp+= "{" +
                    "'idUsuario':'" + loginPrivado.getIdUsuario()+ "', "
                    + "'password':'" + loginPrivado.getPassword() + "',";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<LoginPrivado> loginPrivados) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(loginPrivados);
    }

}

