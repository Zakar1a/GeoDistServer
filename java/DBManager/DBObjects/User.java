/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBManager.DBObjects;

import DBManager.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgis.PGgeometry;
import org.postgresql.geometric.PGpoint;

/**
 *
 * @author Zakaria
 */
public class User {

    private int userID;
    private String userName;
    private String password;
    private String access;
    private String userType;
    private PGgeometry userPrimaryPos;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PGgeometry getUserPrimaryPos() {
        return userPrimaryPos;
    }

    public void setUserPrimaryPos(PGgeometry userPrimaryPos) {
        this.userPrimaryPos = userPrimaryPos;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    
    public boolean getLogIn(String struserName, String strPassword)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        boolean bIsPassMatched = false;
        try{
            conn = DBConnectionManager.getInstance().getConnection();
            
            String query = "SELECT * FROM t_users where user_name='" + struserName + "'";
             stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                while(rs.next())
                {
                    String strUsr = rs.getString("user_name");
                    String strPass = rs.getString("password");
                    
                    if(strPass.compareTo(strPassword) == 0 && strUsr.compareTo(struserName) == 0)
                        bIsPassMatched = true;
                }
               
            
        }
        catch(Exception exInn)
        {
              return bIsPassMatched;
        }
        return bIsPassMatched;
    }
    
    
    public Vector getUserInfoByName( String userName ) {
        Vector userList = new Vector();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        try{
            conn = DBConnectionManager.getInstance().getConnection();
            try{
                String query = "SELECT * FROM users";

                if(userName != null && !userName.isEmpty()){
                    query = query+" Where user_name="+userName;
                }
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                while(rs.next()){
                    HashMap userHM = new HashMap();
                    userHM.put("serial_no", rs.getInt("pk_id"));
                    userHM.put("user_name", rs.getString("user_name"));
                    //md5
                    userHM.put("password", rs.getString("password"));
                    //json data
                    userHM.put("access", rs.getString("access"));
                    userHM.put("user_type", rs.getString("user_type"));
                    PGgeometry geomObj = (PGgeometry)rs.getObject("user_primary_position");

                    if(geomObj == null)
                    {
                        userHM.put("position", "n/a");
                    }
                    else
                    {
                        userHM.put("position", geomObj.toString());
                    }
                    stmt.clearParameters();
                    String divisionQuery = "Select ";
                    stmt.execute(query);
                    userList.add(userHM);
                }
            }catch(Exception exInn){
                userList.add(exInn.getMessage());
            }
        }catch(Exception exCon){
            userList.add(exCon.getMessage());
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return userList;
    }

    public String addUser(
                String userName,
                String password,
                String access,
                int userType,
                String pLat,
                String pLng
            ) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try{
            conn = DBConnectionManager.getInstance().getConnection();
            try{
                String query = "INSERT INTO users(user_name, password, access, user_type, user_primary_position) VALUES (?, ?, ?, ?,GeomFromText(?,?) )";
                stmt = conn.prepareStatement(query);
                if(userName == null || userName.isEmpty()){
                    message = Utils.LookUp.MSGKeys.PARAM_MISSING.toString();
                    message = message.replaceFirst("[PARAM_NAME]", "user name");
                    return message;
                }
                stmt.setString(1, userName);
                if(password == null || password.isEmpty()){
                    message = "Please give password";
                    return message;
                }
                stmt.setString(2, password);
                stmt.setString(3, access);
                stmt.setInt(4, userType);
                stmt.setNull(5, Types.OTHER);
                stmt.setString(5, "POINT("+pLat+" "+pLng+")");
                stmt.setInt(6, 4326);
                int rowEffected = stmt.executeUpdate();
                conn.commit();
                if(rowEffected >0){
                    message = "Informaltion added successfully";
                }
                else
                {
                    message = "Insertion failed!!";
                }
            }catch(Exception exInn){
                message = exInn.getMessage();
            }
        }catch(Exception exCon){
            message = exCon.getMessage();
        }
        finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return message;
    }

    public String modifyUser(
                String userName,
                String oldPassword,
                String newPassword,
                String access,
                Integer userType,
                String pLat,
                String pLng
            ) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try{
            conn = DBConnectionManager.getInstance().getConnection();
            try{
                String query = "INSERT INTO users(user_name, password, access, user_type, user_primary_position) VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);
                if(userName == null || userName.isEmpty()){
                    message = "Please give user name";
                    return message;
                }
                stmt.setString(1, userName);
                if(oldPassword == null || oldPassword.isEmpty()){
                    message = "Please give password";
                    return message;
                }
                stmt.setString(2, oldPassword);
                stmt.setString(3, access);
                stmt.setInt(4, userType);
                stmt.setNull(5, Types.OTHER);
                //stmt.setObject(5,"point("+pLat+" "+pLng+")");
                if(stmt.execute() ){
                    message = "Informaltion added successfully";
                }
                else
                {
                    message = "Insertion failed!!";
                }
            }catch(Exception exInn){
                message = exInn.getMessage();
            }
        }catch(Exception exCon){
            message = exCon.getMessage();
        }
        finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return message;
    }
}

