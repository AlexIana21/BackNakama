package Model;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DescuentoDao implements IDao <Descuento, Integer> {

    private final String SQL_FIND_ALL = "SELECT * FROM DESCUENTO WHERE 1=1 ";

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
                if (((Descuento)bean).getIdDescuento() != null) {
                    sql += " AND ID_DESCUENTO='" + ((Descuento)bean).getIdDescuento() + "'";
                }
                if (((Descuento)bean).getNombre() != null) {
                    sql += " AND DES_NOMBRE='" + ((Descuento)bean).getNombre() + "'";
                }
                if (((Descuento)bean).getFechaInicio() != null) {
                    sql += " AND DES_FECHA_INICIO='" + new java.sql.Date(((Descuento)bean).getFechaInicio().getTime()) + "'";
                }
                if (((Descuento)bean).getFechaFinal() != null) {
                    sql += " AND DES_FECHA_FINAL='" + new java.sql.Date(((Descuento)bean).getFechaFinal().getTime()) + "'";
                }
                if (((Descuento)bean).getCantidad() > 0) {
                    sql += " AND DES_CANTIDAD=" + ((Descuento)bean).getCantidad();
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Descuento descuento = new Descuento();
                descuento.setIdDescuento(rs.getString("ID_DESCUENTO"));
                descuento.setNombre(rs.getString("NOMBRE"));
                descuento.setFechaInicio(rs.getDate("DES_FECHA_INICIO"));
                descuento.setFechaFinal(rs.getDate("DES_FECHA_FIN"));
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

    /*
  public ArrayList<Descuento> findAll(Object bean) {
    ArrayList<Descuento> descuentos = new ArrayList<>();
    // Establecer la fecha de inicio
    Calendar calendarInicio = Calendar.getInstance();
    calendarInicio.set(2024, Calendar.FEBRUARY, 24, 0, 0, 0);
    calendarInicio.set(Calendar.MILLISECOND, 0);
    Date fechaInicio = calendarInicio.getTime();

    // Establecer la fecha de fin
    Calendar calendarFin = Calendar.getInstance();
    calendarFin.set(2024, Calendar.FEBRUARY, 28, 23, 59, 59);
    calendarFin.set(Calendar.MILLISECOND, 999);
    Date fechaFin = calendarFin.getTime();

    // nuevo Descuento a la lista
    descuentos.add(new Descuento("DE01", "halloween", fechaInicio, fechaFin, 5));

    return descuentos;
}
    */


}