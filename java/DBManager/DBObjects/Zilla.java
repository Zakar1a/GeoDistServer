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
public class Zilla {

    /**
     * gid serial NOT NULL,
  first_dist character varying(20),
  geocode numeric,
  ave_area numeric,
  fre numeric,
  per numeric,
  sec numeric,
  top smallint,
  the_geom bgschema.geometry,
     */
    private int gID;
    private String firstDist;
    private double geoCode;
    private double aveArea;
    private double fre;
    private double per;
    private double sec;
    private int top;
    private PGgeometry theGeom;

    public double getAveArea() {
        return aveArea;
    }

    public void setAveArea(double aveArea) {
        this.aveArea = aveArea;
    }

    public String getFirstDist() {
        return firstDist;
    }

    public void setFirstDist(String firstDist) {
        this.firstDist = firstDist;
    }

    public double getFre() {
        return fre;
    }

    public void setFre(double fre) {
        this.fre = fre;
    }

    public int getgID() {
        return gID;
    }

    public void setgID(int gID) {
        this.gID = gID;
    }

    public double getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(double geoCode) {
        this.geoCode = geoCode;
    }

    public double getPer() {
        return per;
    }

    public void setPer(double per) {
        this.per = per;
    }

    public double getSec() {
        return sec;
    }

    public void setSec(double sec) {
        this.sec = sec;
    }

    public PGgeometry getTheGeom() {
        return theGeom;
    }

    public void setTheGeom(PGgeometry theGeom) {
        this.theGeom = theGeom;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public Zilla getContainingZilla(PGgeometry geomObj){

        Zilla zilla = new Zilla();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM bgschema.t_zilla WHERE  ST_Contains(the_geom, ? )";

                stmt = conn.prepareStatement(query);
                stmt.setObject(1, geomObj);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    zilla.setgID(rs.getInt("gid"));
                    zilla.setFirstDist(rs.getString("first_dist"));
                    zilla.setGeoCode(rs.getDouble("geocode"));
                    zilla.setAveArea(rs.getDouble("ave_area"));
                    zilla.setFre(rs.getDouble("fre"));
                    zilla.setPer(rs.getDouble("per"));
                    zilla.setSec(rs.getDouble("sec"));
                    zilla.setTop(rs.getInt("top"));

                    PGgeometry geomObjAddP = (PGgeometry) rs.getObject("the_geom");
                    zilla.setTheGeom(geomObj);
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

        return zilla;
    }
}
