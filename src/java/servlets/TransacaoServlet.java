/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContaModel;
import bean.ContaBean;
import bean.TransacaoBean;
import enumerator.TipoMensagem;
import incriptacao.RSAEncryption;
import incriptacao.RSAKeyUtils;
import java.security.PublicKey;
import java.util.Date;
import util.TransacaoServer;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import jms.ClienteJMS;
import model.TransacaoModel;

/**
 * ˚x
 *
 * @author emanuellima
 */
@WebServlet(name = "Transacao", asyncSupported = true, urlPatterns = {"/Transacao", "/transacao"})
public class TransacaoServlet extends HttpServlet {

    ClienteJMS ClienteJMS;

    @Override
    public void init() throws ServletException {
        //Cria uma nova instancia de ClienteJMS
        ClienteJMS = new ClienteJMS();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var idConta = req.getParameter("idConta");

        if (idConta != null && req != null) {
            TransacaoBean transacaoBean = new TransacaoBean();
            ContaBean contaBean = new ContaBean();
            //Recupera a conta selecionada
            contaBean.findById(Integer.parseInt(idConta));
            ContaModel contaSelecionada = contaBean.getModel();
            List<TransacaoModel> transacaoModels = transacaoBean.getByConta(contaSelecionada);
            req.getSession().setAttribute("transacoes", transacaoModels);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var numeroConta = req.getParameter("numeroConta");
        var valor = req.getParameter("valor");
        ContaModel contaOrigem = (ContaModel) req.getSession().getAttribute("contaSelected");

        ContaBean contaBean = new ContaBean();

        if (numeroConta != null && valor != null) {

            TipoMensagem tipoMensagem = contaBean.findByNumeroConta(Integer.parseInt(numeroConta));
            if (tipoMensagem == TipoMensagem.SUCESSO) {
                try {
                    ContaModel contaDestino = contaBean.getModel();
                    double valorTransferir = Double.parseDouble(valor);

                    //Reduz o saldo disponivel da conta do cliente
                    contaBean.reduzirDisponivel(contaOrigem, valorTransferir);
                    PublicKey publicKey = RSAKeyUtils.publicKeyFromBytes(contaOrigem.getChavePublica());
                    TransacaoServer transacaoServer = new TransacaoServer(contaOrigem.getPkConta(), RSAEncryption.encrypt(Integer.parseInt(numeroConta), publicKey), RSAEncryption.encrypt(Double.parseDouble(valor), publicKey), RSAEncryption.encrypt(TipoMensagem.ENVIANDO, publicKey), RSAEncryption.encrypt(new Timestamp(new Date().getTime()), publicKey));

                    enviarTransacao(transacaoServer, req, resp);
                    req.setAttribute("valor", null);
                    req.setAttribute("numeroConta", null);
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }
            req.setAttribute("typeMessage", tipoMensagem.getDescricao());
        }

    }

    public void enviarTransacao(TransacaoServer transacaoServer, HttpServletRequest req, HttpServletResponse resp) {
        ClienteJMS.sendMessage(transacaoServer);
    }

}
