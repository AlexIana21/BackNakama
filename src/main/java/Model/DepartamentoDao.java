package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DepartamentoDao implements IDao <Departamento, Integer>{
    private final String SQL_FIND_ALL = "SELECT * FROM DEPARTAMENTO WHERE 1=1 ";

    @Override
    public int add(Departamento bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Departamento bean) {

        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public ArrayList<Departamento> findAll(Departamento bean) {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Departamento) bean).getIdDepartamento() != null) {
                    sql += " AND ID_DEPARTAMENTO='" + ((Departamento) bean).getIdDepartamento() + "'";
                }
                if (((Departamento) bean).getNombre() != null) {
                    sql += " AND DEP_NOMBRE='" + ((Departamento) bean).getNombre() + "'";
                }

            }
            ResultSet rs = motor.executeQuery(sql);


            while (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(rs.getString("ID_DEPARTAMENTO"));
                departamento.setNombre(rs.getString("DEP_NOMBRE"));


                departamentos.add(departamento);
            }

        } catch (Exception ex) {
            departamentos.clear();
        } finally {
            motor.disconnect();
        }
        return departamentos;
    }


       /*public static ArrayList<Departamento> findAll(Object bean)
    {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        departamentos.add(new Departamento("DEP01", "Sala"));
        return departamentos;
    }*/

}
