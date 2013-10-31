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
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgis.PGgeometry;

/**
 *
 * @author Zakaria
 */
public class Shop {

    private int shopID;
    private String shopName;
    private PGgeometry location;
    private String owner;
    private int shopRating;
    private String address;
    private String phone;

    //getters and setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PGgeometry getLocation() {
        return location;
    }

    public void setLocation(PGgeometry location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopRating() {
        return shopRating;
    }

    public void setShopRating(int shopRating) {
        this.shopRating = shopRating;
    }

    public String insert(
            String shopName,
            PGgeometry location,
            String owner,
            int shopRating,
            String address,
            String phone) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "INSERT INTO bgschema.t_shop( " +
                        " shop_name, \"location\", \"owner\", shop_rating, address, phone)" +
                        "VALUES ( ?, ?, ?, ?, ?, ?);";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, shopName);
                stmt.setObject(2, location);
                stmt.setString(3, owner);
                stmt.setInt(4, shopRating);
                stmt.setString(5, address);
                stmt.setString(6, phone);
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
            String shopName,
            PGgeometry location,
            String owner,
            int shopRating,
            String address,
            String phone,
            int originalShopId) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "INSERTUPDATE bgschema.t_shop SET shop_name=?, \"location\"=?, " +
                        "\"owner\"=?, shop_rating=?, address=?, phone=?" +
                        "WHERE shop_id=?";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, shopName);
                stmt.setObject(2, location);
                stmt.setString(3, owner);
                stmt.setInt(4, shopRating);
                stmt.setString(5, address);
                stmt.setString(6, phone);
                stmt.setInt(7, originalShopId);
                int rowEffected = stmt.executeUpdate();
                conn.commit();
                if (rowEffected > 0) {
                    message = LookUp.MSGKeys.UPDATES.toString();
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

    public Shop getById(int shopeId) {
        Shop shop = new Shop();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM t_shop where shop_id=?";

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, shopeId);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    shop.setAddress(rs.getString("address"));

                    PGgeometry geomObj = (PGgeometry) rs.getObject("location");
                    if (geomObj == null) {
                        shop.setLocation(null);
                    } else {
                        shop.setLocation(geomObj);
                    }
                    shop.setOwner(rs.getString("owner"));
                    shop.setPhone(rs.getString("phone"));
                    shop.setShopID(rs.getInt("shop_id"));
                    shop.setShopName(rs.getString("shop_name"));
                    shop.setShopRating(rs.getInt("shop_rating"));
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

        return shop;
    }
}
