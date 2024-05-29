package Model.Factory;

import Model.MotorOracle;

public class DatabaseFactory {

    public static final String ORACLE = "ORACLE";


    public static MotorOracle getDatabase(String tipo) {
        switch (tipo) {
            case ORACLE:
                return new MotorOracle();
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado");
        }
    }
}
