/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionFactory;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.ProvinciaModel;

/**
 *
 * @author ismaeldev
 */
public class ProvinciaDao {

    final Connection connection;

    public ProvinciaDao() throws Exception {
        connection = new IConnectionFactory().getConnection();
    }

    public ProvinciaDao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public boolean insert(ProvinciaModel provinciaModel) throws Exception {
        var query = "INSERT INTO provincias(designacao) values(?)";

        var ps = connection.prepareStatement(query);
        ps.setString(1, provinciaModel.getDesignacao());

        return ps.executeUpdate() > 0;
    }

    public boolean update(ProvinciaModel porProvinciaModel) throws Exception {
        var query = "UPDATE provincias SET designacao=? WHERE pkprovincia = ?";

        var ps = connection.prepareStatement(query);
        ps.setString(1, porProvinciaModel.getDesignacao());
        ps.setInt(2, porProvinciaModel.getPkProvincia().intValue());
        

        return ps.executeUpdate() > 0;
    }

    public ProvinciaModel findById(int id) throws Exception {
        var query = "SELECT * FROM provincias where pkprovincia = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new ProvinciaModel(rs.getBigDecimal("pkprovincia").toBigInteger(), rs.getString("designacao"));
        }

        return null;
    }


    public ProvinciaModel findLast() throws Exception {
        var query = "SELECT * FROM provicias ORDER BY pkprovincia desc LIMIT 1";

        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();

        if (rs.next()) {
                return new ProvinciaModel(rs.getBigDecimal("pkprovincia").toBigInteger(), rs.getString("designacao"));
        }

        return null;
    }
    
    

    public List<ProvinciaModel> getAll() throws Exception {
        var query = "SELECT * FROM provincias";
        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();
        List<ProvinciaModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new ProvinciaModel(rs.getBigDecimal("pkProvincia").toBigInteger(), rs.getString("designacao")));
        }
        return lista;
    }
}
