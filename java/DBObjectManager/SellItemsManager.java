/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBObjectManager;

import DBManager.DBObjects.SellItems;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class SellItemsManager {

    public HashMap addNewSellItems(
            String itemName,
            String businessName,
            String category,
            String subCategory) {
        HashMap responseHM = new HashMap();
        try {
            String msg = new SellItems().insert( itemName, businessName, category, subCategory);

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap getSellItem(
            int originalSellItemId) {
        HashMap responseHM = new HashMap();
        try {
            SellItems sellItems = new SellItems().getById(originalSellItemId);
            HashMap sellItemHM = new HashMap();
            sellItemHM.put(JSONKeys.ITEM_ID, sellItems.getItemID());
            sellItemHM.put(JSONKeys.ITEM_NAME, sellItems.getItemName());
            sellItemHM.put(JSONKeys.BUSINESS_NAME, sellItems.getBusinessName());
            sellItemHM.put(JSONKeys.CATEGORY, sellItems.getCategory());
            sellItemHM.put(JSONKeys.SUBCATEGORY, sellItems.getSubCategory());

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, sellItemHM);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap modifySellItem(
            String itemName,
            String businessName,
            String category,
            String subCategory,
            int originalSellItemId) {
        HashMap responseHM = new HashMap();
        try {
            String msg = new SellItems().update(originalSellItemId, itemName, businessName, category, subCategory);

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, "");
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }
}
