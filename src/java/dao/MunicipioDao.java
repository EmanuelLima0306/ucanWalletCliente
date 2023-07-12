/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionFactory;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.MunicipioModel;
import model.ProvinciaModel;

/**
 *
 * @author ismaeldev
 */
public class MunicipioDao {

    final Connection connection;

    public MunicipioDao() throws Exception {
        connection = new IConnectionFactory().getConnection();
    }

    public MunicipioDao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public boolean insert(MunicipioModel municipopModel) throws Exception {
        var query = "INSERT INTO municipios(fkprovincia,designacao) values(?,?)";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, municipopModel.getProvinciaModel().getPkProvincia().intValue());
        ps.setString(2, municipopModel.getDesignacao());

        return ps.executeUpdate() > 0;
    }

    public boolean update(MunicipioModel municipioModel) throws Exception {
        var query = "UPDATE municipios SET fkprovincia=?, designacao=? WHERE pkmunicipio = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, municipioModel.getProvinciaModel().getPkProvincia().intValue());
        ps.setString(2, municipioModel.getDesignacao());
        ps.setInt(3, municipioModel.getPkMunicipio().intValue());
        

        return ps.executeUpdate() > 0;
    }

    public MunicipioModel findById(int id) throws Exception {
        var query = "SELECT * FROM municipios where pkmunicipio = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new MunicipioModel(rs.getBigDecimal("pkmunicipio").toBigInteger(),new ProvinciaDao().findById(rs.getBigDecimal("fkprovincia").intValue()), rs.getString("designacao"));
        }

        return null;
    }
    
     public List<MunicipioModel> findByProvincia(ProvinciaModel provinciaModel) throws Exception {
        var query = "SELECT * FROM municipios where fkprovincia = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, provinciaModel.getPkProvincia().intValue());
        var rs = ps.executeQuery();
        List<MunicipioModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add( new MunicipioModel(rs.getBigDecimal("pkmunicipio").toBigInteger(),new ProvinciaDao().findById(rs.getBigDecimal("fkprovincia").intValue()), rs.getString("designacao")));
        }

        return lista;
    }


    public MunicipioModel findLast() throws Exception {
        var query = "SELECT * FROM municipios ORDER BY pkmunicipio desc LIMIT 1";

        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();

        if (rs.next()) {
                return new MunicipioModel(rs.getBigDecimal("pkmunicipio").toBigInteger(),new ProvinciaDao().findById(rs.getBigDecimal("fkprovincia").intValue()), rs.getString("designacao"));
        }

        return null;
    }
    
    

    public List<MunicipioModel> getAll() throws Exception {
        var query = "SELECT * FROM municipios";
        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();
        List<MunicipioModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new MunicipioModel(rs.getBigDecimal("pkmunicipio").toBigInteger(),new ProvinciaDao().findById(rs.getBigDecimal("fkprovincia").intValue()), rs.getString("designacao")));
        }
        return lista;
    }
}
