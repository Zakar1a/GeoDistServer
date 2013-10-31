/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBObjectManager;

import DBManager.DBObjects.Shop;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.util.HashMap;
import org.postgis.PGgeometry;
import org.postgis.Point;

/**
 *
 * @author ASUS
 */
public class ShopManager {
    public HashMap addNewShop(
            String shopName,
            String latStr,
            String lngStr,
            String owner,
            int shopRating,
            String address,
            String phone) {
        HashMap responseHM = new HashMap();
        try {
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(latStr);
            locLng = Double.parseDouble(lngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);

            String msg = new Shop().insert(shopName, locGeo, owner, shopRating, address, phone);
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap getShop(
            int originalShopId) {
        HashMap responseHM = new HashMap();
        try {
            Shop shop = new Shop().getById(originalShopId);

            HashMap shopHM = new HashMap();
            shopHM.put(JSONKeys.SHOP_ID, shop.getShopID());
            shopHM.put(JSONKeys.SHOP_NAME, shop.getShopName());
            shopHM.put(JSONKeys.LOCATION, shop.getLocation().toString());
            shopHM.put(JSONKeys.OWNER, shop.getOwner());
            shopHM.put(JSONKeys.SHOP_RATING, shop.getShopRating());
            shopHM.put(JSONKeys.ADDRESS, shop.getAddress());
            shopHM.put(JSONKeys.PHONE, shop.getPhone());

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, "");
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap modifyShop(
            String shopName,
            String latStr,
            String lngStr,
            String owner,
            int shopRating,
            String address,
            String phone,
            int originalShopId) {
        HashMap responseHM = new HashMap();
        try {
            Double locLat = 0.0, locLng = 0.0;
            locLat = Double.parseDouble(latStr);
            locLng = Double.parseDouble(lngStr);
            Point loc = new Point(locLat, locLng);
            PGgeometry locGeo = new PGgeometry(loc);

            String msg = new Shop().update(shopName, locGeo, owner, shopRating, address, phone, originalShopId);
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }
}
