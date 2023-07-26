/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bean.UsuarioBean;
import enumerator.TipoMensagem;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jms.ClienteJMS;
import model.PessoaModel;
import model.UsuarioModel;

/**
 *
 * @author emanuellima
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet", "/login"})
public class LoginServlet extends HttpServlet {

    ClienteJMS clienteJMS;

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        log("Iniciando o Sevlet");

    }

    @Override
    public void destroy() {
        super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        log("Destroindo o Servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action.equals("login")) {
            String usuario = req.getParameter("usuario");
            String senha = req.getParameter("senha");

            UsuarioModel usuarioModel = new UsuarioModel();
            PessoaModel pessoaModel = new PessoaModel();
            pessoaModel.setEmail(usuario);
            pessoaModel.setTelefone(usuario);
            usuarioModel.setPessoaModel(pessoaModel);
            usuarioModel.setSenha(senha);

            UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.setModel(usuarioModel);

            TipoMensagem tipoMensagem = usuarioBean.autenticacao();
            if (tipoMensagem == TipoMensagem.SUCESSO) {
                req.getSession().setAttribute("Usuario", usuarioBean.getModel());
                req.getSession().setMaxInactiveInterval(3600);//aumenta o tempo da sessao para 1h

                //Cria uma instância do JMS
                clienteJMS = new ClienteJMS();
                //Ouvir as mensagens
                clienteJMS.consumer(usuarioBean.getModel());
//            RequestDispatcher rd = req.getRequestDispatcher("/home");
//            rd.forward(req, resp);
                resp.sendRedirect("home");
            } else {
                req.setAttribute("typeMessage", TipoMensagem.USUARIO_OU_SENHA_INVALIDA.getDescricao());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else if (action.equals("logout")) {
            // fecha a conexão do jms
            clienteJMS.closeConnection();

            req.getSession().setAttribute("Usuario", null);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

    }

}
