/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enumerator.Estado;
import java.math.BigInteger;
import java.sql.Timestamp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author emanuellima
 */
public class TransacaoModel {

    private BigInteger pkTransacao;
    private ContaModel contaOrigem;
    private ContaModel contaDestino;
    private TipoOperacaoModel tipoOperacaoModel;
    private Estado estado;
    private Timestamp dataTransacao;
    private Timestamp dataValidacao;
    private double valor;
    

    public TransacaoModel() {
    }

    public TransacaoModel(BigInteger pkTransacao, ContaModel contaOrigem, ContaModel contaDestino, TipoOperacaoModel tipoOperacaoModel, Estado estado, Timestamp dataTransacao, Timestamp dataValidacao, double valor) {
        this.pkTransacao = pkTransacao;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.tipoOperacaoModel = tipoOperacaoModel;
        this.estado = estado;
        this.dataTransacao = dataTransacao;
        this.dataValidacao = dataValidacao;
        this.valor = valor;
    }

    public TransacaoModel(ContaModel contaOrigem, ContaModel contaDestino, TipoOperacaoModel tipoOperacaoModel, Estado estado, Timestamp dataTransacao, Timestamp dataValidacao, double valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.tipoOperacaoModel = tipoOperacaoModel;
        this.estado = estado;
        this.dataTransacao = dataTransacao;
        this.dataValidacao = dataValidacao;
        this.valor = valor;
    }

    public BigInteger getPkTransacao() {
        return pkTransacao;
    }

    public void setPkTransacao(BigInteger pkTransacao) {
        this.pkTransacao = pkTransacao;
    }

    public ContaModel getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(ContaModel contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public ContaModel getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(ContaModel contaDestino) {
        this.contaDestino = contaDestino;
    }

    public TipoOperacaoModel getTipoOperacaoModel() {
        return tipoOperacaoModel;
    }

    public void setTipoOperacaoModel(TipoOperacaoModel tipoOperacaoModel) {
        this.tipoOperacaoModel = tipoOperacaoModel;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Timestamp getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Timestamp dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Timestamp getDataValidacao() {
        return dataValidacao;
    }

    public void setDataValidacao(Timestamp dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public String toJson() {
        try {
            //Cria a instacia de Gson e defini o formato da data
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); 
            return gson.toJson(this);
        } catch (Exception e) {
        }
        return null;
    }

    public boolean isEmpty() {
        return contaOrigem == null || contaDestino == null || tipoOperacaoModel == null;
    }

}
