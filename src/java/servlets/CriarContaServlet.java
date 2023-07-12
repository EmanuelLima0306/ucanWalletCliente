/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bean.ClienteBean;
import bean.ContaBean;
import enumerator.TipoMensagem;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContaModel;
import model.UsuarioModel;

/**
 *
 * @author emanuellima
 */
@WebServlet(name = "CriarContaServlet", urlPatterns = {"/CriarContaServlet", "/CriarConta"})
public class CriarContaServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        log("Iniciando o servlet");
    }

    @Override
    public void destroy() {
        super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        log("destruindo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String saldo = req.getParameter("saldoInicial");
        float saldoInicial = saldo != null ? Float.parseFloat(req.getParameter("saldoInicial")) : 0;

        UsuarioModel usuarioModel = (UsuarioModel) req.getSession().getAttribute("Usuario");

        ContaBean contaBean = new ContaBean();
        ContaModel contaModel = new ContaModel();
        contaModel.setSaldoContablistico(saldoInicial);
        contaModel.setSaldoDisponivel(saldoInicial);
        contaModel.setClienteModel(new ClienteBean().findByPessoa(usuarioModel.getPessoaModel()));

        contaBean.setModel(contaModel);

        TipoMensagem tipoMensagem = contaBean.saveOrUpdate();

        if (tipoMensagem == TipoMensagem.SUCESSO) {
              resp.sendRedirect("home");
//            RequestDispatcher rd = req.getRequestDispatcher("/home");
//            rd.forward(req, resp);
        } else {
//            resp.sendRedirect("home");
//            RequestDispatcher rd = req.getRequestDispatcher("/home");
//            rd.forward(req, resp);
        }
    }

}
