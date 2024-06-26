package Model;


import Model.Factory.DatabaseFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesDao implements IDao<Clientes, String> {

    private final String SQL_ADD = "INSERT INTO CLIENTES (ID_CLIENTE, CL_NOMBRE, CL_APELLIDO, CL_EMAIL, CL_PASSWORD) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_FIND_ALL = "SELECT * FROM CLIENTES WHERE 1=1 ";

    private final String SQL_LOGIN = "SELECT ID_CLIENTE, CL_NOMBRE, CL_APELLIDO, CL_EMAIL, CL_PASSWORD FROM CLIENTES WHERE CL_EMAIL = ? AND CL_PASSWORD = ?";
    private MotorOracle _motorOracle;

    public ClientesDao(String db) {
        _motorOracle = DatabaseFactory.getDatabase(db);
    }

    // este es paar el find all
    public ClientesDao() {

    }

    /* private String getNextIdCliente() {
         String nextId = "00001"; // Valor inicial por defecto
         _motorOracle.connect();
         try {
             ResultSet rs = _motorOracle.executeQuery(SQL_GET_MAX_ID);
             if (rs.next()) {
                 String maxId = rs.getString(1);
                 if (maxId != null) {
                     int maxIdInt = Integer.parseInt(maxId) + 1;
                     nextId = String.format("%02d", maxIdInt); // Formatear con ceros a la izquierda
                 }
             }
         } catch (SQLException e) {
             System.out.println("Error al obtener el próximo ID_CLIENTE: " + e.getMessage());
             e.printStackTrace();
         } finally {
             _motorOracle.disconnect();
         }
         return nextId;
     }
 */
    @Override
    public int add(Clientes bean) {
        int filasModificadas = 0;
        _motorOracle.connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = _motorOracle.prepareStatement(SQL_ADD);
            pstmt.setString(1, bean.getIdCliente());
            pstmt.setString(2, bean.getNombre());
            pstmt.setString(3, bean.getApellido());
            pstmt.setString(4, bean.getEmail());
            pstmt.setString(5, bean.getPassword());
            filasModificadas = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                _motorOracle.disconnect();
            } catch (SQLException se) {
                System.out.println("Error al cerrar recursos: " + se.getMessage());
                se.printStackTrace();
            }
        }
        return filasModificadas;
    }

    @Override
    public int delete(String bean) {
        return 0;
    }

    @Override
    public int update(Clientes bean) {
        return 0;
    }

    @Override
    public ArrayList<Clientes> findAll(Clientes bean ) {
        ArrayList<Clientes> clientes = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Clientes) bean).getIdCliente() != null) {
                    sql += " AND ID_CLIENTE='" + ((Clientes) bean).getIdCliente() + "'";
                }
                if (((Clientes) bean).getNombre() != null) {
                    sql += " AND CL_NOMBRE='" + ((Clientes) bean).getNombre() + "'";
                }
                if (((Clientes) bean).getApellido() != null) {
                    sql += " AND CL_APELLIDO='" + ((Clientes) bean).getApellido() + "'";
                }
                if (((Clientes) bean).getPassword() != null) {
                    sql += " AND CL_EMAIL='" + ((Clientes) bean).getPassword() + "'";
                }
                if (((Clientes) bean).getPassword() != null) {
                    sql += " AND CL_PASSWORD='" + ((Clientes) bean).getApellido() + "'";
                }
            }

            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setIdCliente(rs.getString("ID_CLIENTE"));
                cliente.setNombre(rs.getString("CL_NOMBRE"));
                cliente.setApellido(rs.getString("CL_APELLIDO"));
                cliente.setEmail(rs.getString("CL_EMAIL"));
                cliente.setPassword(rs.getString("CL_PASSWORD"));
                clientes.add(cliente);
            }
        } catch (Exception ex) {
            clientes.clear();
        } finally {
            motor.disconnect();
        }
        return clientes;
    }

    public Clientes login(String email, String password) {
        _motorOracle.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Clientes cliente = null;
        try {
            pstmt = _motorOracle.prepareStatement(SQL_LOGIN);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = new Clientes();
                cliente.setIdCliente(rs.getString("ID_CLIENTE"));
                cliente.setNombre(rs.getString("CL_NOMBRE"));
                cliente.setApellido(rs.getString("CL_APELLIDO"));
                cliente.setEmail(rs.getString("CL_EMAIL"));
                cliente.setPassword(rs.getString("CL_PASSWORD"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                _motorOracle.disconnect();
            } catch (SQLException se) {
                Logger.getLogger(ClientesDao.class.getName()).log(Level.SEVERE, null, se);
            }
        }
        return cliente;
    }

    public int register(Clientes bean) {
        int filasModificadas = 0;
        _motorOracle.connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = _motorOracle.prepareStatement(SQL_ADD);
            pstmt.setString(1, bean.getIdCliente());
            pstmt.setString(2, bean.getNombre());
            pstmt.setString(3, bean.getApellido());
            pstmt.setString(4, bean.getEmail());
            pstmt.setString(5, bean.getPassword());
            filasModificadas = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error registering client: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                _motorOracle.disconnect();
            } catch (SQLException se) {
                System.out.println("Error closing resources: " + se.getMessage());
                se.printStackTrace();
            }
        }
        return filasModificadas;
    }



}