/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager.DBObjects;

import DBManager.DBConnectionManager;
import Utils.LookUp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zakaria
 */
public class Salesman {

    private int salesmanID;
    private String name;
    private String address;
    private String phone;
    private String curAreaCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurAreaCode() {
        return curAreaCode;
    }

    public void setCurAreaCode(String curAreaCode) {
        this.curAreaCode = curAreaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalesmanID() {
        return salesmanID;
    }

    public void setSalesmanID(int salesmanID) {
        this.salesmanID = salesmanID;
    }

    public String insert(
            String name,
            String address,
            String phone,
            String curAreaCode) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "INSERT INTO bgschema.t_salesman( " +
                        "\"name\", address, phone, cur_area_code) " +
                        "VALUES ( ?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, name);
                stmt.setString(2, address);
                stmt.setString(3, phone);
                stmt.setString(4, curAreaCode);
                int rowEffected = stmt.executeUpdate();
                conn.commit();
                if (rowEffected > 0) {
                    message = LookUp.MSGKeys.INSERTED.toString();
                } else {
                    message = LookUp.MSGKeys.INSERTION_FAILED.toString();
                }
            } catch (Exception exInn) {
                message = exInn.getMessage();
            }
        } catch (Exception exCon) {
            message = exCon.getMessage();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return message;
    }

    public String update(
            int originalSalesmanID,
            String name,
            String address,
            String phone,
            String curAreaCode) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "UPDATE bgschema.t_salesman " +
                        "SET \"name\"=?, address=?, phone=?, cur_area_code=? " +
                        "WHERE salesman_id=?";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, name);
                stmt.setString(2, address);
                stmt.setString(3, phone);
                stmt.setString(4, curAreaCode);
                stmt.setInt(5, originalSalesmanID);
                int rowEffected = stmt.executeUpdate();
                conn.commit();
                if (rowEffected > 0) {
                    message = LookUp.MSGKeys.INSERTED.toString();
                } else {
                    message = LookUp.MSGKeys.INSERTION_FAILED.toString();
                }
            } catch (Exception exInn) {
                message = exInn.getMessage();
            }
        } catch (Exception exCon) {
            message = exCon.getMessage();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return message;
    }

    public Salesman getById(int salesmanId) {
        Salesman salesman = new Salesman();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM bgschema.t_salesman where salesman_id=?";

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, salesmanId);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    salesman.setSalesmanID(rs.getInt("salesman_id"));
                    salesman.setName(rs.getString("name"));
                    salesman.setAddress(rs.getString("address"));
                    salesman.setPhone(rs.getString("phone"));
                    salesman.setCurAreaCode(rs.getString("cur_area_code"));
                }
            } catch (Exception exInn) {
            }
        } catch (Exception exCon) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return salesman;
    }
}
