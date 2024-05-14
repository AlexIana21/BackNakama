package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PuestoDao implements IDao{
    private final String SQL_FIND_ALL = "SELECT * FROM PUESTO WHERE 1=1 ";

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
    public ArrayList<Puesto> findAll(Object bean) {
        ArrayList<Puesto> puestos = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Puesto) bean).getIdPuesto() != null) {
                    sql += " AND ID_PUESTO='" + ((Puesto) bean).getIdPuesto() + "'";
                }
                if (((Puesto) bean).getNombre() != null) {
                    sql += " AND PUE_NOMBRE='" + ((Puesto) bean).getNombre() + "'";
                }
                if (((Puesto) bean).getDescripcion() != null) {
                    sql += " AND PUE_DESCRIPCION=" + ((Puesto) bean).getDescripcion() + "'";
                }
                if (((Puesto) bean).getIdDepartamento() != null) {
                    sql += " AND ID_DEPARTAMENTO_PUE=" + ((Puesto) bean).getIdDepartamento() + "'";
                }

            }
            ResultSet rs = motor.executeQuery(sql);


            while (rs.next()) {
                Puesto puesto = new Puesto();
                puesto.setIdPuesto(rs.getString("ID_PUESTO"));
                puesto.setNombre(rs.getString("PUE_NOMBRE"));
                puesto.setDescripcion(rs.getString("PUE_DESCRIPCION"));

                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(rs.getString("ID_DEPARTAMENTO_PUE"));
                puesto.setIdDepartamento(String.valueOf(departamento));
                //puesto.setIdDepartamento(rs.getString("ID_DEPARTAMENTO_PUE"));

                puestos.add(puesto);
            }

        } catch (Exception ex) {
            puestos.clear();
        } finally {
            motor.disconnect();
        }
        return puestos;
    }


    /*public static ArrayList<Puesto> findAll(Object bean)
    {
        ArrayList<Puesto> puestos = new ArrayList<>();
        puestos.add(new Puesto("PUE01", "Cocinero", "Trabaja en cocina", "DEP02"));
        return puestos;
    }*/


}