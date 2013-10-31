/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager.DBObjects;

import DBManager.DBConnectionManager;
import Utils.LookUp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zakaria
 */
public class Sales {

    private int salesID;
    private int itemID;
    private int shopID;
    private int salesmanID;
    private double amount;
    private double price;
    private Date dateTime;

    //getters and setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }

    public int getSalesmanID() {
        return salesmanID;
    }

    public void setSalesmanID(int salesmanID) {
        this.salesmanID = salesmanID;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String insert(
            int itemID,
            int shopID,
            int salesmanID,
            double amount,
            double price,
            Date dateTime) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "INSERT INTO bgschema.t_sales( item_id, " +
                        "shop_id, salesman_id, amount, price, date_time) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);

                stmt.setInt(1, itemID);
                stmt.setInt(2, shopID);
                stmt.setInt(3, salesmanID);
                stmt.setDouble(4, amount);
                stmt.setDouble(5, price);
                stmt.setDate(6, dateTime);
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
            int originalSalesID,
            int itemID,
            int shopID,
            int salesmanID,
            double amount,
            double price,
            Date dateTime) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "UPDATE bgschema.t_sales SET item_id=?, shop_id=?, " +
                        "salesman_id=?, amount=?, price=?, date_time=? " +
                        "WHERE  sales_id=?";
                stmt = conn.prepareStatement(query);

                stmt.setInt(1, itemID);
                stmt.setInt(2, shopID);
                stmt.setInt(3, salesmanID);
                stmt.setDouble(4, amount);
                stmt.setDouble(5, price);
                stmt.setDate(6, dateTime);
                stmt.setInt(7, originalSalesID);
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

    public Sales getById(int salesId) {
        Sales sales = new Sales();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM bgschema.t_sales where sales_id=?";

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, salesId);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    sales.setSalesID(rs.getInt("sales_id"));
                    sales.setItemID(rs.getInt("item_id"));
                    sales.setShopID(rs.getInt("shop_id"));
                    sales.setSalesmanID(rs.getInt("salesman_id"));
                    sales.setAmount(rs.getDouble("amount"));
                    sales.setPrice(rs.getDouble("price"));
                    sales.setDateTime(rs.getDate("date_time"));
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

        return sales;
    }

}
