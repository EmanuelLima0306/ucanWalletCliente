/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import dao.PessoaDao;
import enumerator.TipoMensagem;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PessoaModel;

/**
 *
 * @author emanuellima
 */
public class PessoaBean {

    private PessoaModel pessoaModel;
    private PessoaDao pessoaDao;

    public TipoMensagem saveOrUpdate() { // Função para salvar Pessoa
        try {
            if (pessoaModel != null) {
                if (!pessoaModel.isEmpty()) {
                    pessoaDao = new PessoaDao();
                    PessoaModel pessoaModelAux = getByEmailOrTelefone(pessoaModel.getEmail(), pessoaModel.getTelefone());
                    if (pessoaModelAux == null && pessoaModel.getPkPessoa()== null) {
                        if (pessoaDao.insert(pessoaModel)) {
                            pessoaModel = getLast();
                            return TipoMensagem.SUCESSO;
                        }
                        return TipoMensagem.ERRO;

                    } else {
                        if (pessoaModel.getPkPessoa()!= null) {
                            if (pessoaDao.update(pessoaModel)) {
                                return TipoMensagem.SUCESSO;
                            }
                        }
                        pessoaModel = pessoaModelAux;
                        return TipoMensagem.DADOS_EXISTENTES;
                    }

                } else {
                    return TipoMensagem.PREENCHA_CAMPO;
                }
            } else {
                return TipoMensagem.NULL;
            }
        } catch (Exception ex) {
            System.out.print(ex);
            return TipoMensagem.ERRO_INESPERADO;
        }
    }

    public PessoaModel getByEmailOrTelefone(String email, String telefone) {
        try {
            pessoaDao = new PessoaDao();
            return pessoaDao.findByEmailOrTelefone(email, telefone);
        } catch (Exception ex) {
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public PessoaModel getLast() {
        try {
            pessoaDao = new PessoaDao();
            return pessoaDao.findLast();
        } catch (Exception ex) {
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public PessoaModel getModel() {
        return this.pessoaModel;
    }

    public void setModel(PessoaModel pessoaModel) {
        this.pessoaModel = pessoaModel;
    }
}
