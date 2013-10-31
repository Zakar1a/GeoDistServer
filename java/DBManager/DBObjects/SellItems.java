/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager.DBObjects;

import DBManager.DBConnectionManager;
import Utils.LookUp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class SellItems {

    /**

    item_id bigint NOT NULL DEFAULT nextval('bgschema."SalesItem_ItemID_seq"'::regclass),
    item_name character varying(100),
    business_name character varying(200),
    category bgschema.item_category,
    subcategory character varying(30),

     */
    private int itemID;
    private String itemName;
    private String businessName;
    private String category;
    private String subCategory;

    //getters and setters
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String insert(
            String itemName,
            String businessName,
            String category,
            String subCategory) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "INSERT INTO bgschema.t_sales_item( " +
                        "item_name, business_name, category, subcategory) " +
                        "VALUES ( ?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, itemName);
                stmt.setString(2, businessName);
                stmt.setString(3, category);
                stmt.setString(4, subCategory);
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
            int originalItemID,
            String itemName,
            String businessName,
            String category,
            String subCategory) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "UPDATE bgschema.t_sales_item" +
                        "SET item_name=?, business_name=?, category=?, subcategory=?" +
                        "WHERE item_id=?";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, itemName);
                stmt.setString(2, businessName);
                stmt.setString(3, category);
                stmt.setString(4, subCategory);
                stmt.setInt(5, originalItemID);
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

    public SellItems getById(int itemId) {
        SellItems saleItems = new SellItems();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM bgschema.t_sales_item where item_id=?";

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, itemId);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    saleItems.setItemID(rs.getInt("item_id"));
                    saleItems.setItemName(rs.getString("item_name"));
                    saleItems.setBusinessName(rs.getString("business_name"));
                    saleItems.setCategory(rs.getString("category"));
                    saleItems.setSubCategory(rs.getString("subcategory"));
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

        return saleItems;
    }
}
