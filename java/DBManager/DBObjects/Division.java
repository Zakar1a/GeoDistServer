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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgis.PGgeometry;

/**
 *
 * @author Zakaria
 */
public class Division {
  
    private int gID;
    private int recID;
    private int divCode;
    private double sumArea;
    private String divName;
    private double arSqkm;
    private PGgeometry theGeom;

    //getters and setters
    
    public double getArSqkm() {
        return arSqkm;
    }

    public void setArSqkm(double arSqkm) {
        this.arSqkm = arSqkm;
    }

    public int getDivCode() {
        return divCode;
    }

    public void setDivCode(int divCode) {
        this.divCode = divCode;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public int getgID() {
        return gID;
    }

    public void setgID(int gID) {
        this.gID = gID;
    }

    public int getRecID() {
        return recID;
    }

    public void setRecID(int recID) {
        this.recID = recID;
    }

    public double getSumArea() {
        return sumArea;
    }

    public void setSumArea(double sumArea) {
        this.sumArea = sumArea;
    }

    public PGgeometry getTheGeom() {
        return theGeom;
    }

    public void setTheGeom(PGgeometry theGeom) {
        this.theGeom = theGeom;
    }

    public Division getContainingDivisoin(PGgeometry geomObj){

        Division division = new Division();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM bgschema.t_division WHERE  ST_Contains(the_geom, ? )";

                stmt = conn.prepareStatement(query);
                stmt.setObject(1, geomObj);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    division.setgID(rs.getInt("gid"));
                    division.setRecID(rs.getInt("recid"));
                    division.setDivCode(rs.getInt("divcode"));
                    division.setSumArea(rs.getDouble("sum_area"));
                    division.setDivName(rs.getString("divname"));
                    division.setArSqkm(rs.getDouble("ar_sqkm"));

                    PGgeometry geomObjAddP = (PGgeometry) rs.getObject("the_geom");
                    division.setTheGeom(geomObj);
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

        return division;
    }
}
