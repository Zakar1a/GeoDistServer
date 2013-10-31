/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBObjectManager;

import DBManager.DBObjects.Upazilla;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.util.HashMap;
import org.postgis.PGgeometry;
import org.postgis.Point;

/**
 *
 * @author ASUS
 */
public class UpazillaManager {
    public HashMap addNewUpazilla() {
        HashMap responseHM = new HashMap();
        try {


            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, "");
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap getUpazilla(
            String latStr,
            String lngStr) {
        HashMap responseHM = new HashMap();
        try {
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(latStr);
            locLng = Double.parseDouble(lngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);
            Upazilla upazilla = new Upazilla().getContainingUpazilla(locGeo);

            HashMap upazillaHM = new HashMap();
            upazillaHM.put(JSONKeys.UPAZILLA_ID, upazilla.getgID());
            upazillaHM.put(JSONKeys.AREA, upazilla.getArea());
            upazillaHM.put(JSONKeys.PERIMETER, upazilla.getPerimeter());
            upazillaHM.put(JSONKeys.THANA, upazilla.getThana());
            upazillaHM.put(JSONKeys.THANA_ID, upazilla.getThanaID());
            upazillaHM.put(JSONKeys.DISTBNDC_I, upazilla.getDistbndc_i());
            upazillaHM.put(JSONKeys.DIVISION_NAME, upazilla.getDivName());
            upazillaHM.put(JSONKeys.DIST_NAME, upazilla.getDistName());
            upazillaHM.put(JSONKeys.THANA_NAME, upazilla.getThanaName());
            upazillaHM.put(JSONKeys.AREA__SR_K, upazilla.getArea__sr_k());
            upazillaHM.put(JSONKeys.DENSITY_SQ, upazilla.getDensitySq());
            upazillaHM.put(JSONKeys.POPULATION, upazilla.getPopulation());
            // upzillaHM.put(JSONKeys.THE_GEOM, upazilla.getTheGeom().toString());

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, upazillaHM);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap modifyUpazilla() {
        HashMap responseHM = new HashMap();
        try {


            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, "");
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }
}
