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
import org.postgis.PGgeometry;

/**
 *
 * @author ASUS
 */
public class Address {

    private int addressId;
    private String addressDetail;
    private PGgeometry location;
    private int majorType;
    private int minorType;
    private PGgeometry addressPos;

    //getter and setter
    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String address) {
        this.addressDetail = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public PGgeometry getAddressPos() {
        return addressPos;
    }

    public void setAddressPos(PGgeometry addressPos) {
        this.addressPos = addressPos;
    }

    public PGgeometry getLocation() {
        return location;
    }

    public void setLocation(PGgeometry location) {
        this.location = location;
    }

    public int getMajorType() {
        return majorType;
    }

    public void setMajorType(int majorType) {
        this.majorType = majorType;
    }

    public int getMinorType() {
        return minorType;
    }

    public void setMinorType(int minorType) {
        this.minorType = minorType;
    }

    public String insert(
            String address,
            PGgeometry location,
            int majorType,
            int minorType,
            PGgeometry addressPos) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "INSERT INTO bgschema.t_address(address, loc, " +
                        " major_type, minor_type, address_position)" +
                        "VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, address);
                stmt.setObject(2, location);
                stmt.setInt(3, majorType);
                stmt.setInt(4, minorType);
                stmt.setObject(5, addressPos);
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
            int originalAddressId,
            String address,
            PGgeometry location,
            int majorType,
            int minorType,
            PGgeometry addressPos) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "UPDATE bgschema.t_address SET address=?, loc=?, " +
                        "major_type=?, minor_type=?, address_position=?" +
                        "WHERE pk_id=?;";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, address);
                stmt.setObject(2, location);
                stmt.setInt(3, majorType);
                stmt.setInt(4, minorType);
                stmt.setObject(5, addressPos);
                stmt.setInt(6, originalAddressId);
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

    public Address getById(int addressId) {
        Address addressObj = new Address();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM t_address where pk_id=?";

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, addressId);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    addressObj.setAddressDetail(rs.getString("address"));
                    addressObj.setAddressId(rs.getInt("pk_id"));

                    PGgeometry geomObjAddP = (PGgeometry) rs.getObject("address_position");
                    if (geomObjAddP == null) {
                        addressObj.setAddressPos(null);
                    } else {
                        addressObj.setAddressPos(geomObjAddP);
                    }

                    PGgeometry geomObj = (PGgeometry) rs.getObject("location");
                    if (geomObj == null) {
                        addressObj.setLocation(null);
                    } else {
                        addressObj.setLocation(geomObj);
                    }

                    addressObj.setMajorType(rs.getInt("major_type"));
                    addressObj.setMinorType(rs.getInt("minor_type"));
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

        return addressObj;
    }

    
}
