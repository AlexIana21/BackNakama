package Model;


import Model.Factory.DatabaseFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesDao implements IDao<Clientes, Integer> {

    private final String SQL_ADD = "INSERT INTO CLIENTES (ID_CLIENTE, CL_NOMBRE, CL_APELLIDO, CL_EMAIL, CL_PASSWORD) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_GET_MAX_ID = "SELECT MAX(ID_CLIENTE) FROM CLIENTES";
    private final String SQL_FIND = "SELECT * FROM CLIENTES WHERE 1=1 ORDER BY ID_CLIENTE";

    private MotorOracle _motorOracle;

    public ClientesDao(String db) {
        _motorOracle = DatabaseFactory.getDatabase(db);
    }

    private String getNextIdCliente() {
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

    @Override
    public int add(Clientes bean) {
        int filasModificadas = 0;
        _motorOracle.connect();
        PreparedStatement pstmt = null;
        try {
            // Generar nuevo ID_CLIENTE si es necesario
            if (bean.getIdCliente() == null || bean.getIdCliente().isEmpty()) {
                bean.setIdCliente(getNextIdCliente());
            }
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

    public Clientes login(String email, String password) {
        String sql = "SELECT CL_EMAIL, CL_PASSWORD FROM CLIENTES WHERE CL_EMAIL = ? AND CL_PASSWORD = ?";
        _motorOracle.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Clientes cliente = new Clientes();
        try {
            pstmt = _motorOracle.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente.setEmail(rs.getString(1));
                cliente.setPassword(rs.getString(2));
            } else {
                return null;
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


    public ArrayList<Clientes> find(Clientes filter) {
        ArrayList<Clientes> clientes = new ArrayList<>();
        _motorOracle.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            _motorOracle.connect();
            StringBuilder sql = new StringBuilder(SQL_FIND);

            if (filter.getNombre() != null && !filter.getNombre().isEmpty()) {
                sql.append(" AND CL_NOMBRE LIKE ?");
            }
            if (filter.getApellido() != null && !filter.getApellido().isEmpty()) {
                sql.append(" AND CL_APELLIDO LIKE ?");
            }
            if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                sql.append(" AND CL_EMAIL = ?");
            }

            pstmt = _motorOracle.prepareStatement(sql.toString());

            int index = 1;

            if (filter.getNombre() != null && !filter.getNombre().isEmpty()) {
                pstmt.setString(index++, "%" + filter.getNombre() + "%");
            }
            if (filter.getApellido() != null && !filter.getApellido().isEmpty()) {
                pstmt.setString(index++, "%" + filter.getApellido() + "%");
            }
            if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                pstmt.setString(index++, filter.getEmail());
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setIdCliente(rs.getString("ID_CLIENTE"));
                cliente.setNombre(rs.getString("CL_NOMBRE"));
                cliente.setApellido(rs.getString("CL_APELLIDO"));
                cliente.setEmail(rs.getString("CL_EMAIL"));
                cliente.setPassword(rs.getString("CL_PASSWORD"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return clientes;
    }
}