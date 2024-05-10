package Model;

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
}
