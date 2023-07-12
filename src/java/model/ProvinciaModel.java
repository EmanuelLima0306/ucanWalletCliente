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
public class ProvinciaModel {
    private BigInteger pkProvincia;
    private String designacao;

    public ProvinciaModel() {
    }

    public ProvinciaModel(BigInteger pkProvincia, String designacao) {
        this.pkProvincia = pkProvincia;
        this.designacao = designacao;
    }

    public ProvinciaModel(String designacao) {
        this.designacao = designacao;
    }

    public BigInteger getPkProvincia() {
        return pkProvincia;
    }

    public void setPkProvincia(BigInteger pkProvincia) {
        this.pkProvincia = pkProvincia;
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
