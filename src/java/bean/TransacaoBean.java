/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import dao.TransacaoDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ContaModel;
import model.TransacaoModel;

/**
 *
 * @author emanuellima
 */
public class TransacaoBean {

    private TransacaoModel transacaoModel;
    private TransacaoDao transacaoDao;

    public List<TransacaoModel> getAll() {
        try {
            transacaoDao = new TransacaoDao();
            return transacaoDao.getAll();
        } catch (Exception ex) {
            Logger.getLogger(TransacaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<TransacaoModel> getByConta(ContaModel contaModel) {
        List<TransacaoModel> list = new ArrayList<>();
        try {
            transacaoDao = new TransacaoDao();
            if (contaModel != null) {
                list = transacaoDao.findByConta(contaModel);
            }
        } catch (Exception ex) {
            Logger.getLogger(TransacaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public TransacaoModel getLast() {
        try {
            transacaoDao = new TransacaoDao();
            return transacaoDao.findLast();
        } catch (Exception ex) {
            Logger.getLogger(TransacaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TransacaoModel getModel() {
        return this.transacaoModel;
    }

    public void setModel(TransacaoModel transacaoModel) {
        this.transacaoModel = transacaoModel;
    }
}
