/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBObjectManager;

import Utils.LookUp.JSONKeys;
import Utils.LookUp.RespStatus;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class UserManager {
    public HashMap addNewUser() {
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

    public HashMap getUser(
            int originalPersonPosId) {
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

    public HashMap modifyUser() {
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
