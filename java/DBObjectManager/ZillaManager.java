/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBObjectManager;

import DBManager.DBObjects.Zilla;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.util.HashMap;
import org.postgis.PGgeometry;
import org.postgis.Point;

/**
 *
 * @author ASUS
 */
public class ZillaManager {
    public HashMap addNewZilla() {
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

    public HashMap getZilla(
            String latStr,
            String lngStr) {
        HashMap responseHM = new HashMap();
        try {
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(latStr);
            locLng = Double.parseDouble(lngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);
            Zilla zilla = new Zilla().getContainingZilla(locGeo);
            HashMap zillaHM = new HashMap();
            zillaHM.put(JSONKeys.ZILLA_ID, zilla.getgID());
            zillaHM.put(JSONKeys.FIRST_DIST, zilla.getFirstDist());
            zillaHM.put(JSONKeys.GEO_CODE, zilla.getGeoCode());
            zillaHM.put(JSONKeys.AVE_AREA, zilla.getAveArea());
            zillaHM.put(JSONKeys.FRE, zilla.getFre());
            zillaHM.put(JSONKeys.PER, zilla.getPer());
            zillaHM.put(JSONKeys.SEC, zilla.getSec());
            zillaHM.put(JSONKeys.TOP, zilla.getTop());
            //zillaHM.put(JSONKeys.THE_GEOM, zilla.getTheGeom());

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, zilla);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap modifyZilla() {
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
