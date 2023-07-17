/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bean.ClienteBean;
import bean.MunicipioBean;
import bean.ProvinciaBean;
import bean.UsuarioBean;
import enumerator.EstadoCivil;
import enumerator.Sexo;
import enumerator.TipoCliente;
import enumerator.TipoMensagem;
import enumerator.TipoUsuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClienteModel;
import model.MunicipioModel;
import model.PessoaModel;
import model.ProvinciaModel;
import model.UsuarioModel;
import util.DateUtil;

/**
 *
 * @author emanuellima
 */
@WebServlet(name = "CadastrarClienteServlet", urlPatterns = {"/CadastrarClienteServlet", "/cadastrarCliente", "/CadastrarCliente"})
public class CadastrarClienteServlet extends HttpServlet {

    //Atributos
    String proviciasParam = "provincias";
    String proviciaSelecionadaParam = "provinciaSelecionada";
    String municipiosParam = "municipios";

   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        requestDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nomeCompleto = req.getParameter("nomeCompleto");
        String nomePai = req.getParameter("nomePai");
        String nomeMae = req.getParameter("nomeMae");
        String municipioId = req.getParameter("municipio");
        String numeroDocumento = req.getParameter("numeroDocumento");
        String dataEmissao = req.getParameter("dataEmissao");
        String dataValidade = req.getParameter("dataValidade");
        String dataNascimento = req.getParameter("dataNascimento");
        String sexo = req.getParameter("sexo");
        String estadoCivil = req.getParameter("estadoCivil");
        String altura = req.getParameter("altura");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String confirmarSenha = req.getParameter("confirmarSenha");
        String tipoCliente = req.getParameter("tipoCliente");

        /* Pessoa */
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setNomeCompleto(nomeCompleto);
        pessoaModel.setNomePai(nomePai);
        pessoaModel.setNomeMae(nomeMae);
        pessoaModel.setMunicipioModel(new MunicipioBean().getById(Integer.parseInt(municipioId)));
        pessoaModel.setNumeroDocumento(numeroDocumento);
        pessoaModel.setDataEmissao(DateUtil.stringToDate(dataEmissao));
        pessoaModel.setDataValidade(DateUtil.stringToDate(dataValidade));
        pessoaModel.setDataNascimento(DateUtil.stringToDate(dataNascimento));
        pessoaModel.setSexo(Sexo.valueOf(sexo));
        pessoaModel.setEstadoCivil(EstadoCivil.valueOf(estadoCivil));
        pessoaModel.setAltura(Float.parseFloat(altura));
        pessoaModel.setEmail(email);
        pessoaModel.setTelefone(telefone);
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setPessoaModel(pessoaModel);
        clienteModel.setTipoCliente(TipoCliente.valueOf(tipoCliente));

        TipoMensagem mensageResponse;

        ClienteBean clienteBean = new ClienteBean();
        clienteBean.setModel(clienteModel);
        mensageResponse = clienteBean.saveOrUpdate();
        if (mensageResponse == TipoMensagem.SUCESSO) {

            UsuarioModel usuarioModel = new UsuarioModel(clienteBean.getModel().getPessoaModel(), TipoUsuario.CLIENTE, senha);

            UsuarioBean bean = new UsuarioBean();
            bean.setModel(usuarioModel);

            mensageResponse = bean.saveOrUpdate(confirmarSenha);

            if (mensageResponse == TipoMensagem.SUCESSO) {
                resp.sendRedirect("login");
            } else {
                req.setAttribute("typeMessage", mensageResponse.getDescricao());
                requestDispatcher(req, resp);
            }
        }
        req.setAttribute("typeMessage", mensageResponse.getDescricao());
        requestDispatcher(req, resp);
    }
    
   public void requestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pronvinciaID = req.getParameter("provinciaId");//Pega a provincia selecionada

        ProvinciaBean provinciaBean = new ProvinciaBean();
        MunicipioBean municipioBean = new MunicipioBean();

        ProvinciaModel provinciaSelecionada;
        List<MunicipioModel> municipios = new ArrayList<>();

        if (pronvinciaID != null) {
            provinciaSelecionada = provinciaBean.getById(Integer.parseInt(pronvinciaID));
            municipios = municipioBean.getByProvincia(provinciaSelecionada);
        }
        req.setAttribute(proviciasParam, provinciaBean.getAll());
        req.setAttribute(municipiosParam, municipios);

        req.getRequestDispatcher("cadastrarCliente.jsp").forward(req, resp);
    }

}
