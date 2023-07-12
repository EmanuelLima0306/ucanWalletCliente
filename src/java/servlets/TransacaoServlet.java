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
import com.google.gson.Gson;
import enumerator.TipoMensagem;
import java.util.Date;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.RequestDispatcher;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import util.TransacaoServer;
import java.sql.Timestamp;

/**
 * ˚x
 *
 * @author emanuellima
 */
@WebServlet(name = "Transacao", asyncSupported = true, urlPatterns = {"/Transacao", "/transacao"})
public class TransacaoServlet extends HttpServlet {
//

    private ConnectionFactory connectionFactory;
    private Destination filaValidador;
    MessageConsumer consumer;

    @Override
    public void init() throws ServletException {
        // Inicializar a ConnectionFactory com a configuração adequada
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Definir a fila de validação de transações
        filaValidador = new ActiveMQQueue("fila.validacao");
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

                ContaModel contaDestino = contaBean.getModel();
                double valorTransferir = Double.parseDouble(valor);

                //Reduz o saldo disponivel da conta do cliente
                contaBean.reduzirDisponivel(contaOrigem, valorTransferir);
                TransacaoServer transacaoServer = new TransacaoServer(contaOrigem.getPkConta(), Integer.parseInt(numeroConta), Double.parseDouble(valor), TipoMensagem.ENVIANDO, new Timestamp(new Date().getTime()));

                enviarTransacao(transacaoServer, req, resp);
                req.setAttribute("valor", null);
                req.setAttribute("numeroConta", null);
            }
            req.setAttribute("typeMessage", tipoMensagem.getDescricao());
        }

        req.getRequestDispatcher("transacao.jsp").forward(req, resp);

    }

    public void enviarTransacao(TransacaoServer transacaoServer, HttpServletRequest req, HttpServletResponse resp) {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(filaValidador);

            // Converter a transação em uma mensagem JMS
            TextMessage textMessage = session.createTextMessage(transacaoServer.toJson());

            // Definir a fila de retorno para receber a resposta do servidor
            Destination filaRetorno = new ActiveMQQueue("fila.resposta");
            textMessage.setJMSReplyTo(filaValidador);

            // Criar um consumidor para receber a resposta
            consumer = session.createConsumer(filaRetorno);

            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage respostaMessage = (TextMessage) message;
                            String resposta = respostaMessage.getText();
                            Gson gson = new Gson();
                            TransacaoServer transacaoServer1 = gson.fromJson(resposta, TransacaoServer.class);
                            req.setAttribute("typeMessage", transacaoServer1.getTipoMensagem().getDescricao());
                            resp.sendRedirect("/home");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            producer.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
