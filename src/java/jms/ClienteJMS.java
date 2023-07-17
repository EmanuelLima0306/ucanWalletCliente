
package jms;

import bean.ContaBean;
import com.google.gson.Gson;
import enumerator.TipoMensagem;
import incriptacao.RSAEncryption;
import incriptacao.RSAKeyUtils;
import java.security.PrivateKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import model.ContaModel;
import model.UsuarioModel;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import util.TransacaoServer;

/**
 *
 * @author emanuellima
 */
public class ClienteJMS {

    // Atributos jms
    private static UsuarioModel usuarioModel;
    private ConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    private Destination filaValidador;
    Destination filaRetorno;
    MessageConsumer consumer;

    public ClienteJMS() {
        try{
        // Inicializar a ConnectionFactory com a configuração adequada
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Definir a fila de validação de transações
        filaValidador = new ActiveMQQueue("fila.validacao");
        filaRetorno = new ActiveMQQueue("fila.resposta");
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // Criar um consumidor para receber a resposta
        consumer = session.createConsumer(filaRetorno);
        }catch(Exception ex){
            System.err.println(ex);
        }
    }

    
    public void sendMessage(TransacaoServer transacaoServer) {
        try {
            MessageProducer producer = session.createProducer(filaValidador);

            // Converter a transação em uma mensagem JMS
            TextMessage textMessage = session.createTextMessage(transacaoServer.toJson());
            textMessage.setJMSReplyTo(filaValidador);

            producer.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumer(UsuarioModel usuario) {
        try {
            usuarioModel = usuario;
            
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage respostaMessage = (TextMessage) message;
                            String resposta = respostaMessage.getText();
                            Gson gson = new Gson();
                            TransacaoServer transacaoServer1 = gson.fromJson(resposta, TransacaoServer.class);

                            //Recuperação da conta de origem
                            ContaBean contaBean = new ContaBean();
                            contaBean.findById(transacaoServer1.getPkContaOrigem().intValue());
                            ContaModel contaOrigem = contaBean.getModel();
                            //Recuperação da chave privada
                            PrivateKey privateKey = RSAKeyUtils.privateKeyFromBytes(contaOrigem.getChavePrivada());

                            //Verifica se a pessoa que recebeu a resposta e a pessoa predestinada
                            if (usuarioModel.getPessoaModel().getPkPessoa().intValue() == contaOrigem.getClienteModel().getPessoaModel().getPkPessoa().intValue()) {
                                message.acknowledge();
                                System.out.println("Usuario predestinado recebeu:::");
                                
                                TipoMensagem tipoMensagem = (TipoMensagem) RSAEncryption.decrypt(transacaoServer1.getTipoMensagem(), privateKey);
//                                req.setAttribute("typeMessage", tipoMensagem.getDescricao());
//                                resp.sendRedirect("/home");
                            }else{
                                 System.out.println("Usuario não predestinado recebeu:::");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (JMSException ex) {
                Logger.getLogger(ClienteJMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
