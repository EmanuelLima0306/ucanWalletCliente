/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import dao.ClienteDao;
import enumerator.TipoMensagem;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClienteModel;
import model.PessoaModel;

/**
 *
 * @author emanuellima
 */
public class ClienteBean {

    private ClienteModel clienteModel;
    private ClienteDao clienteDao;

    public TipoMensagem saveOrUpdate() { // Função para salvar cliente
        try {
            if (clienteModel != null) {
                if (!clienteModel.isEmpty()) {
                    clienteDao = new ClienteDao();
                    ClienteModel clienteAux = getByEmailOrTelefone(clienteModel.getPessoaModel().getEmail(), clienteModel.getPessoaModel().getTelefone()); //busca o cliente que ja tem o id ou email

                    if (clienteAux == null && clienteModel.getPkCliente() == null) { // se o cliente não for encontrado e não tiver uma pk
                        PessoaBean pessoaBean = new PessoaBean();
                        pessoaBean.setModel(clienteModel.getPessoaModel());
                        TipoMensagem tipoMensagem = pessoaBean.saveOrUpdate(); // salva ou seleciona a pessoa caso exista, no caso de pessoas que não era cliente e decidu ser cliente

                        if (tipoMensagem == TipoMensagem.SUCESSO || tipoMensagem == TipoMensagem.DADOS_EXISTENTES) {
                            clienteModel.setPessoaModel(pessoaBean.getModel());
                            if (clienteDao.insert(clienteModel)) {
                                clienteModel = getLast();
                                return TipoMensagem.SUCESSO;
                            }
                            return TipoMensagem.ERRO;
                        }
                        return TipoMensagem.ERRO;

                    } else {
                        if (clienteModel.getPkCliente() != null) {
                            if (clienteDao.update(clienteModel)) {
                                return TipoMensagem.SUCESSO;
                            }
                        }
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

    public ClienteModel getByEmailOrTelefone(String email, String telefone) {
        try {
            clienteDao = new ClienteDao();
            return clienteDao.findByEmailOrTelefone(email, telefone);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ClienteModel getLast() {
        try {
            clienteDao = new ClienteDao();
            return clienteDao.findLast();
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ClienteModel findByPessoa(PessoaModel pessoaModel) {
        try {
            clienteDao = new ClienteDao();
            return clienteDao.findByPessoa(pessoaModel);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ClienteModel getModel() {
        return this.clienteModel;
    }

    public void setModel(ClienteModel pessoaModel) {
        this.clienteModel = pessoaModel;
    }
}
