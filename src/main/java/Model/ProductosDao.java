package Model;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductosDao implements IDao <Productos, Integer> {

    private final String SQL_FIND_ALL = "SELECT * FROM PRODUCTOS WHERE 1=1 ";

    @Override
        public int add(Productos bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Productos bean) {


        throw new UnsupportedOperationException("Not supported yet.");
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
