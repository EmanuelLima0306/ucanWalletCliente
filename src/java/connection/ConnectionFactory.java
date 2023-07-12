/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ismaeldev
 */
public interface ConnectionFactory {
    
    public void openConnection() throws SQLException;
    public void closeConnection() throws SQLException;
    public Connection getConnection();
}
