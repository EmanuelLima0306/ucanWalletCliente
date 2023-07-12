/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enumerator.EstadoCivil;
import enumerator.Sexo;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author emanuellima
 */
public class PessoaModel {

    private BigInteger pkPessoa;
    private String nomeCompleto;
    private String nomePai;
    private String nomeMae;
    private MunicipioModel municipioModel;
    private String numeroDocumento;
    private Date dataEmissao;
    private Date dataValidade;
    private Date dataNascimento;
    private Sexo sexo;
    private EstadoCivil estadoCivil;
    private float altura;
    private String telefone;
    private String email;

    public PessoaModel() {
    }

    public PessoaModel(BigInteger pkPessoa, String nomeCompleto, String nomePai, String nomeMae, MunicipioModel municipioModel, String numeroDocumento, Date dataEmissao, Date dataValidade, Date dataNascimento, Sexo sexo, EstadoCivil estadoCivil, float altura, String telefone, String email) {
        this.pkPessoa = pkPessoa;
        this.nomeCompleto = nomeCompleto;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.municipioModel = municipioModel;
        this.numeroDocumento = numeroDocumento;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.altura = altura;
        this.telefone = telefone;
        this.email = email;
    }

    public PessoaModel(String nomeCompleto, String nomePai, String nomeMae, MunicipioModel municipioModel, String numeroDocumento, Date dataEmissao, Date dataValidade, Date dataNascimento, Sexo sexo, EstadoCivil estadoCivil, float altura, String telefone, String email) {
        this.nomeCompleto = nomeCompleto;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.municipioModel = municipioModel;
        this.numeroDocumento = numeroDocumento;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.altura = altura;
        this.telefone = telefone;
        this.email = email;
    }

    public BigInteger getPkPessoa() {
        return pkPessoa;
    }

    public void setPkPessoa(BigInteger pkPessoa) {
        this.pkPessoa = pkPessoa;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public MunicipioModel getMunicipioModel() {
        return municipioModel;
    }

    public void setMunicipioModel(MunicipioModel municipioModel) {
        this.municipioModel = municipioModel;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    @Override
    public String toString() {
        return this.nomeCompleto;
    }

    public boolean isEmpty() {
        return nomeCompleto.isEmpty() || email.isEmpty()
                || telefone.isEmpty();
    }
}
