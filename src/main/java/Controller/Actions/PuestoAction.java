package Controller.Actions;

import Model.Puesto;
import Model.PuestoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PuestoAction implements IAction{
    @Override
    //ACTION=PUESTO.FIND_ALL+PUE_NOMBRE="Cocinero"+ID_PUESTO="PUE01"
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn ="";
        switch (action)
        {
            case "FIND_FIRST":
                //strReturn
                break;
            case "FIND_ALL":
                Puesto pue = new Puesto();
                pue.setIdPuesto("PUE01");
                pue.setDescripcion("Cocinero");
                strReturn = findAll(pue);
                strReturn = findAll(pue);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

   /* private String findAll(Puesto pue) {

         PuestoDao puestoDao = new PuestoDao();
        //ArrayList<Puesto> puestos = PuestoDao.findAll(null);
        ArrayList<Puesto> puestos;
        puestos = PuestoDao.findAll(null);
        return Puesto.toArrayJSon(puestos);

    }
    */


    private String findAll(Puesto puesto) {
        PuestoDao puestoDao = new PuestoDao();
        ArrayList<Puesto> puestos = puestoDao.findAll(null); // Usando instancia para llamar a findAll
        return Puesto.toArrayJSon(puestos); // Asumiendo que toArrayJSon debe ser estático correctamente
    }
}
