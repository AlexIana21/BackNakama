package Model;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDao implements IDao {

    private final String SQL_FIND_ALL = "SELECT * FROM CATEGORIA WHERE 1=1 ";

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
    public ArrayList<Categoria> findAll(Object bean) {
        ArrayList<Categoria> categorias = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Categoria) bean).getIdCategoria() != null) {
                    sql += " AND ID_CATEGORIA='" + ((Categoria) bean).getIdCategoria() + "'";
                }
                if (((Categoria) bean).getNombre() != null) {
                    sql += " AND CAT_NOMBRE='" + ((Categoria) bean).getNombre() + "'";
                }
                if (((Categoria) bean).getDescripcion() != null) {
                    sql += " AND CAT_DESCRIPCION=" + ((Categoria) bean).getDescripcion() + "";
                }

            }
            ResultSet rs = motor.executeQuery(sql);


            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getString("ID_CATEGORIA"));
                categoria.setNombre(rs.getString("CAT_NOMBRE"));
                categoria.setDescripcion(rs.getString("CAT_DESCRIPCION"));


                categorias.add(categoria);
            }

        } catch (Exception ex) {
            categorias.clear();
        } finally {
            motor.disconnect();
        }
        return categorias;
    }


    /*public static ArrayList<Categoria> findAll(Object bean)
    {
        ArrayList<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("CAT01", "Burgers", "Hamburguesas"));
        return categorias;
    }*/


}








