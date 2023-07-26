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
import model.PessoaModel;
import model.UsuarioModel;

/**
 *
 * @author ismaeldev
 */
public class UsuarioDao {

    final Connection connection;

    public UsuarioDao() throws Exception {
        connection = new IConnectionFactory().getConnection();
    }

    public UsuarioDao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public boolean insert(UsuarioModel usuarioModel) throws Exception {
        var query = "INSERT INTO usuarios(fkpessoa,tipousuario,senha) values(?,?,MD5(?))";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, usuarioModel.getPessoaModel().getPkPessoa().intValue());
        ps.setString(2, usuarioModel.getTipoUsuario().name());
        ps.setString(3, usuarioModel.getSenha());

        return ps.executeUpdate() > 0;
    }

    public boolean update(UsuarioModel usuarioModel) throws Exception {
        var query = "UPDATE usuarios SET fkpessoa=?, tipousuario=?,senha= MD5(?) WHERE pkusuario = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, usuarioModel.getPessoaModel().getPkPessoa().intValue());
        ps.setString(2, usuarioModel.getTipoUsuario().toString());
        ps.setString(3, usuarioModel.getSenha());
        ps.setInt(4, usuarioModel.getPkUsuario().intValue());

        return ps.executeUpdate() > 0;
    }

    public UsuarioModel findById(int id) throws Exception {
        var query = "SELECT * FROM usuarios where pkusuario = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new UsuarioModel(rs.getBigDecimal("pkusuario").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()), TipoUsuario.valueOf(rs.getString("tipousuario")), rs.getString("senha"));
        }

        return null;
    }
    
    public UsuarioModel autenticacao(UsuarioModel usuarioModel) throws Exception {
        var query = "SELECT * FROM usuarios usuarios INNER JOIN pessoas pessoas ON usuarios.fkpessoa=pessoas.pkpessoa WHERE pessoas.email = ? AND usuarios.senha = MD5(?)";

        var ps = connection.prepareStatement(query);
        ps.setString(1, usuarioModel.getPessoaModel().getEmail());
        ps.setString(2, usuarioModel.getSenha());
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new UsuarioModel(rs.getBigDecimal("pkusuario").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()), TipoUsuario.valueOf(rs.getString("tipousuario")), rs.getString("senha"));
        }

        return null;
    }

    public UsuarioModel findLast() throws Exception {
        var query = "SELECT * FROM usuarios ORDER BY pkusuario desc LIMIT 1";

        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new UsuarioModel(rs.getBigDecimal("pkusuario").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()), TipoUsuario.valueOf(rs.getString("tipousuario")), rs.getString("senha"));
        }

        return null;
    }
    
    

    public List<UsuarioModel> getAll() throws Exception {
        var query = "SELECT * FROM usuarios";
        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();
        List<UsuarioModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new UsuarioModel(rs.getBigDecimal("pkusuario").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()), TipoUsuario.valueOf(rs.getString("tipousuario")), rs.getString("senha")));
        }
        return lista;
    }
}
