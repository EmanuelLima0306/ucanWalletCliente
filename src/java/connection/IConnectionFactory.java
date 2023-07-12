/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismaeldev
 */
public class IConnectionFactory {

    static private Connection connection;

    //Escritorio
//    private final String USER = "postgres";
//    private final String PASSWORD = "postgress";
    //Pessoal
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    public final static String PORT = "5432";
    private final String DATABASE = "ucanWallet";
    private final String URL = "jdbc:postgresql://localhost:" + PORT + "/" + DATABASE + "?autoReconnect=true";

    public IConnectionFactory() throws Exception {
        forName("org.postgresql.Driver");
        open();
    }

    public void open() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws SQLException {

//        if (connection == null) {
//            open();
//        }
        return connection;
    }

}
