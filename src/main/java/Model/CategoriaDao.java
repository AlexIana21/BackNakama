package Model;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDao implements IDao <Categoria, String> {

    private final String SQL_FIND_ALL = "SELECT * FROM CATEGORIA WHERE 1=1 ";

    @Override
    public int add(Categoria bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(String e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Categoria bean) {


        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public ArrayList<Categoria> findAll(Categoria bean) {
        ArrayList<Categoria> categorias = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
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




}








