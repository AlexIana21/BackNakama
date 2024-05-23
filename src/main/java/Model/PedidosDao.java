package Model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PedidosDao implements IDao<Pedidos, String> {
    private final String SQL_ADD = "INSERT INTO PEDIDOS (ID_PEDIDO, PED_HORA, PED_FECHA, PED_TELEFONO, PED_DIRECCION, PED_ESTADO, PED_PRECIO, ID_CLIENTE_PED, ID_EMPLEADO_PED) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        private final String SQL_FIND_ALL = "SELECT * FROM PEDIDOS ORDER BY ID_PEDIDO";

    @Override
    public int add(Pedidos bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        PreparedStatement pstmt = null;
        try {
            motor.connect();
            pstmt = motor.prepareStatement(SQL_ADD);


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date sqlFecha = new Date(bean.getFecha().getTime());
            Timestamp sqlHora = new Timestamp(bean.getHora().getTime());

            pstmt.setString(1, bean.getIdPedido());
            pstmt.setTimestamp(2, sqlHora);
            pstmt.setDate(3, sqlFecha);
            pstmt.setString(4, bean.getTlf());
            pstmt.setString(5, bean.getDireccion());
            pstmt.setBoolean(6, bean.getEstado());
            pstmt.setDouble(7, bean.getPrecio());
            pstmt.setString(8, bean.getIdCliente());
            pstmt.setString(9, bean.getIdEmpleado());

            resp = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar pedido: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                System.out.println("Error al cerrar recursos: " + se.getMessage());
                se.printStackTrace();
            }
        }
        return resp;
    }

   /* @Override
    public ArrayList<Pedidos> findAll(Pedidos bean) {
        ArrayList<Pedidos> pedidos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Pedidos)bean).getIdPedido() != null) {
                    sql += " AND ID_PEDIDO='" + ((Pedidos)bean).getIdPedido() + "'";
                }
                if (((Pedidos)bean).getHora() != null) {
                    sql += " AND PED_HORA='" + ((Pedidos)bean).getHora() + "'";
                }
                if (((Pedidos)bean).getFecha() != null) {
                    sql += " AND PED_FECHA=" + ((Pedidos)bean).getFecha() + "'";
                }
                if (((Pedidos)bean).getTlf() != null) {
                    sql += " AND PED_TELEFONO='" + ((Pedidos)bean).getTlf() + "'";
                }
                if (((Pedidos)bean).getDireccion() != null) {
                    sql += " AND PED_DIRECCION='"+ ((Pedidos)bean).getDireccion() + "'";
                }
                if (((Pedidos)bean).getEstado() == true) {
                    sql += " AND PED_ESTADO='" + ((Pedidos)bean).getEstado() + "'";
                }
                if (((Pedidos)bean).getPrecio() > 0) {
                    sql += " AND PED_PRECIO='" + ((Pedidos)bean).getPrecio() + "'";
                }
                if (((Pedidos)bean).getIdCliente() != null) {
                    sql += " AND ID_CLIENTE_PED='" + ((Pedidos)bean).getIdCliente() + "'";
                }
                if (((Pedidos)bean).getIdEmpleado() != null) {
                    sql += " AND ID_EMPLEADO_PED='" + ((Pedidos)bean).getIdEmpleado() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setIdPedido(rs.getString("ID_PEDIDO"));
                pedido.setHora(rs.getDate("PED_HORA"));
                pedido.setFecha(rs.getDate("PED_FECHA"));
                pedido.setTlf(rs.getString("PED_TELEFONO"));
                pedido.setDireccion(rs.getString("PED_DIRECCION"));
                pedido.setEstado(rs.getBoolean("PRD_ESTADO") == true);
                pedido.setPrecio(rs.getDouble("PED_PRECIO"));
                Clientes cliente = new Clientes();
                cliente.setIdCliente(rs.getString("ID_CLIENTE_PED"));
                pedido.setIdCliente(pedido.getIdCliente());
                Empleados empleado = new Empleados();
                empleado.setIdEmpleado(rs.getString("ID_EMPLEADO_PED"));
                pedido.setIdEmpleado(pedido.getIdEmpleado());

                pedidos.add(pedido);
            }

        }catch (Exception ex)
        {
            pedidos.clear();
        }
        finally {
            motor.disconnect();
        }
        return pedidos;
    }
    */


   @Override
    public ArrayList<Pedidos> findAll(Pedidos bean) {
        ArrayList<Pedidos> pedidos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getIdPedido() != null) {
                    sql += " AND ID_PEDIDO='" + bean.getIdPedido() + "'";
                }
                if (bean.getHora() != null) {
                    sql += " AND PED_HORA='" + new Timestamp(bean.getHora().getTime()) + "'";
                }
                if (bean.getFecha() != null) {
                    sql += " AND PED_FECHA='" + new Date(bean.getFecha().getTime()) + "'";
                }
                if (bean.getTlf() != null) {
                    sql += " AND PED_TELEFONO='" + bean.getTlf() + "'";
                }
                if (bean.getDireccion() != null) {
                    sql += " AND PED_DIRECCION='" + bean.getDireccion() + "'";
                }
                if (bean.getEstado() != null) {
                    sql += " AND PED_ESTADO=" + (bean.getEstado() ? 1 : 0);
                }
                if (bean.getPrecio() != null && bean.getPrecio() > 0) {
                    sql += " AND PED_PRECIO=" + bean.getPrecio();
                }
                if (bean.getIdCliente() != null) {
                    sql += " AND ID_CLIENTE_PED='" + bean.getIdCliente() + "'";
                }
                if (bean.getIdEmpleado() != null) {
                    sql += " AND ID_EMPLEADO_PED='" + bean.getIdEmpleado() + "'";
                }
            }


            System.out.println("Executing query: " + sql);
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setIdPedido(rs.getString("ID_PEDIDO"));
                pedido.setHora(rs.getTimestamp("PED_HORA"));
                pedido.setFecha(rs.getDate("PED_FECHA"));
                pedido.setTlf(rs.getString("PED_TELEFONO"));
                pedido.setDireccion(rs.getString("PED_DIRECCION"));
                pedido.setEstado(rs.getBoolean("PED_ESTADO"));
                pedido.setPrecio(rs.getDouble("PED_PRECIO"));
                pedido.setIdCliente(rs.getString("ID_CLIENTE_PED"));
                pedido.setIdEmpleado(rs.getString("ID_EMPLEADO_PED"));

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar pedidos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return pedidos;
    }

    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Pedidos bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
