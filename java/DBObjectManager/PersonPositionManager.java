/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBObjectManager;

import DBManager.DBObjects.PersonPosition;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.sql.Date;
import java.util.HashMap;
import org.postgis.PGgeometry;
import org.postgis.Point;

/**
 *
 * @author ASUS
 */
public class PersonPositionManager {

    public HashMap addNewPersonPosition(
            String locLatStr,
            String locLngStr,
            int salesmanId,
            Date dateTime
            ) {
        HashMap responseHM = new HashMap();
        try {
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(locLatStr);
            locLng = Double.parseDouble(locLngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);
            String msg = new PersonPosition().insert(locGeo, salesmanId, dateTime);
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap getPersonPosition(
            int originalPersonPosId) {
        HashMap responseHM = new HashMap();
        try {
            PersonPosition personPosition = new PersonPosition().getById(originalPersonPosId);
            HashMap personPositionHM = new HashMap();
            personPositionHM.put(JSONKeys.PERSON_POS_ID, personPosition.getPersonPosID());
            personPositionHM.put(JSONKeys.LOCATION, personPosition.getLocation());
            personPositionHM.put(JSONKeys.SALESMAN_ID, personPosition.getSalesmanID());
            personPositionHM.put(JSONKeys.DATE_TIME, personPosition.getDateTime());

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, personPosition);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap modifyPersonPosition(
            String locLatStr,
            String locLngStr,
            int salesmanId,
            Date dateTime,
            int originalPersonPosId) {
        HashMap responseHM = new HashMap();
        try {
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(locLatStr);
            locLng = Double.parseDouble(locLngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);
            String msg = new PersonPosition().update(originalPersonPosId, locGeo, salesmanId, dateTime);
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }
}
