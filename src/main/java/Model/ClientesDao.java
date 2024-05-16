package Model;


import Model.Factory.DatabaseFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesDao implements IDao <Clientes, Integer> {

    private MotorOracle _motorOracle;

    private final String SQL_ADD
            = "INSERT INTO USUARIO (CL_NOMBRE , CL_APELLIDO , CL_EMAIL, CL_PASSWORD ) VALUES( ";

    public ClientesDao(String db) {
        MotorOracle MotorOracle = DatabaseFactory.getDatabase(db);
    }

    @Override
    public int add(Clientes bean) {
        _motorOracle.connect();
        String sql = "";
        sql+= "INSERT INTO USUARIO (CL_NOMBRE , CL_APELLIDO , CL_EMAIL, CL_PASSWORD ) VALUES( ";
        sql+= "'" + bean.getNombre() + "'";
        sql+= ",";
        sql+= "'" + bean.getApellido() + "'";
        sql+= ",";
        sql+= "'" + bean.getEmail()+ "'";
        sql+= ",";
        sql+= "'" + bean.getPassword()+ "'";
        sql+= ")";

        //int lastId = "SELECT MAX(ID) from USUARIO";


        // bean.setId(lastId); //999

        int filasModificadas = _motorOracle.execute(sql);
        _motorOracle.disconnect();

        return filasModificadas;
    }

    @Override
    public int delete(Integer bean) {
        return 0;
    }

    @Override
    public int update(Clientes bean) {
        return 0;
    }

    @Override
    public ArrayList<Clientes> findAll(Clientes bean) {
        return null;
    }

    public Clientes login(String email, String password){
        String sql = "SELECT EMAIL, PASSWORD FROM USUARIO WHERE EMAIL = '"+email+"' AND PASSWORD='"+password+"' ";
        _motorOracle.connect();
        ResultSet rs =  _motorOracle.executeQuery(sql);
        Clientes usuario = new Clientes();
        try {
            if(rs.next()){
                usuario.setEmail(rs.getString(1));
                usuario.setPassword(rs.getString(2));
            }else{
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        _motorOracle.disconnect();

        return usuario;
    }
}
