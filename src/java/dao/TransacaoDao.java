/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionFactory;
import enumerator.Estado;
import incriptacao.RSAEncryption;
import incriptacao.RSAKeyUtils;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import model.ContaModel;
import model.TransacaoModel;
import org.postgresql.jdbc.TimestampUtils;

/**
 *
 * @author ismaeldev
 */
public class TransacaoDao {

    final Connection connection;

    public TransacaoDao() throws Exception {
        connection = new IConnectionFactory().getConnection();
    }

    public TransacaoDao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public boolean insert(TransacaoModel transacaoModel) throws Exception {
        var query = "INSERT INTO transacoes(valor,fkcontaorigem,fkcontadestino, datatransacao, datavalidacao,estado) values(?,?,?,?,?,?)";

        //Converte a chave publica de byte para publickey
        PublicKey publicKey = RSAKeyUtils.publicKeyFromBytes(transacaoModel.getContaDestino().getChavePublica());

        var ps = connection.prepareStatement(query);
        ps.setBytes(1, RSAEncryption.encrypt(transacaoModel.getValor(), publicKey));
        ps.setInt(2, transacaoModel.getContaOrigem().getPkConta().intValue());
        ps.setInt(3, transacaoModel.getContaDestino().getPkConta().intValue());
        ps.setBytes(4, RSAEncryption.encrypt(transacaoModel.getDataTransacao(), publicKey));
        ps.setBytes(5, RSAEncryption.encrypt(transacaoModel.getDataValidacao(), publicKey));
        ps.setBytes(6, RSAEncryption.encrypt(transacaoModel.getEstado().name(), publicKey));

        return ps.executeUpdate() > 0;
    }

    public boolean update(TransacaoModel transacaoModel) throws Exception {
        var query = "UPDATE transacoes SET valor=?,fkcontaorigem=?,fkcontadestino=?, datatransacao=?, datavalidacao=?,estado=? WHERE pktransacao = ?";

        //Converte a chave publica de byte para publickey
        PublicKey publicKey = RSAKeyUtils.publicKeyFromBytes(transacaoModel.getContaDestino().getChavePublica());

        var ps = connection.prepareStatement(query);
        ps.setBytes(1, RSAEncryption.encrypt(transacaoModel.getValor(), publicKey));
        ps.setInt(2, transacaoModel.getContaOrigem().getPkConta().intValue());
        ps.setInt(3, transacaoModel.getContaDestino().getPkConta().intValue());
        ps.setBytes(4, RSAEncryption.encrypt(transacaoModel.getDataTransacao(), publicKey));
        ps.setBytes(5, RSAEncryption.encrypt(transacaoModel.getDataValidacao(), publicKey));
        ps.setBytes(6, RSAEncryption.encrypt(transacaoModel.getEstado().name(), publicKey));
        ps.setInt(7, transacaoModel.getPkTransacao().intValue());
        return ps.executeUpdate() > 0;
    }

    public TransacaoModel findById(int id) throws Exception {
        var query = "SELECT * FROM transacoes where pktransacao = ?";

        var ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        if (rs.next()) {
            TransacaoModel model = new TransacaoModel();
            model.setPkTransacao(rs.getBigDecimal("pktransacao").toBigInteger());
            model.setContaOrigem(new ContaDao().findById(rs.getBigDecimal("fkcontaorigem").intValue()));
            model.setContaDestino(new ContaDao().findById(rs.getBigDecimal("fkcontadestino").intValue()));

            //Converte a chave publica de byte para publickey
            PrivateKey privateKey = RSAKeyUtils.privateKeyFromBytes(model.getContaDestino().getChavePrivada());
            
            model.setValor((double) RSAEncryption.decrypt(rs.getBytes("valor"), privateKey));
            model.setDataTransacao((Timestamp) RSAEncryption.decrypt(rs.getBytes("datatransacao"), privateKey));
            model.setDataValidacao((Timestamp) RSAEncryption.decrypt(rs.getBytes("datavalidacao"), privateKey));
            
            model.setEstado(Estado.valueOf((String) RSAEncryption.decrypt(rs.getBytes("estado"), privateKey)));
            return model;
        }

        return null;
    }

    public TransacaoModel findLast() throws Exception {
        var query = "SELECT * FROM transacoes ORDER BY pktransacao desc LIMIT 1";

        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();

        if (rs.next()) {
            TransacaoModel model = new TransacaoModel();
            model.setPkTransacao(rs.getBigDecimal("pktransacao").toBigInteger());
            model.setContaOrigem(new ContaDao().findById(rs.getBigDecimal("fkcontaorigem").intValue()));
            model.setContaDestino(new ContaDao().findById(rs.getBigDecimal("fkcontadestino").intValue()));

            //Converte a chave publica de byte para publickey
            PrivateKey privateKey = RSAKeyUtils.privateKeyFromBytes(model.getContaDestino().getChavePrivada());
            
            model.setValor((double) RSAEncryption.decrypt(rs.getBytes("valor"), privateKey));
            model.setDataTransacao((Timestamp) RSAEncryption.decrypt(rs.getBytes("datatransacao"), privateKey));
            model.setDataValidacao((Timestamp) RSAEncryption.decrypt(rs.getBytes("datavalidacao"), privateKey));
            
            model.setEstado(Estado.valueOf((String) RSAEncryption.decrypt(rs.getBytes("estado"), privateKey)));
            return model;
        }

        return null;
    }

    public List<TransacaoModel> getAll() throws Exception {
        var query = "SELECT * FROM transacoes";
        var ps = connection.prepareStatement(query);
        var rs = ps.executeQuery();
        List<TransacaoModel> lista = new ArrayList<>();
        while (rs.next()) {
            TransacaoModel model = new TransacaoModel();
            model.setPkTransacao(rs.getBigDecimal("pktransacao").toBigInteger());
            model.setContaOrigem(new ContaDao().findById(rs.getBigDecimal("fkcontaorigem").intValue()));
            model.setContaDestino(new ContaDao().findById(rs.getBigDecimal("fkcontadestino").intValue()));

            //Converte a chave publica de byte para publickey
            PrivateKey privateKey = RSAKeyUtils.privateKeyFromBytes(model.getContaDestino().getChavePrivada());
            
            model.setValor((double) RSAEncryption.decrypt(rs.getBytes("valor"), privateKey));
            model.setDataTransacao((Timestamp) RSAEncryption.decrypt(rs.getBytes("datatransacao"), privateKey));
            model.setDataValidacao((Timestamp) RSAEncryption.decrypt(rs.getBytes("datavalidacao"), privateKey));
            
            model.setEstado(Estado.valueOf((String) RSAEncryption.decrypt(rs.getBytes("estado"), privateKey)));
            
            lista.add(model);
        }
        return lista;
    }
    public List<TransacaoModel> findByConta(ContaModel contaModel) throws Exception {
        var query = "SELECT * FROM transacoes WHERE fkcontaorigem = ? OR fkcontadestino = ?";
        var ps = connection.prepareStatement(query);
        ps.setInt(1, contaModel.getPkConta().intValue());
        ps.setInt(2, contaModel.getPkConta().intValue());
        var rs = ps.executeQuery();
        List<TransacaoModel> lista = new ArrayList<>();
        while (rs.next()) {
            TransacaoModel model = new TransacaoModel();
            model.setPkTransacao(rs.getBigDecimal("pktransacao").toBigInteger());
            model.setContaOrigem(new ContaDao().findById(rs.getBigDecimal("fkcontaorigem").intValue()));
            model.setContaDestino(new ContaDao().findById(rs.getBigDecimal("fkcontadestino").intValue()));

            //Converte a chave publica de byte para publickey
            PrivateKey privateKey = RSAKeyUtils.privateKeyFromBytes(model.getContaDestino().getChavePrivada());
            
            model.setValor((double) RSAEncryption.decrypt(rs.getBytes("valor"), privateKey));
            model.setDataTransacao((Timestamp) RSAEncryption.decrypt(rs.getBytes("datatransacao"), privateKey));
            model.setDataValidacao((Timestamp) RSAEncryption.decrypt(rs.getBytes("datavalidacao"), privateKey));
            
            model.setEstado(Estado.valueOf((String) RSAEncryption.decrypt(rs.getBytes("estado"), privateKey)));
            
            lista.add(model);
        }
        return lista;
    }

}
