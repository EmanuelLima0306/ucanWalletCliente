/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionFactory;
import enumerator.TipoUsuario;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.TipoOperacaoModel;
import model.TipoOperacaoModel;

/**
 *
 * @author ismaeldev
 */
public class TipoOperacaoDao {

    final Connection connection;

    public TipoOperacaoDao() throws Exception {
        connection = new IConnectionFactory().getConnection();
    }

    public TipoOperacaoDao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public boolean insert(TipoOperacaoModel tipoOperacaoModel) throws Exception {
        var query = "INSERT INTO tipooperacao(designacao) values(?)";

        var ps = connection.prepareStatement(query);
        ps.setString(1, tipoOperacaoModel.getDesignacao());

        return ps.executeUpdate() > 0;
    }

    public boolean update(TipoOperacaoModel tipoOperacaoModel) throws Exception {
        var query = "UPDATE tipooperacao SET designacao=? WHERE pktipooperacao = ?";

        var ps = connection.prepareStatement(query);
        ps.setString(1, tipoOperacaoModel.getDesignacao());
        ps.setInt(2, tipoOperacaoModel.getPkTipoOperacao().intValue());
        

        return ps.executeUpdate() > 0;
    }

    public TipoOperacaoModel findById(int id) throws Exception {
        var query = "SELECT * FROM tipooperacao where pktipooperacao = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new TipoOperacaoModel(rs.getBigDecimal("pktipooperacao").toBigInteger(), rs.getString("designacao"));
        }

        return null;
    }


    public TipoOperacaoModel findLast() throws Exception {
        var query = "SELECT * FROM tipooperacao ORDER BY pktipooperacao desc LIMIT 1";

        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new TipoOperacaoModel(rs.getBigDecimal("pktipooperacao").toBigInteger(), rs.getString("designacao"));
        }

        return null;
    }
    
    

    public List<TipoOperacaoModel> getAll() throws Exception {
        var query = "SELECT * FROM tipooperacao";
        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();
        List<TipoOperacaoModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new TipoOperacaoModel(rs.getBigDecimal("pktipooperacao").toBigInteger(), rs.getString("designacao")));
        }
        return lista;
    }
}
