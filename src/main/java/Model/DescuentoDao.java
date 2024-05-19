/*package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DescuentoDao implements IDao<Descuento, Integer> {

    private final String SQL_FIND_ALL = "SELECT * FROM DESCUENTO WHERE 1=1 ";
    private final String SQL_FIND_BY_ID = "SELECT * FROM DESCUENTO WHERE ID_DESCUENTO = ?";

    @Override
    public int add(Descuento bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Descuento bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Descuento> findAll(Descuento bean) {
        ArrayList<Descuento> descuentos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Descuento) bean).getIdDescuento() != null) {
                    sql += " AND ID_DESCUENTO='" + ((Descuento) bean).getIdDescuento() + "'";
                }
                if (((Descuento) bean).getNombre() != null) {
                    sql += " AND DES_NOMBRE='" + ((Descuento) bean).getNombre() + "'";
                }
                if (((Descuento) bean).getFechaInicio() != null) {
                    sql += " AND DES_FECHA_INICIO='" + new java.sql.Date(((Descuento) bean).getFechaInicio().getTime()) + "'";
                }
                if (((Descuento) bean).getFechaFinal() != null) {
                    sql += " AND DES_FECHA_FINAL='" + new java.sql.Date(((Descuento) bean).getFechaFinal().getTime()) + "'";
                }
                if (((Descuento) bean).getCantidad() > 0) {
                    sql += " AND DES_CANTIDAD=" + ((Descuento) bean).getCantidad();
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Descuento descuento = new Descuento();
                descuento.setIdDescuento(rs.getString("ID_DESCUENTO"));
                descuento.setNombre(rs.getString("NOMBRE"));
                descuento.setFechaInicio(rs.getDate("DES_FECHA_INICIO"));
                descuento.setFechaFinal(rs.getDate("DES_FECHA_FINAL"));
                descuento.setCantidad(rs.getInt("DES_CANTIDAD"));
                descuentos.add(descuento);
            }

        } catch (Exception ex) {
            descuentos.clear();
        } finally {
            motor.disconnect();
        }
        return descuentos;
    }

    public Descuento findById(String idDescuento) {
        Descuento descuento = null;
        MotorOracle motor = new MotorOracle();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            motor.connect();
            pstmt = motor.prepareStatement(SQL_FIND_BY_ID);
            pstmt.setString(1, idDescuento);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                descuento = new Descuento(
                        rs.getString("ID_DESCUENTO"),
                        rs.getString("NOMBRE"),
                        rs.getDate("DES_FECHA_INICIO"),
                        rs.getDate("DES_FECHA_FINAL"),
                        rs.getInt("DES_CANTIDAD")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar descuento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                System.out.println("Error al cerrar recursos: " + se.getMessage());
                se.printStackTrace();
            }
        }
        return descuento;
    }
}*/
