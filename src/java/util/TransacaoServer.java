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
    private byte[] numeroContaDestino;
    private byte[] valor;
    private byte[] tipoMensagem;
    private byte[] dataTransacao;

    public TransacaoServer() {
    }

    public TransacaoServer(BigInteger pkContaOrigem, byte[] numeroContaDestino, byte[] valor, byte[] tipoMensagem, byte[] dataTransacao) {
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

    public byte[] getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(byte[] numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public byte[] getValor() {
        return valor;
    }

    public void setValor(byte[] valor) {
        this.valor = valor;
    }

    public byte[] getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(byte[] tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public byte[] getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(byte[] dataTransacao) {
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
