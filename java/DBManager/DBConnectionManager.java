/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Zakaria
 */
public class DBConnectionManager {

    private static DBConnectionManager dbcMan;

    private DBConnectionManager() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException cnf)
        {
            System.out.println("POSTgreSQL Driver load failed!!");
        } catch (Exception ex) {
            System.out.println("POSTgreSQL Driver load failed!!");
        }
    }

    public static DBConnectionManager getInstance() throws ClassNotFoundException {
        synchronized (DBConnectionManager.class) {
            if (dbcMan == null) {
                dbcMan = new DBConnectionManager();
            }
        }
        return dbcMan;
    }

    private String SCHEMA_NAME;
    private String DB_USER_NAME;
    private String DB_PASSWORD;
    public Connection getConnection()
    {
        Connection conn = null;
        SCHEMA_NAME = "postgis";
        DB_USER_NAME = "postgres";
        DB_PASSWORD = "root";
        try
        {
            DBConnectionManager.getInstance();
            String connUrl = "jdbc:postgresql://localhost:5432/"+SCHEMA_NAME+"?user="+DB_USER_NAME+"&password="+DB_PASSWORD;
            conn = DriverManager.getConnection(connUrl);
            ((org.postgresql.PGConnection)conn).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
            ((org.postgresql.PGConnection)conn).addDataType("box3d",Class.forName("org.postgis.PGbox3d"));
        }
        catch(Exception ex)
        {
            System.out.println("Could not established connection with POSTGreSQL");
        }
        return conn;
    }
}
