/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletReport;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContaModel;
import report.ExtratoReport;

/**
 *
 * @author emanuellima
 */
@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet","/extrato"})
public class RelatorioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String operacao = req.getParameter("action");
       
       if(operacao !=null){
           if(operacao.equals("extrato")){
               ContaModel contaModel = (ContaModel) req.getSession().getAttribute("contaSelected");
               ExtratoReport.conta(resp, getServletContext(), contaModel);
               
           }
       }
       
    }

    
}
