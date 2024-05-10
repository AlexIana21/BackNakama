package Model;

public class LoginPrivado {
    private String idUsuario;
    private String password;

    //Constructor
    public LoginPrivado(String idUsuario, String password) {
        this.idUsuario = idUsuario;
        this.password = password;
    }
    public LoginPrivado() {
    }

    //Getters y setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
