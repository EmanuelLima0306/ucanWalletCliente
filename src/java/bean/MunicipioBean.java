/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import dao.MunicipioDao;
import enumerator.TipoMensagem;
import java.util.ArrayList;
import java.util.List;
import model.MunicipioModel;
import model.ProvinciaModel;

/**
 *
 * @author emanuellima
 */
public class MunicipioBean {

    private MunicipioModel municipioModel;
    private MunicipioDao municipioDao;

    public TipoMensagem saveOrUpdate(String confirmarSenha) {
        try {
            if (municipioModel != null) {
                if (!municipioModel.isEmpty()) {
                    municipioDao = new MunicipioDao();
                    if(municipioModel.getPkMunicipio()== null){
                        if (municipioDao.insert(municipioModel)) {
                            return TipoMensagem.SUCESSO;
                        }
                        return TipoMensagem.ERRO_INESPERADO;
                    }else{
                        if(municipioDao.update(municipioModel)){
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
    
    public List<MunicipioModel> getAll(){
        List<MunicipioModel> list = new ArrayList<>();
        try {
            municipioDao = new MunicipioDao();
            list = municipioDao.getAll();
        } catch (Exception e) {
        }
        return list;
    }
    
    public MunicipioModel getById(int id){
        try {
            municipioDao = new MunicipioDao();
            return municipioDao.findById(id);
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<MunicipioModel> getByProvincia(ProvinciaModel provinciaModel){
        List<MunicipioModel> list = new ArrayList<>();
        try {
            municipioDao = new MunicipioDao();
            list = municipioDao.findByProvincia(provinciaModel);
        } catch (Exception e) {
        }
        return list;
    }
   
    public MunicipioModel getModel() {
        return this.municipioModel;
    }

    public void setModel(MunicipioModel municipioModel) {
        this.municipioModel = municipioModel;
    }
}
