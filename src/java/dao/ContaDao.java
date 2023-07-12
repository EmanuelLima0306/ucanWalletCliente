/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionFactory;
import enumerator.Estado;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.ContaModel;
import model.PessoaModel;

/**
 *
 * @author ismaeldev
 */
public class ContaDao {

    final Connection connection;

    public ContaDao() throws Exception {
        connection = new IConnectionFactory().getConnection();
    }

    public ContaDao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public boolean insert(ContaModel contaModel) throws Exception {
        var query = "INSERT INTO contas(fkcliente,saldocontabilistico,saldodisponivel,chaveprivada,chavepublica,estado) values(?,?,?,?,?,?)";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, contaModel.getClienteModel().getPkCliente().intValue());
        ps.setDouble(2, contaModel.getSaldoContablistico());
        ps.setDouble(3, contaModel.getSaldoDisponivel());
        ps.setBytes(4, contaModel.getChavePrivada());
        ps.setBytes(5, contaModel.getChavePublica());
        ps.setString(6, contaModel.getEstado().name());

        return ps.executeUpdate() > 0;
    }

    public boolean update(ContaModel contaModel) throws Exception {
        var query = "UPDATE contas SET fkcliente=?,saldocontabilistico=?,saldodisponivel=?,chaveprivada=?,chavepublica=?,estado=? WHERE pkconta = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, contaModel.getClienteModel().getPkCliente().intValue());
        ps.setDouble(2, contaModel.getSaldoContablistico());
        ps.setDouble(3, contaModel.getSaldoDisponivel());
        ps.setBytes(4, contaModel.getChavePrivada());
        ps.setBytes(5, contaModel.getChavePublica());
        ps.setString(6, contaModel.getTipoConta().name());
        ps.setInt(7, contaModel.getPkConta().intValue());
        return ps.executeUpdate() > 0;
    }
    
    public boolean reduzirDisponivel(ContaModel contaModel, double valor) throws Exception {
        var query = "UPDATE contas SET saldodisponivel= (saldodisponivel - ?) WHERE pkconta = ?";

        var ps = connection.prepareStatement(query);
        ps.setDouble(1, valor);
        ps.setInt(2, contaModel.getPkConta().intValue());
        return ps.executeUpdate() > 0;
    }

    public ContaModel findById(int id) throws Exception {
        var query = "SELECT * FROM contas where pkconta = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new ContaModel(rs.getBigDecimal("pkconta").toBigInteger(), new ClienteDao().findById(rs.getBigDecimal("fkcliente").intValue()),rs.getDouble("saldocontabilistico"),rs.getDouble("saldodisponivel"),rs.getBytes("chaveprivada"),rs.getBytes("chavepublica"),Estado.valueOf(rs.getString("estado")),null, rs.getBigDecimal("numeroconta").toBigInteger());
        }

        return null;
    }
    public ContaModel findByNumeroConta(int numeroConta) throws Exception {
        var query = "SELECT * FROM contas where numeroconta = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, numeroConta);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new ContaModel(rs.getBigDecimal("pkconta").toBigInteger(), new ClienteDao().findById(rs.getBigDecimal("fkcliente").intValue()),rs.getDouble("saldocontabilistico"),rs.getDouble("saldodisponivel"),rs.getBytes("chaveprivada"),rs.getBytes("chavepublica"),Estado.valueOf(rs.getString("estado")),null, rs.getBigDecimal("numeroconta").toBigInteger());
        }

        return null;
    }

    public ContaModel findLast() throws Exception {
        var query = "SELECT * FROM contas ORDER BY pkconta desc LIMIT 1";

        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();

        if (rs.next()) {
            return new ContaModel(rs.getBigDecimal("pkconta").toBigInteger(), new ClienteDao().findById(rs.getBigDecimal("fkcliente").intValue()),rs.getDouble("saldocontabilistico"),rs.getDouble("saldodisponivel"),rs.getBytes("chaveprivada"),rs.getBytes("chavepublica"),Estado.valueOf(rs.getString("estado")),null, rs.getBigDecimal("numeroconta").toBigInteger());
        }

        return null;
    }

    public List<ContaModel> getAll() throws Exception {
        var query = "SELECT * FROM contas";
        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();
        List<ContaModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new ContaModel(rs.getBigDecimal("pkconta").toBigInteger(), new ClienteDao().findById(rs.getBigDecimal("fkcliente").intValue()),rs.getDouble("saldocontabilistico"),rs.getDouble("saldodisponivel"),rs.getBytes("chaveprivada"),rs.getBytes("chavepublica"),Estado.valueOf(rs.getString("estado")),null, rs.getBigDecimal("numeroconta").toBigInteger()));
        }
        return lista;
    }
    
    public List<ContaModel> findAllByPessoa(PessoaModel pessoaModel) throws Exception {
        var query = "SELECT * FROM contas contas INNER JOIN clientes clientes ON contas.fkcliente = clientes.pkcliente INNER JOIN pessoas pessoas ON clientes.fkpessoa = pessoas.pkpessoa WHERE pessoas.pkpessoa = ?";
        var ps = connection.prepareStatement(query);
        ps.setInt(1, pessoaModel.getPkPessoa().intValue());
        var rs = ps.executeQuery();
        List<ContaModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new ContaModel(rs.getBigDecimal("pkconta").toBigInteger(), new ClienteDao().findById(rs.getBigDecimal("fkcliente").intValue()),rs.getDouble("saldocontabilistico"),rs.getDouble("saldodisponivel"),rs.getBytes("chaveprivada"),rs.getBytes("chavepublica"),Estado.valueOf(rs.getString("estado")),null, rs.getBigDecimal("numeroconta").toBigInteger()));
        }
        return lista;
    }
}
