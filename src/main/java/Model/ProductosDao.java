package Model;
import Model.Factory.DatabaseFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class ProductosDao implements IDao <Productos, Integer> {

    private final String SQL_FIND_ALL = "select * from PRODUCTOS WHERE PRD_ESTADO =1 ";
    private final String SQL_FIND_ALL_ADMIN = "select * from PRODUCTOS WHERE 1=1 ";

    private final String SQL_DELETE = "DELETE FROM PRODUCTOS WHERE ID_PRODUCTO = ? ";

    private final String SQL_UPDATE = "UPDATE PRODUCTOS SET ";

    private final String SQL_FIND_BY_FILTER =
            "SELECT ID_CATEGORIA_PRD " +
                    "FROM PRODUCTOS";

    private final String SQL_ADD = "INSERT INTO PRODUCTOS (ID_PRODUCTO, PRD_NOMBRE, PRD_PRECIO_VENTA, PRD_DESCRIPCION, PRD_IMAGEN_RUTA, PRD_ESTADO, ID_CATEGORIA_PRD) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final String SQL_FIND_BY_ID = "SELECT * FROM PRODUCTOS WHERE ID_PRODUCTO = ?";
    private MotorOracle _motorOracle;

    public ProductosDao(String db) {
        _motorOracle = DatabaseFactory.getDatabase(db);
    }

    @Override
    public int add(Productos bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        PreparedStatement pstmt = null;
        try {
            // Aqui esta el codigo para ver los erroes que me daba pero se puede hacer sin sout
            motor.connect();
            pstmt = motor.prepareStatement(SQL_ADD);
            System.out.println("ID_PRODUCTO: " + bean.getIdProducto());
            pstmt.setString(1, bean.getIdProducto());
            System.out.println("PRD_NOMBRE: " + bean.getNombre());
            pstmt.setString(2, bean.getNombre());
            System.out.println("PRD_PRECIO_VENTA: " + bean.getPrecioVenta());
            pstmt.setDouble(3, bean.getPrecioVenta());
            System.out.println("PRD_DESCRIPCION: " + bean.getDescripcion());
            pstmt.setString(4, bean.getDescripcion());
            System.out.println("PRD_IMAGEN_RUTA: " + bean.getImagenRuta());
            pstmt.setString(5, bean.getImagenRuta());
            System.out.println("PRD_ESTADO: " + bean.getEstado());
            pstmt.setBoolean(6, bean.getEstado());
            System.out.println("ID_CATEGORIA_PRD: " + bean.getIdCategoria());
            pstmt.setString(7, bean.getIdCategoria());

            resp = pstmt.executeUpdate();
            System.out.println("Update executed, response: " + resp);
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
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
            System.out.println("Error al eliminar producto: " + e.getMessage());
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
    public int update(Productos bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        PreparedStatement pstmt = null;
        try {
            motor.connect();

            // Construir la consulta SQL dinámicamente
            StringBuilder sql = new StringBuilder(SQL_UPDATE);
            ArrayList<Object> params = new ArrayList<>();

            if (bean.getNombre() != null) {
                sql.append("PRD_NOMBRE = ?, ");
                params.add(bean.getNombre());
            }
            if (bean.getPrecioVenta() > 0) {
                sql.append("PRD_PRECIO_VENTA = ?, ");
                params.add(bean.getPrecioVenta());
            }
            if (bean.getDescripcion() != null) {
                sql.append("PRD_DESCRIPCION = ?, ");
                params.add(bean.getDescripcion());
            }
            if (bean.getImagenRuta() != null) {
                sql.append("PRD_IMAGEN_RUTA = ?, ");
                params.add(bean.getImagenRuta());
            }
            if (bean.getEstado() == true) {
                sql.append("PRD_ESTADO = ?, ");
                params.add(bean.getEstado() ? 1 : 0);
            }
            if (bean.getIdCategoria() != null) {
                sql.append("ID_CATEGORIA_PRD = ?, ");
                params.add(bean.getIdCategoria());
            }

            //Eliminar la última coma y agregar la cláusula WHERE
            sql.setLength(sql.length() - 2);
            sql.append(" WHERE ID_PRODUCTO = ?");
            params.add(bean.getIdProducto());

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

    public Productos findByIdProductos(String id) {
        Productos producto = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            _motorOracle.connect();
            pstmt = _motorOracle.prepareStatement(SQL_FIND_BY_ID);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                producto = new Productos();
                producto.setIdProducto(rs.getString("ID_PRODUCTO"));
                producto.setNombre(rs.getString("PRD_NOMBRE"));
                producto.setPrecioVenta(rs.getDouble("PRD_PRECIO_VENTA"));
                producto.setDescripcion(rs.getString("PRD_DESCRIPCION"));
                producto.setImagenRuta(rs.getString("PRD_IMAGEN_RUTA"));
                producto.setEstado(rs.getInt("PRD_ESTADO") == 1);
                producto.setIdCategoria(rs.getString("ID_CATEGORIA_PRD"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                _motorOracle.disconnect();
            } catch (SQLException se) {
                System.out.println("Error al cerrar recursos: " + se.getMessage());
                se.printStackTrace();
            }
        }
        return producto;
    }

    public ArrayList<Productos> findAllByCategory(Productos bean, boolean orderByIdProducto ) {
        ArrayList<Productos> productos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_BY_FILTER;
            if (bean != null) {
                if (((Productos)bean).getIdProducto() != null) {
                    sql += " AND ID_PRODCUTO='" + ((Productos)bean).getIdProducto() + "'";
                }
                if (((Productos)bean).getNombre() != null) {
                    sql += " AND PRD_NOMBRE='" + ((Productos)bean).getNombre() + "'";
                }
                if (((Productos)bean).getPrecioVenta() > 0) {
                    sql += " AND PRD_PRECIO_VENTA=" + ((Productos)bean).getPrecioVenta() + "'";
                }
                if (((Productos)bean).getDescripcion() != null) {
                    sql += " AND PRD_DESCRIPCION='" + ((Productos)bean).getDescripcion() + "'";
                }
                if (((Productos)bean).getImagenRuta() != null) {
                    sql += " AND PRD_IMAGEN_RUTA='"+ ((Productos)bean).getImagenRuta() + "'";
                }
                if (((Productos)bean).getEstado() == true) {
                    sql += " AND PRD_ESTADO='" + ((Productos)bean).getEstado() + "'";
                }
                if (((Productos)bean).getIdCategoria() != null) {
                    sql += " AND ID_CATEGORIA_PRD='" + ((Productos)bean).getIdCategoria() + "'";
                }
            }
            sql += " ORDER BY ID_PRODUCTO";
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Productos producto = new Productos();
                producto.setIdProducto(rs.getString("ID_PRODUCTO"));
                producto.setNombre(rs.getString("PRD_NOMBRE"));
                producto.setPrecioVenta(rs.getDouble("PRD_PRECIO_VENTA"));
                producto.setDescripcion(rs.getString("PRD_DESCRIPCION"));
                producto.setImagenRuta(rs.getString("PRD_IMAGEN_RUTA"));
                producto.setEstado(rs.getInt("PRD_ESTADO") == 1);
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getString("ID_CATEGORIA_PRD"));
                producto.setIdCategoria(categoria.getIdCategoria());
                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        return productos;
    }

    @Override
    public ArrayList<Productos> findAll(Productos bean) {
        ArrayList<Productos> productos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Productos)bean).getIdProducto() != null) {
                    sql += " AND ID_PRODCUTO='" + ((Productos)bean).getIdProducto() + "'";
                }
                if (((Productos)bean).getNombre() != null) {
                    sql += " AND PRD_NOMBRE='" + ((Productos)bean).getNombre() + "'";
                }
                if (((Productos)bean).getPrecioVenta() > 0) {
                    sql += " AND PRD_PRECIO_VENTA=" + ((Productos)bean).getPrecioVenta() + "'";
                }
                if (((Productos)bean).getDescripcion() != null) {
                    sql += " AND PRD_DESCRIPCION='" + ((Productos)bean).getDescripcion() + "'";
                }
                if (((Productos)bean).getImagenRuta() != null) {
                    sql += " AND PRD_IMAGEN_RUTA='"+ ((Productos)bean).getImagenRuta() + "'";
                }
                if (((Productos)bean).getEstado() == true) {
                    sql += " AND PRD_ESTADO='" + ((Productos)bean).getEstado() + "'";
                }
                if (((Productos)bean).getIdCategoria() != null) {
                    sql += " AND ID_CATEGORIA_PRD='" + ((Productos)bean).getIdCategoria() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Productos producto = new Productos();
                producto.setIdProducto(rs.getString("ID_PRODUCTO"));
                producto.setNombre(rs.getString("PRD_NOMBRE"));
                producto.setPrecioVenta(rs.getDouble("PRD_PRECIO_VENTA"));
                producto.setDescripcion(rs.getString("PRD_DESCRIPCION"));
                producto.setImagenRuta(rs.getString("PRD_IMAGEN_RUTA"));
                producto.setEstado(rs.getInt("PRD_ESTADO") == 1);
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getString("ID_CATEGORIA_PRD"));
                producto.setIdCategoria(categoria.getIdCategoria());
                productos.add(producto);
            }

        }catch (Exception ex)
        {
            productos.clear();
        }
        finally {
            motor.disconnect();
        }
        return productos;
    }

}


