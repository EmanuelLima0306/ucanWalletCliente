/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enumerator.TipoMensagem;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 *
 * @author emanuellima
 */
public class TransacaoServer {
    private BigInteger pkContaOrigem;
    private int numeroContaDestino;
    private double valor;
    private TipoMensagem tipoMensagem;
    private Timestamp dataTransacao;

    public TransacaoServer() {
    }

    public TransacaoServer(BigInteger pkContaOrigem, int numeroContaDestino, double valor, TipoMensagem tipoMensagem, Timestamp dataTransacao) {
        this.pkContaOrigem = pkContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.valor = valor;
        this.tipoMensagem = tipoMensagem;
        this.dataTransacao = dataTransacao;
        
    }

    public BigInteger getPkContaOrigem() {
        return pkContaOrigem;
    }

    public void setPkContaOrigem(BigInteger pkContaOrigem) {
        this.pkContaOrigem = pkContaOrigem;
    }

    public int getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(int numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagem tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public Timestamp getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Timestamp dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
    
    

    
    
    public String toJson(){
        try {
            //Cria a instacia de Gson e defini o formato da data
            Gson gson = new GsonBuilder().create(); 
            return gson.toJson(this);
        } catch (Exception e) {
        }
        return null;
    }
}
