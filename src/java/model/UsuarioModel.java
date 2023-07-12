/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enumerator.TipoUsuario;
import java.math.BigInteger;

/**
 *
 * @author emanuellima
 */
public class UsuarioModel {

    private BigInteger pkUsuario;
    private PessoaModel pessoaModel;
    private TipoUsuario tipoUsuario;
    private String senha;

    public UsuarioModel() {
    }

    public UsuarioModel(BigInteger pkUsuario, PessoaModel pessoaModel, TipoUsuario tipoUsuario, String senha) {
        this.pkUsuario = pkUsuario;
        this.pessoaModel = pessoaModel;
        this.tipoUsuario = tipoUsuario;
        this.senha = senha;
    }

    public UsuarioModel(PessoaModel pessoaModel, TipoUsuario tipoUsuario, String senha) {
        this.pessoaModel = pessoaModel;
        this.tipoUsuario = tipoUsuario;
        this.senha = senha;
    }

    public BigInteger getPkUsuario() {
        return pkUsuario;
    }

    public void setPkUsuario(BigInteger pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public PessoaModel getPessoaModel() {
        return pessoaModel;
    }

    public void setPessoaModel(PessoaModel pessoaModel) {
        this.pessoaModel = pessoaModel;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return pessoaModel != null ? pessoaModel.toString() : "";
    }

    public boolean isEmpty() {
        return pessoaModel == null || pessoaModel.isEmpty() || senha.isEmpty() || tipoUsuario == null;
    }
}
