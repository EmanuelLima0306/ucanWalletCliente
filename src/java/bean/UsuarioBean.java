/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import dao.UsuarioDao;
import enumerator.TipoMensagem;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UsuarioModel;

/**
 *
 * @author emanuellima
 */
public class UsuarioBean {

    private UsuarioModel usuarioModel;
    private UsuarioDao usuarioDao;

    public TipoMensagem saveOrUpdate(String confirmarSenha) {
        try {
            if (usuarioModel != null) {
                if (!usuarioModel.isEmpty()) {
                    if (usuarioModel.getSenha().equals(confirmarSenha)) {
                        TipoMensagem tipoMensagem; // criou uma variar do tipoMensagem para pegar a mensagem retorna

                        usuarioDao = new UsuarioDao();
                        PessoaBean pessoaBean = new PessoaBean(); // crio uma instancia da pessoaBean
                        pessoaBean.setModel(usuarioModel.getPessoaModel()); // passo a pessoa para minha bean
                        tipoMensagem = pessoaBean.saveOrUpdate();//Salvar os dados em cliente
                        if (tipoMensagem == TipoMensagem.SUCESSO) { // Salvar os dados como pessoa
                            usuarioModel.setPessoaModel(pessoaBean.getModel()); //pega os dados do usuario
                            if (usuarioModel.getPkUsuario() != null) { // Verifica se o usuário possui pk
                                if (usuarioDao.update(usuarioModel)) {
                                    return TipoMensagem.SUCESSO;
                                }
                                return TipoMensagem.ERRO_INESPERADO;
                            } else {
                                if (usuarioDao.insert(usuarioModel)) {
                                    usuarioModel = getLast();
                                    return TipoMensagem.SUCESSO;
                                }
                            }
                        }
                        return tipoMensagem;
                    }
                    return TipoMensagem.CREDENCIAS_NAO_CONDIZEM;
                } else {
                    return TipoMensagem.PREENCHA_CAMPO;
                }
            } else {
                return TipoMensagem.NULL;
            }
        } catch (Exception ex) {
            System.err.print(ex);
            return TipoMensagem.ERRO_INESPERADO;
        }
    }
    
    public UsuarioModel getLast(){
        try {
            usuarioDao = new UsuarioDao();
            return usuarioDao.findLast();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TipoMensagem autenticacao() {
        try {
            usuarioDao = new UsuarioDao();
            if (usuarioModel != null) {
                if(usuarioModel.getPessoaModel().getEmail().isEmpty() || usuarioModel.getSenha().isEmpty()){
                    return TipoMensagem.PREENCHA_CAMPO;
                }
                usuarioModel = usuarioDao.autenticacao(usuarioModel);
                if(usuarioModel != null){
                    return TipoMensagem.SUCESSO;
                }
                return TipoMensagem.CREDENCIAIS_INVALIDA;
            }
            return TipoMensagem.NULL;

        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public UsuarioModel getModel() {
        return this.usuarioModel;
    }

    public void setModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
}
