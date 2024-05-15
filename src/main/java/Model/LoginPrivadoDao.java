package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginPrivadoDao implements IDao{
    private final String SQL_FIND_ALL = "SELECT * FROM LOGIN_PRIVADO WHERE 1=1 ";

    @Override
    public int add(Object bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Object bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public ArrayList<LoginPrivado> findAll(Object bean) {
        ArrayList<LoginPrivado> loginPrivados = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((LoginPrivado)bean).getIdUsuario() != null) {
                    sql += " AND ID_USUARIO='" + ((LoginPrivado)bean).getIdUsuario() + "'";
                }
                if (((LoginPrivado)bean).getPassword() != null) {
                    sql += " AND LOG_PASSWORD='" + ((LoginPrivado)bean).getPassword() + "'";
                }

            }
            ResultSet rs = motor.executeQuery(sql);


            while (rs.next()) {
                LoginPrivado login = new LoginPrivado();
                login.setIdUsuario(rs.getString("ID_USUARIO"));
                login.setPassword(rs.getString("LOG_PASSWORD"));
                loginPrivados.add(login);
            }

        } catch (Exception ex) {
            loginPrivados.clear();
        } finally {
            motor.disconnect();
        }
        return loginPrivados;
    }


    /*public static ArrayList<LoginPrivado> findAll(Object bean)
    {
        ArrayList<LoginPrivado> login = new ArrayList<>();
        login.add(new LoginPrivado("A001", "admin1234"));
        return login;
    }*/


}
