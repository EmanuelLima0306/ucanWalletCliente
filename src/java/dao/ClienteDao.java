/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionFactory;
import enumerator.TipoCliente;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.ClienteModel;
import model.PessoaModel;

/**
 *
 * @author ismaeldev
 */
public class ClienteDao {

    final Connection connection;

    public ClienteDao() throws Exception {
        connection = new IConnectionFactory().getConnection();
    }

    public ClienteDao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public boolean insert(ClienteModel clienteModel) throws Exception {

        var query = "INSERT INTO clientes(fkpessoa,tipocliente) values(?,?)";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, clienteModel.getPessoaModel().getPkPessoa().intValue());
        ps.setString(2, clienteModel.getTipoCliente().name());
        
        return ps.executeUpdate() > 0;
    }

    public boolean update(ClienteModel clienteModel) throws Exception {
        var query = "UPDATE clientes SET fkpessoa=?,tipocliente=? WHERE pkcliente = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, clienteModel.getPessoaModel().getPkPessoa().intValue());
        ps.setString(2, clienteModel.getTipoCliente().name());
        ps.setInt(3, clienteModel.getPkCliente().intValue());

        return ps.executeUpdate() > 0;
    }

    public ClienteModel findById(int id) throws Exception {
        var query = "SELECT * FROM clientes where pkcliente = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new ClienteModel(rs.getBigDecimal("pkcliente").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()),TipoCliente.valueOf(rs.getString("tipocliente")));
        }

        return null;
    }
    public ClienteModel findByPessoa(PessoaModel pessoaModel) throws Exception {
        var query = "SELECT * FROM clientes clientes INNER JOIN pessoas pessoas ON clientes.fkpessoa = pessoas.pkpessoa where pessoas.pkpessoa = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, pessoaModel.getPkPessoa().intValue());
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new ClienteModel(rs.getBigDecimal("pkcliente").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()),TipoCliente.valueOf(rs.getString("tipocliente")));
        }

        return null;
    }

    public ClienteModel findByEmailOrTelefone(String email, String telefone) throws Exception {
        var query = "SELECT * FROM clientes clientes INNER JOIN pessoas pessoas ON clientes.fkpessoa = pessoas.pkpessoa WHERE pessoas.email =? OR pessoas.telefone = ?";

        var ps = connection.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, telefone);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new ClienteModel(rs.getBigDecimal("pkcliente").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()),TipoCliente.valueOf(rs.getString("tipocliente")));
        }

        return null;
    }

    public ClienteModel findLast() throws Exception {
        var query = "SELECT * FROM clientes ORDER BY pkcliente desc LIMIT 1";

        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new ClienteModel(rs.getBigDecimal("pkcliente").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()),TipoCliente.valueOf(rs.getString("tipocliente")));
        }

        return null;
    }

    public List<ClienteModel> getAll() throws Exception {
        var query = "SELECT * FROM clientes";
        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();
        List<ClienteModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new ClienteModel(rs.getBigDecimal("pkcliente").toBigInteger(), new PessoaDao().findById(rs.getBigDecimal("fkpessoa").intValue()),TipoCliente.valueOf(rs.getString("tipocliente"))));
        }
        return lista;
    }
}
