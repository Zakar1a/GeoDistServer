/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBObjectManager;

import DBManager.DBObjects.Sales;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.sql.Date;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class SalesManager {
    public HashMap addNewSellInfo(
            int itemID,
            int shopID,
            int salesmanID,
            double amount,
            double price,
            Date dateTime) {
        HashMap responseHM = new HashMap();
        try {
            String msg = new Sales().insert(itemID, shopID, salesmanID, amount, price, dateTime);
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap getSellInfo(
            int originalSalesId) {
        HashMap responseHM = new HashMap();
        try {
            Sales sales  = new Sales().getById(originalSalesId);
            
            HashMap salesHM = new HashMap();
            salesHM.put(JSONKeys.SALES_ID, sales.getSalesID());
            salesHM.put(JSONKeys.SHOP_ID, sales.getShopID());
            salesHM.put(JSONKeys.SALESMAN_ID, sales.getSalesmanID());
            salesHM.put(JSONKeys.AMOUNT, sales.getAmount());
            salesHM.put(JSONKeys.PRICE, sales.getPrice());
            salesHM.put(JSONKeys.DATE_TIME, sales.getDateTime());

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, salesHM);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap modifySellInfo(int itemID,
            int shopID,
            int salesmanID,
            double amount,
            double price,
            Date dateTime,
            int originalSalesId) {
        HashMap responseHM = new HashMap();
        try {
            String msg = new Sales().update(originalSalesId, itemID, shopID, salesmanID, amount, price, dateTime);
            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }
}
