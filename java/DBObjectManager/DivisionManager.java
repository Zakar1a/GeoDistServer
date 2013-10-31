/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBObjectManager;

import DBManager.DBObjects.Division;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.util.HashMap;
import org.postgis.PGgeometry;
import org.postgis.Point;

/**
 *
 * @author Zakaria
 */
public class DivisionManager {
    public HashMap getContainingDivisionInfo(String latStr,
            String lngStr){
        HashMap responseHM = new HashMap();
        try{
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(latStr);
            locLng = Double.parseDouble(lngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);
            Division division = new Division().getContainingDivisoin(locGeo);
            HashMap divisionHM = new HashMap();
            divisionHM.put(JSONKeys.DIVISION_ID, division.getgID());
            divisionHM.put(JSONKeys.REC_ID, division.getRecID());
            divisionHM.put(JSONKeys.DIVISION_CODE, division.getDivCode());
            divisionHM.put(JSONKeys.SUM_AREA, division.getSumArea());
            divisionHM.put(JSONKeys.DIVISION_NAME, division.getDivName());
            divisionHM.put(JSONKeys.AR_SQKM, division.getArSqkm());
            //divisionHM.put(JSONKeys.THE_GEOM, division.getTheGeom());
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, divisionHM);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }
}
