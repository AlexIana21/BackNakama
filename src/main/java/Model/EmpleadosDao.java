package Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class EmpleadosDao implements IDao {
    private final String SQL_FIND_ALL = "SELECT * FROM EMPLEADOS WHERE 1=1 ";

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
    public ArrayList<Empleados> findAll(Object bean) {
        ArrayList<Empleados> empleados = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Empleados)bean).getIdEmpleado() != null) {
                    sql += " AND ID_EMPLEADO='" + ((Empleados)bean).getIdEmpleado() + "'";
                }
                if (((Empleados)bean).getNombre() != null) {
                    sql += " AND EMP_NOMBRE='" + ((Empleados)bean).getNombre() + "'";
                }
                if (((Empleados)bean).getApellido() != null) {
                    sql += " AND EMP_APELLIDO='" + ((Empleados)bean).getApellido() + "'";
                }
                if (((Empleados)bean).getEmail() != null) {
                    sql += " AND EMP_EMAIL='" + ((Empleados)bean).getEmail() + "'";
                }
                if (((Empleados)bean).getTelefono() != null) {
                    sql += " AND EMP_TELEFONO='" + ((Empleados)bean).getTelefono() + "'";
                }
                if (((Empleados)bean).getFechaContrato() != null) {
                    sql += " AND EMP_FECHA_CONTRATO='" + new java.sql.Date(((Empleados)bean).getFechaContrato().getTime()) + "'";
                }
                if (((Empleados)bean).getRolComite() != null) {
                    sql += " AND EMP_ROL_COMITE='" + ((Empleados)bean).getRolComite() + "'";
                }
                if (((Empleados)bean).getSalario() > 0) {
                    sql += " AND EMP_SALARY =" + ((Empleados)bean).getSalario();
                }
                if (((Empleados)bean).getEstado()) {
                    sql += " AND EMP_ESTADO='" + (((Empleados)bean).getEstado() ? 1 : 0) + "'";
                }
                if (((Empleados)bean).getIdPuesto() != null) {
                    sql += " AND EMP_ID_PUESTO='" + ((Empleados)bean).getIdPuesto() + "'";
                }
                if (((Empleados)bean).getIdUsuario() != null) {
                    sql += " AND EMP_ID_USUARIO='" + ((Empleados)bean).getIdUsuario() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Empleados empleado = new Empleados();
                empleado.setIdEmpleado(rs.getString("ID_EMPLEADO"));
                empleado.setNombre(rs.getString("EMP_NOMBRE"));
                empleado.setApellido(rs.getString("EMP_APELLIDO"));
                empleado.setEmail(rs.getString("EMP_EMAIL"));
                empleado.setTelefono(rs.getString("EMP_TELEFONO"));
                empleado.setFechaContrato(rs.getDate("EMP_FECHA_CONTRATO"));
                empleado.setRolComite(rs.getString("EMP_ROL_COMITE"));
                empleado.setSalario(rs.getDouble("EMP_SALARY"));
                empleado.setEstado(rs.getInt("EMP_ESTADO") == 1);
                Puesto puesto = new Puesto();
                puesto.setIdPuesto(rs.getString("ID_PUESTO_emp"));
                empleado.setIdPuesto(puesto.getIdPuesto());
                LoginPrivado loginPrivado = new LoginPrivado();
                loginPrivado.setIdUsuario(rs.getString("ID_USUARIO_EMP"));
                empleado.setIdUsuario(loginPrivado.getIdUsuario());
                empleados.add(empleado);
            }

        } catch (Exception ex) {
            empleados.clear();
        } finally {
            motor.disconnect();
        }
        return empleados;
    }
}