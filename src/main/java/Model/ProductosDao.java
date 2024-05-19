package Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class ProductosDao implements IDao <Productos, Integer> {

    private final String SQL_FIND_ALL = "select * from PRODUCTOS WHERE 1=1 ORDER BY ID_PRODUCTO";

    private final String SQL_DELETE = "DELETE FROM PRODUCTOS WHERE ID_PRODUCTO =  ";

    private final String SQL_FIND_BY_FILTER =
            "SELECT ID_CATEGORIA_PRD " +
                    "FROM PRODUCTOS" ;

    private final String SQL_ADD = "INSERT INTO PRODUCTOS (ID_PRODUCTO, PRD_NOMBRE, PRD_PRECIO_VENTA, PRD_DESCRIPCION, PRD_IMAGEN_RUTA, PRD_ESTADO, ID_CATEGORIA_PRD) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public int add(Productos bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        PreparedStatement pstmt = null;
        try {
            System.out.println("Connecting to database...");
            motor.connect();
            System.out.println("Preparing statement...");
            pstmt = motor.prepareStatement(SQL_ADD);
            System.out.println("Setting parameters...");
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

            System.out.println("Executing update...");
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
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_DELETE + id ;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Borrado con exito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }

    @Override
    public int update(Productos bean) {


        throw new UnsupportedOperationException("Not supported yet.");
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
