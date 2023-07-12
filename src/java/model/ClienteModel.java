/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enumerator.TipoCliente;
import java.math.BigInteger;

/**
 *
 * @author emanuellima
 */
public class ClienteModel {
    private BigInteger pkCliente;
    private PessoaModel pessoaModel;
    private TipoCliente tipoCliente;

    public ClienteModel() {
    }

    public ClienteModel(BigInteger pkCliente, PessoaModel pessoaModel, TipoCliente tipoCliente) {
        this.pkCliente = pkCliente;
        this.pessoaModel = pessoaModel;
        this.tipoCliente = tipoCliente;
    }

    public ClienteModel(PessoaModel pessoaModel, TipoCliente tipoCliente) {
        this.pessoaModel = pessoaModel;
        this.tipoCliente = tipoCliente;
    }

    public BigInteger getPkCliente() {
        return pkCliente;
    }

    public void setPkCliente(BigInteger pkCliente) {
        this.pkCliente = pkCliente;
    }

    public PessoaModel getPessoaModel() {
        return pessoaModel;
    }

    public void setPessoaModel(PessoaModel pessoaModel) {
        this.pessoaModel = pessoaModel;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    

    @Override
    public String toString() {
        return pessoaModel.toString();
    }
    
    public boolean isEmpty(){
        return pessoaModel.isEmpty() ||  tipoCliente == null;
    }
}
