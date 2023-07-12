/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import dao.ContaDao;
import enumerator.Estado;
import enumerator.TipoMensagem;
import incriptacao.KeyGenerator;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClienteModel;
import model.ContaModel;
import model.PessoaModel;

/**
 *
 * @author emanuellima
 */
public class ContaBean {

    private ContaModel contaModel;
    private ContaDao contaDao;

    public TipoMensagem saveOrUpdate() { // Função para salvar
        try {
            if (contaModel != null) {
                if (!contaModel.isEmpty()) {
                    contaDao = new ContaDao();
                    if (contaModel.getPkConta() != null) { //verifica se tem pk
                        if (contaDao.update(contaModel)) {
                            return TipoMensagem.SUCESSO;
                        }
                        return TipoMensagem.ERRO;
                    } else {
                        ClienteBean clienteBean = new ClienteBean();
                        clienteBean.setModel(contaModel.getClienteModel());
                        TipoMensagem tipoMensagem = clienteBean.saveOrUpdate();
                        ClienteModel clienteModel = clienteBean.getModel();
                        if (tipoMensagem == TipoMensagem.SUCESSO) {
                            contaModel.setClienteModel(clienteModel);
                            contaModel.setEstado(Estado.ACTIVO);
                            KeyPair keyPair = KeyGenerator.generateKeyPair();// gera o par de chaves
                            contaModel.setChavePublica(keyPair.getPublic().getEncoded());// pega a chave publica
                            contaModel.setChavePrivada(keyPair.getPrivate().getEncoded());// pega a chave privada
                            if (contaDao.insert(contaModel)) {
                                contaModel = findLast();
                                return TipoMensagem.SUCESSO;
                            }
                        }
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
        return TipoMensagem.ERRO_INESPERADO;
    }

    public List<ContaModel> getAllByPessoa(PessoaModel pessoaModel) {
        List<ContaModel> list = new ArrayList<>();
        try {
            contaDao = new ContaDao();
            list = contaDao.findAllByPessoa(pessoaModel);
        } catch (Exception ex) {
            Logger.getLogger(ContaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public TipoMensagem reduzirDisponivel(ContaModel contaModel, double valor) {
        try {
            contaDao = new ContaDao();
            if (contaModel != null) {
                if (contaDao.reduzirDisponivel(contaModel, valor)) {
                    return TipoMensagem.SUCESSO;
                }
            }else{
                return TipoMensagem.NULL;
            }
        } catch (Exception ex) {
            Logger.getLogger(ContaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TipoMensagem.ERRO;
    }

    public ContaModel findLast() {
        try {
            contaDao = new ContaDao();
            return contaDao.findLast();
        } catch (Exception ex) {
            Logger.getLogger(ContaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TipoMensagem findById(int id) {
        try {
            contaDao = new ContaDao();
            contaModel = contaDao.findById(id);
            if (contaModel != null) {
                return TipoMensagem.SUCESSO;
            }
        } catch (Exception ex) {
            Logger.getLogger(ContaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TipoMensagem.DADOS_NAO_EXISTENTE;
    }

    public TipoMensagem findByNumeroConta(int numeroConta) {
        try {
            contaDao = new ContaDao();
            contaModel = contaDao.findByNumeroConta(numeroConta);
            if (contaModel != null) {
                return TipoMensagem.SUCESSO;
            }
        } catch (Exception ex) {
            Logger.getLogger(ContaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TipoMensagem.DADOS_NAO_EXISTENTE;
    }

    public ContaModel getModel() {
        return this.contaModel;
    }

    public void setModel(ContaModel contaModel) {
        this.contaModel = contaModel;
    }
}
