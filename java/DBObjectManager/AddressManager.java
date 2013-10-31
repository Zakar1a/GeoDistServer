/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBObjectManager;

import DBManager.DBObjects.Address;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.util.HashMap;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgresql.geometric.PGpoint;

/**
 *
 * @author Zakaria
 */
public class AddressManager {

    public HashMap addNewAddress(
            String addressDetail,
            String locLatStr,
            String locLngStr,
            int majorType,
            int minorType,
            String addPosLatStr,
            String addPosLngStr) {
        HashMap responseHM = new HashMap();
        try {
            Address address = new Address();
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(locLatStr);
            locLng = Double.parseDouble(locLngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);
            locLat = Double.parseDouble(addPosLatStr);
            locLng = Double.parseDouble(addPosLngStr);
            Point addPos = new Point(locLat, locLng);
            PGgeometry addPosGeo = new PGgeometry(addPos);

            String msg = address.insert(addressDetail, locGeo, majorType, minorType, addPosGeo);
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap getAddress(
            int originalPkId) {
        HashMap responseHM = new HashMap();
        try {

            HashMap addressHM = new HashMap();
            Address address = new Address().getById(originalPkId);
            addressHM.put(JSONKeys.ADDRESS_DETAIL, address.getAddressDetail());
            addressHM.put(JSONKeys.LOCATION, address.getLocation().toString());
            addressHM.put(JSONKeys.MINOR_TYPE, address.getMinorType());
            addressHM.put(JSONKeys.ADDRESS_POSITION, address.getAddressPos().toString());
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, addressHM);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap modifyAddress(String addressDetail,
            String locLatStr,
            String locLngStr,
            int majorType,
            int minorType,
            String addPosLatStr,
            String addPosLngStr,
            int originalAddressId) {
        HashMap responseHM = new HashMap();
        try {
            Address address = new Address();
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(locLatStr);
            locLng = Double.parseDouble(locLngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);
            locLat = Double.parseDouble(addPosLatStr);
            locLng = Double.parseDouble(addPosLngStr);
            Point addPos = new Point(locLat, locLng);
            PGgeometry addPosGeo = new PGgeometry(addPos);

            String msg = address.update(originalAddressId, addressDetail, locGeo, majorType, minorType, addPosGeo);
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }
}
