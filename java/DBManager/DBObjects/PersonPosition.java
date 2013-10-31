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
import org.postgis.PGgeometry;

/**
 *
 * @author Zakaria
 */
public class PersonPosition {

    private int personPosID;
    private PGgeometry location;
    private int salesmanID;
    private Date dateTime;


    //getters and setters
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public PGgeometry getLocation() {
        return location;
    }

    public void setLocation(PGgeometry location) {
        this.location = location;
    }

    public int getPersonPosID() {
        return personPosID;
    }

    public void setPersonPosID(int personPosID) {
        this.personPosID = personPosID;
    }

    public int getSalesmanID() {
        return salesmanID;
    }

    public void setSalesmanID(int salesmanID) {
        this.salesmanID = salesmanID;
    }

     public String insert(
            PGgeometry location,
            int salesmanID,
            Date dateTime) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "INSERT INTO bgschema.t_person_position( " +
                        " \"location\", salesman_id, date_time) " +
                        "VALUES ( ?, ?, ?)";
                stmt = conn.prepareStatement(query);

                stmt.setObject(1, location);
                stmt.setInt(2, salesmanID);
                stmt.setDate(3, dateTime);
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
            int originalPersonPosID,
            PGgeometry location,
            int salesmanID,
            Date dateTime) {
        String message = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "UPDATE bgschema.t_person_position " +
                        " SET \"location\"=?, salesman_id=?, date_time=? " +
                        "WHERE person_pos_id=? ";
                stmt = conn.prepareStatement(query);

                stmt.setObject(1, location);
                stmt.setInt(2, salesmanID);
                stmt.setDate(3, dateTime);
                stmt.setInt(4, originalPersonPosID);
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

    public PersonPosition getById(int personPosId) {
        PersonPosition personPos = new PersonPosition();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM bgschema.t_person_position where person_pos_id=?";

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, personPosId);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    personPos.setPersonPosID(rs.getInt("person_pos_id"));
                    personPos.setSalesmanID(rs.getInt("salesman_id"));

                    PGgeometry geomObj = (PGgeometry) rs.getObject("location");
                    if (geomObj == null) {
                        personPos.setLocation(null);
                    } else {
                        personPos.setLocation(geomObj);
                    }
                    personPos.setDateTime(rs.getDate("date_time"));
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

        return personPos;
    }

}
