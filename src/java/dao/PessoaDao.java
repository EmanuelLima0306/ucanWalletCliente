/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionFactory;
import enumerator.EstadoCivil;
import enumerator.Sexo;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.PessoaModel;

/**
 *
 * @author ismaeldev
 */
public class PessoaDao {

    final Connection connection;

    public PessoaDao() throws Exception {
        connection = new IConnectionFactory().getConnection();
    }

    public PessoaDao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public boolean insert(PessoaModel pessoaModel) throws Exception {

        var query = "INSERT INTO pessoas(nomecompleto,nomepai,nomemae,fkmunicipio,numerodocumento,dataemissao,datavalidade,datanascimento,sexo,estadocivil,altura,telefone,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        var ps = connection.prepareStatement(query);
        ps.setString(1, pessoaModel.getNomeCompleto());
        ps.setString(2, pessoaModel.getNomePai());
        ps.setString(3, pessoaModel.getNomeMae());
        ps.setInt(4, pessoaModel.getMunicipioModel().getPkMunicipio().intValue());
        ps.setString(5, pessoaModel.getNumeroDocumento());
        ps.setDate(6, new Date(pessoaModel.getDataEmissao().getTime()));
        ps.setDate(7, new Date( pessoaModel.getDataValidade().getTime()));
        ps.setDate(8, new Date(pessoaModel.getDataNascimento().getTime()) );
        ps.setString(9, pessoaModel.getSexo().name());
        ps.setString(10, pessoaModel.getEstadoCivil().name());
        ps.setFloat(11, pessoaModel.getAltura());
        ps.setString(12, pessoaModel.getTelefone());
        ps.setString(13, pessoaModel.getEmail());

        return ps.executeUpdate() > 0;
    }

    public boolean update(PessoaModel pessoaModel) throws Exception {
        var query = "UPDATE pessoas SET nomecompleto=?,nomepai=?,nomemae=?,fkmunicipio=?,numerodocumento=?,dataemissao=?,datavalidade=?,datanascimento=?,sexo=?,estadocivil=?,altura=?,telefone=?,email=? WHERE pkpessoa = ?";

        var ps = connection.prepareStatement(query);
        ps.setString(1, pessoaModel.getNomeCompleto());
        ps.setString(2, pessoaModel.getNomePai());
        ps.setString(3, pessoaModel.getNomeMae());
        ps.setInt(4, pessoaModel.getMunicipioModel().getPkMunicipio().intValue());
        ps.setString(5, pessoaModel.getNumeroDocumento());
        ps.setDate(6, new Date(pessoaModel.getDataEmissao().getTime()));
        ps.setDate(7, new Date( pessoaModel.getDataValidade().getTime()));
        ps.setDate(8, new Date(pessoaModel.getDataNascimento().getTime()) );
        ps.setString(9, pessoaModel.getSexo().name());
        ps.setString(10, pessoaModel.getEstadoCivil().name());
        ps.setFloat(11, pessoaModel.getAltura());
        ps.setString(12, pessoaModel.getTelefone());
        ps.setString(13, pessoaModel.getEmail());
        ps.setInt(14, pessoaModel.getPkPessoa().intValue());

        return ps.executeUpdate() > 0;
    }

    public PessoaModel findById(int id) throws Exception {
        var query = "SELECT * FROM pessoas where pkpessoa = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new PessoaModel(rs.getBigDecimal("pkpessoa").toBigInteger(), rs.getString("nomecompleto"), rs.getString("nomepai"), rs.getString("nomemae"), new MunicipioDao().findById(rs.getBigDecimal("fkmunicipio").intValue()), rs.getString("numerodocumento"), rs.getDate("dataemissao"), rs.getDate("datavalidade"), rs.getDate("datanascimento"), Sexo.valueOf(rs.getString("sexo")), EstadoCivil.valueOf(rs.getString("estadocivil")), rs.getFloat("altura"), rs.getString("telefone"), rs.getString("email"));
        }

        return null;
    }

    public PessoaModel findByEmailOrTelefone(String email, String telefone) throws Exception {
        var query = "SELECT * FROM pessoas WHERE email =? OR telefone = ?";

        var ps = connection.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, telefone);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new PessoaModel(rs.getBigDecimal("pkpessoa").toBigInteger(), rs.getString("nomecompleto"), rs.getString("nomepai"), rs.getString("nomemae"), new MunicipioDao().findById(rs.getBigDecimal("fkmunicipio").intValue()), rs.getString("numerodocumento"), rs.getDate("dataemissao"), rs.getDate("datavalidade"), rs.getDate("datanascimento"), Sexo.valueOf(rs.getString("sexo")), EstadoCivil.valueOf(rs.getString("estadocivil")), rs.getFloat("altura"), rs.getString("telefone"), rs.getString("email"));
        }

        return null;
    }

    public PessoaModel findLast() throws Exception {
        var query = "SELECT * FROM pessoas ORDER BY pkpessoa desc LIMIT 1";

        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new PessoaModel(rs.getBigDecimal("pkpessoa").toBigInteger(), rs.getString("nomecompleto"), rs.getString("nomepai"), rs.getString("nomemae"), new MunicipioDao().findById(rs.getBigDecimal("fkmunicipio").intValue()), rs.getString("numerodocumento"), rs.getDate("dataemissao"), rs.getDate("datavalidade"), rs.getDate("datanascimento"), Sexo.valueOf(rs.getString("sexo")), EstadoCivil.valueOf(rs.getString("estadocivil")), rs.getFloat("altura"), rs.getString("telefone"), rs.getString("email"));
        }

        return null;
    }

    public List<PessoaModel> getAll() throws Exception {
        var query = "SELECT * FROM pessoas";
        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();
        List<PessoaModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new PessoaModel(rs.getBigDecimal("pkpessoa").toBigInteger(), rs.getString("nomecompleto"), rs.getString("nomepai"), rs.getString("nomemae"), new MunicipioDao().findById(rs.getBigDecimal("fkmunicipio").intValue()), rs.getString("numerodocumento"), rs.getDate("dataemissao"), rs.getDate("datavalidade"), rs.getDate("datanascimento"), Sexo.valueOf(rs.getString("sexo")), EstadoCivil.valueOf(rs.getString("estadocivil")), rs.getFloat("altura"), rs.getString("telefone"), rs.getString("email")));
        }
        return lista;
    }
}
