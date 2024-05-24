package Model;
import Model.Factory.DatabaseFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadosDao implements IDao <Empleados, String>{
    private final String SQL_FIND_ALL = "SELECT * FROM EMPLEADOS WHERE 1=1 ";
    private final String SQL_ADD = "INSERT INTO EMPLEADOS (ID_EMPLEADO, EMP_NOMBRE, EMP_APELLIDO, EMP_EMAIL, EMP_TELEFONO, EMP_FECHA_CONTRATO, EMP_ROL_COMITE, EMP_SALARY, EMP_ESTADO, ID_PUESTO_EMP, ID_USUARIO_EMP  ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String SQL_UPDATE = "UPDATE EMPLEADOS SET ";

    private final String SQL_DELETE = "DELETE FROM EMPLEADOS WHERE ID_EMPLEADO = ? ";
    private MotorOracle _motorOracle;
    public EmpleadosDao(String db) {
        _motorOracle = DatabaseFactory.getDatabase(db);
    }




    @Override
    public int add(Empleados bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        PreparedStatement pstmt = null;
        try {
            // Aqui esta el codigo para ver los erroes que me daba pero se puede hacer sin sout
            motor.connect();
            pstmt = motor.prepareStatement(SQL_ADD);
            System.out.println("ID_EMPLEADO: " + bean.getIdEmpleado());
            pstmt.setString(1, bean.getIdEmpleado());
            System.out.println("EMP_NOMBRE: " + bean.getNombre());
            pstmt.setString(2, bean.getNombre());
            System.out.println("EMP_APELLIDO: " + bean.getApellido());
            pstmt.setString(3, bean.getApellido());
            System.out.println("EMP_EMAIL: " + bean.getEmail());
            pstmt.setString(4, bean.getEmail());
            System.out.println("EMP_TELEFONO: " + bean.getTelefono());
            pstmt.setString(5, bean.getTelefono());
            System.out.println("EMP_FECHA_CONTRATO: " + bean.getEstado());
            pstmt.setBoolean(6, bean.getEstado());
            System.out.println("EMP_ROL_COMITE: " + bean.getRolComite());
            pstmt.setString(7, bean.getRolComite());
            System.out.println("EMP_SALARY: " + bean.getSalario());
            pstmt.setDouble(8, bean.getSalario());
            System.out.println("EMP_ESTADO: " + bean.getEstado());
            pstmt.setBoolean(9, bean.getEstado());
            System.out.println("ID_PUESTO_EMP: " + bean.getIdPuesto());
            pstmt.setString(10, bean.getIdPuesto());
            System.out.println("ID_USUARIO_EMP: " + bean.getIdUsuario());
            pstmt.setString(11, bean.getIdUsuario());


            resp = pstmt.executeUpdate();
            System.out.println("Update executed, response: " + resp);
        } catch (SQLException e) {
            System.out.println("Error al agregar empleado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                motor.disconnect();
            } catch (SQLException se) {
                System.out.println("Error al cerrar recursos: " + se.getMessage());
                se.printStackTrace();
            }
        }
        return resp;
    }

    @Override
    public int delete(String id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        PreparedStatement pstmt = null;
        try {
            motor.connect();
            pstmt = motor.prepareStatement(SQL_DELETE);
            pstmt.setString(1, id);
            resp = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                motor.disconnect();
            } catch (SQLException se) {
                System.out.println("Error al cerrar recursos: " + se.getMessage());
                se.printStackTrace();
            }
        }
        return resp;
    }

    @Override
    public int update(Empleados bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        PreparedStatement pstmt = null;
        try {
            motor.connect();

            // Construir la consulta SQL dinámicamente
            StringBuilder sql = new StringBuilder(SQL_UPDATE);
            ArrayList<Object> params = new ArrayList<>();

            if (bean.getNombre() != null) {
                sql.append("EMP_NOMBRE = ?, ");
                params.add(bean.getNombre());
            }
            if (bean.getNombre() != null) {
                sql.append("EMP_APELLIDO = ?, ");
                params.add(bean.getNombre());
            }
            if (bean.getEmail() != null) {
                sql.append("EMP_EMAIL = ?, ");
                params.add(bean.getEmail());
            }
            if (bean.getTelefono() != null) {
                sql.append("EMP_TELEFONO = ?, ");
                params.add(bean.getTelefono());
            }
            if (bean.getFechaContrato() != null) {
                sql.append("EMP_FECHA_CONTRATO = ?, ");
                params.add(bean.getFechaContrato());
            }
            if (bean.getRolComite() != null) {
                sql.append("EMP_ROL_COMITE = ?, ");
                params.add(bean.getRolComite());
            }
            if (bean.getSalario() > 0) {
                sql.append("EMP_SALARY = ?, ");
                params.add(bean.getSalario());
            }
            if (bean.getEstado() == true) {
                sql.append("EMP_ESTADO = ?, ");
                params.add(bean.getEstado() ? 1 : 0);
            }
            if (bean.getIdPuesto() != null) {
                sql.append("ID_PUESTO_EMP = ?, ");
                params.add(bean.getIdPuesto());
            }
            if (bean.getIdUsuario() != null) {
                sql.append("ID_USUARIO_EMP = ?, ");
                params.add(bean.getIdUsuario());
            }

            // Eliminar la última coma y agregar la cláusula WHERE
            sql.setLength(sql.length() - 2);
            sql.append(" WHERE ID_EMPLEADOS = ?");
            params.add(bean.getIdEmpleado());

            pstmt = motor.prepareStatement(sql.toString());

            // Establecer los parámetros en la consulta preparada
            for (int i = 0; i < params.size(); i++) {
                if (params.get(i) instanceof String) {
                    pstmt.setString(i + 1, (String) params.get(i));
                } else if (params.get(i) instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) params.get(i));
                } else if (params.get(i) instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) params.get(i));
                }
            }

            resp = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                motor.disconnect();
            } catch (SQLException se) {
                System.out.println("Error al cerrar recursos: " + se.getMessage());
                se.printStackTrace();
            }
        }
        return resp;
    }



    @Override
    public ArrayList<Empleados> findAll(Empleados bean) {
        ArrayList<Empleados> empleados = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
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
                    sql += " AND ID_PUESTO_EMP='" + ((Empleados)bean).getIdPuesto() + "'";
                }
                if (((Empleados)bean).getIdUsuario() != null) {
                    sql += " AND ID_USUARIO_EMP='" + ((Empleados)bean).getIdUsuario() + "'";
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
                empleado.setEstado(rs.getBoolean("EMP_ESTADO") == true);
                Puesto puesto = new Puesto();
                puesto.setIdPuesto(rs.getString("ID_PUESTO_EMP"));
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