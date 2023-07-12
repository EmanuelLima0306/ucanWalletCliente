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
public class MunicipioModel {

    private BigInteger pkMunicipio;
    private ProvinciaModel provinciaModel;
    private String designacao;

    public MunicipioModel(BigInteger pkMunicipio, ProvinciaModel provinciaModel, String designacao) {
        this.pkMunicipio = pkMunicipio;
        this.provinciaModel = provinciaModel;
        this.designacao = designacao;
    }

    public MunicipioModel() {
    }

    public MunicipioModel(ProvinciaModel provinciaModel, String designacao) {
        this.provinciaModel = provinciaModel;
        this.designacao = designacao;
    }

    public BigInteger getPkMunicipio() {
        return pkMunicipio;
    }

    public void setPkMunicipio(BigInteger pkMunicipio) {
        this.pkMunicipio = pkMunicipio;
    }

    public ProvinciaModel getProvinciaModel() {
        return provinciaModel;
    }

    public void setProvinciaModel(ProvinciaModel provinciaModel) {
        this.provinciaModel = provinciaModel;
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

    public boolean isEmpty() {
        return designacao.isEmpty();
    }
}
