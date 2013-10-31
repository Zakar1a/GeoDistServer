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
public class Upazilla {

    private int gID;
    private double area;
    private double perimeter;
    private String thana;
    private int thanaID;
    private int distbndc_i;//??
    private String divName;
    private String distName;
    private String thanaName;
    private int area__sr_k;
    private int densitySq;
    private int population;
    private PGgeometry theGeom;

    //Getters and setters
    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getArea__sr_k() {
        return area__sr_k;
    }

    public void setArea__sr_k(int area__sr_k) {
        this.area__sr_k = area__sr_k;
    }

    public int getDensitySq() {
        return densitySq;
    }

    public void setDensitySq(int densitySq) {
        this.densitySq = densitySq;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public int getDistbndc_i() {
        return distbndc_i;
    }

    public void setDistbndc_i(int distbndc_i) {
        this.distbndc_i = distbndc_i;
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

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double parimeter) {
        this.perimeter = parimeter;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public int getThanaID() {
        return thanaID;
    }

    public void setThanaID(int thanaID) {
        this.thanaID = thanaID;
    }

    public String getThanaName() {
        return thanaName;
    }

    public void setThanaName(String thanaName) {
        this.thanaName = thanaName;
    }

    public PGgeometry getTheGeom() {
        return theGeom;
    }

    public void setTheGeom(PGgeometry theGeom) {
        this.theGeom = theGeom;
    }

    public Upazilla getContainingUpazilla(PGgeometry geomObj) {
        Upazilla upazilla = new Upazilla();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection();
            try {
                String query = "SELECT * FROM bgschema.t_upazilla WHERE  ST_Contains(the_geom, ? )";

                stmt = conn.prepareStatement(query);
                stmt.setObject(1, geomObj);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    upazilla.setgID(rs.getInt("gid"));
                    upazilla.setArea(rs.getDouble("area"));
                    upazilla.setPerimeter(rs.getDouble("perimeter"));
                    upazilla.setThana(rs.getString("thana_"));
                    upazilla.setThanaID(rs.getInt("thana_id"));
                    upazilla.setDistbndc_i(rs.getInt("distbndc_i"));
                    upazilla.setDivName(rs.getString("div_name"));
                    upazilla.setDistName(rs.getString("dist_name"));
                    upazilla.setThana(rs.getString("thana_name"));
                    upazilla.setArea__sr_k(rs.getInt("area__sr_k"));
                    upazilla.setDensitySq(rs.getInt("density_sq"));
                    upazilla.setPopulation(rs.getInt("population"));
                    PGgeometry geomObjAddP = (PGgeometry) rs.getObject("the_geom");
                    upazilla.setTheGeom(geomObj);
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

        return upazilla;
    }
}
