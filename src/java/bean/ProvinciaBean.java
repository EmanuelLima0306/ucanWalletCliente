/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import dao.ProvinciaDao;
import enumerator.TipoMensagem;
import java.util.ArrayList;
import java.util.List;
import model.ProvinciaModel;

/**
 *
 * @author emanuellima
 */
public class ProvinciaBean {

    private ProvinciaModel provinciaModel;
    private ProvinciaDao provinciaDao;

    public TipoMensagem saveOrUpdate(String confirmarSenha) {
        try {
            if (provinciaModel != null) {
                if (!provinciaModel.isEmpty()) {
                    provinciaDao = new ProvinciaDao();
                    if(provinciaModel.getPkProvincia() == null){
                        if (provinciaDao.insert(provinciaModel)) {
                            return TipoMensagem.SUCESSO;
                        }
                        return TipoMensagem.ERRO_INESPERADO;
                    }else{
                        if(provinciaDao.update(provinciaModel)){
                            return TipoMensagem.SUCESSO;
                        }
                        return TipoMensagem.ERRO_INESPERADO;
                    }
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
    
    public List<ProvinciaModel> getAll(){
        List<ProvinciaModel> list = new ArrayList<>();
        try {
            provinciaDao = new ProvinciaDao();
            list = provinciaDao.getAll();
        } catch (Exception e) {
        }
        return list;
    }
    
    public ProvinciaModel getById(int id){
        try {
            provinciaDao = new ProvinciaDao();
            return provinciaDao.findById(id);
        } catch (Exception e) {
        }
        return null;
    }
   
    public ProvinciaModel getModel() {
        return this.provinciaModel;
    }

    public void setModel(ProvinciaModel provinciaModel) {
        this.provinciaModel = provinciaModel;
    }
}
