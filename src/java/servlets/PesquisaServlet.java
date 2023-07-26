/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bean.ContaBean;
import bean.TransacaoBean;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContaModel;
import model.TransacaoModel;
import model.UsuarioModel;

/**
 *
 * @author emanuellima
 */
@WebServlet(name = "PesquisaServlet", urlPatterns = {"/PesquisaServlet", "/pesquisa", "/pesquisaServlet"})
public class PesquisaServlet extends HttpServlet {

    ContaBean contaBean = new ContaBean();

    //Atributos
    String contasAtribute = "contas";
    String contaSelectedAtribute = "contaSelected";
    String transacoes = "transacoes";

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        log("Iniciando a Home");
    }

    @Override
    public void destroy() {
        super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        log("Destruindo a home");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioModel usuarioModel = (UsuarioModel) request.getSession().getAttribute("Usuario"); // pego o usuario autenticado
        ContaModel contaModelSelected = (ContaModel) request.getSession().getAttribute(contaSelectedAtribute);
        var pesquisa = request.getParameter("textoPesquisa");
        if (usuarioModel != null) { // Verifico se a rota está a ser acessado por usuário autenticado

            List<ContaModel> contas = contaBean.getAllByPessoa(usuarioModel.getPessoaModel()); // busca todas as contas da pessoa logada
            request.getSession().setAttribute(contasAtribute, contas); // carrego a lista de contas

            contaModelSelected = contaModelSelected != null ? contaModelSelected : !contas.isEmpty() ? contas.get(0) : null; // pega a conta selecionada
            request.getSession().setAttribute(contaSelectedAtribute, contaModelSelected);
            TransacaoBean transacaoBean = new TransacaoBean();
            List<TransacaoModel> transacaoModels = transacaoBean.getByContaAndPesquisa(contaModelSelected, (String) pesquisa);
            request.getSession().setAttribute(transacoes, transacaoModels);

            RequestDispatcher rd = request.getRequestDispatcher("home.jsp"); // chamo a pagina home
            rd.forward(request, response);
        } else {
            response.sendRedirect("erro401.jsp");// chama a pagina de erro de autenticação
        }

    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
