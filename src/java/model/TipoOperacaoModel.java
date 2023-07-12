/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigInteger;

/**
 *
 * @author emanuellima
 */
public class TipoOperacaoModel {

    private BigInteger pkTipoOperacao;
    private String designacao;

    public TipoOperacaoModel() {
    }

    public TipoOperacaoModel(BigInteger pkTipoOperacao, String designacao) {
        this.pkTipoOperacao = pkTipoOperacao;
        this.designacao = designacao;
    }

    public TipoOperacaoModel(String designacao) {
        this.designacao = designacao;
    }

    public BigInteger getPkTipoOperacao() {
        return pkTipoOperacao;
    }

    public void setPkTipoOperacao(BigInteger pkTipoOperacao) {
        this.pkTipoOperacao = pkTipoOperacao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @Override
    public String toString() {
        return this.designacao;
    }
    
    public boolean isEmpty(){
        return designacao.isEmpty();
    }

}
