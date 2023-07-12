/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enumerator.Estado;
import enumerator.TipoConta;
import incriptacao.RSAKeyUtils;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author emanuellima
 */
public class ContaModel {

    private BigInteger pkConta;
    private ClienteModel clienteModel;
    private double saldoContablistico;
    private double saldoDisponivel;
    private byte[] chavePrivada;
    private byte[] chavePublica;
    private Estado estado;
    private TipoConta tipoConta;
    private BigInteger numeroConta;

    public ContaModel() {
    }

    public ContaModel(BigInteger pkConta, ClienteModel clienteModel, double saldoContablistico, double saldoDisponivel, byte[] chavePrivada, byte[] chavePublica, Estado estado, TipoConta tipoConta,BigInteger numeroConta) {
        this.pkConta = pkConta;
        this.clienteModel = clienteModel;
        this.saldoContablistico = saldoContablistico;
        this.saldoDisponivel = saldoDisponivel;
        this.chavePrivada = chavePrivada;
        this.chavePublica = chavePublica;
        this.estado = estado;
        this.tipoConta = tipoConta;
        this.numeroConta = numeroConta;
    }

    public ContaModel(ClienteModel clienteModel, double saldoContablistico, double saldoDisponivel, byte[] chavePrivada, byte[] chavePublica, Estado estado, TipoConta tipoConta,BigInteger numeroConta) {
        this.clienteModel = clienteModel;
        this.saldoContablistico = saldoContablistico;
        this.saldoDisponivel = saldoDisponivel;
        this.chavePrivada = chavePrivada;
        this.chavePublica = chavePublica;
        this.estado = estado;
        this.tipoConta = tipoConta;
        this.numeroConta = numeroConta;
    }

    public BigInteger getPkConta() {
        return pkConta;
    }

    public void setPkConta(BigInteger pkConta) {
        this.pkConta = pkConta;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public double getSaldoContablistico() {
        return saldoContablistico;
    }

    public void setSaldoContablistico(double saldoContablistico) {
        this.saldoContablistico = saldoContablistico;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public byte[] getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(byte[] chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public byte[] getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(byte[] chavePublica) {
        this.chavePublica = chavePublica;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public BigInteger getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(BigInteger numeroConta) {
        this.numeroConta = numeroConta;
    }
    

    @Override
    public String toString() {
        return RSAKeyUtils.publicKeyToBase64(this.chavePublica);
    }

    public boolean isEmpty() {
        return clienteModel.isEmpty() || saldoDisponivel < 0 || saldoContablistico < 0;
    }

}
