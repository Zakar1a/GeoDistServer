/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBObjectManager;

import DBManager.DBObjects.Salesman;
import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class SalesmanManager {

    public HashMap addNewSalesman(
            String name,
            String address,
            String phone,
            String curAreaCode) {
        HashMap responseHM = new HashMap();
        try {
            String msg = new Salesman().insert(name, address, phone, curAreaCode);

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap getSalesman(
            int originalSalesmanId) {
        HashMap responseHM = new HashMap();
        try {
            Salesman salesman = new Salesman().getById(originalSalesmanId);

            HashMap salesmanHM = new HashMap();
            salesmanHM.put(JSONKeys.SALESMAN_ID, salesman.getSalesmanID());
            salesmanHM.put(JSONKeys.NAME, salesman.getName());
            salesmanHM.put(JSONKeys.ADDRESS, salesman.getAddress());
            salesmanHM.put(JSONKeys.PHONE, salesman.getPhone());
            salesmanHM.put(JSONKeys.CURRENT_AREA_CODE, salesman.getCurAreaCode());

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, salesmanHM);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }

    public HashMap modifySalesman(
            String name,
            String address,
            String phone,
            String curAreaCode,
            int originalSalesmanId) {
        HashMap responseHM = new HashMap();
        try {
            String msg = new Salesman().update(originalSalesmanId, name, address, phone, curAreaCode);

            responseHM.put(JSONKeys.STATUS, RespStatus.SUCCESS);
            responseHM.put(JSONKeys.CONTENT, msg);
        } catch (Exception ex) {
            responseHM.put(JSONKeys.STATUS, RespStatus.SQL_ERROR);
            responseHM.put(JSONKeys.CONTENT, ex.getMessage());
        }
        return responseHM;
    }
}
