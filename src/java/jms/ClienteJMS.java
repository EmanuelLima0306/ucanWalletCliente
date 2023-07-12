
package jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import model.TransacaoModel;

/**
 *
 * @author emanuellima
 */
public class ClienteJMS implements MessageListener {

    public TopicConnection connection;
    private TopicSession session;
    private TopicPublisher publisher;

    public ClienteJMS(String topicName, String initialContextFactory, String providerUrl) {
        try {
            
            // Configurar a conexão JMS
            java.util.Properties properties = new java.util.Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
            properties.setProperty(Context.PROVIDER_URL, providerUrl);
            Context context = new InitialContext(properties);
            TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
            connection = factory.createTopicConnection();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
//            Topic topic = (Topic) context.lookup(topicName);
            Topic topic = (Topic) session.createTopic(topicName);

            // Criar o publicador para enviar mensagens
            publisher = session.createPublisher(topic);

            // Configurar o assinante para receber mensagens
            TopicSubscriber subscriber = session.createSubscriber(topic);
            subscriber.setMessageListener(this);

            // Iniciar a conexão JMS
            connection.start();
        } catch (Exception e) {
            System.err.print(e);
        }
    }

//    private ConnectionFactory connectionFactory;
//    private Destination filaValidador;
//    public void enviarTransacao(TransacaoModel transacaoModel) {
//        try {
//            Context ctx = new InitialContext();
////            connectionFactory = (ConnectionFactory) ctx.lookup("java:comp/env/jms/myQueueFactory");
//            connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
//            Connection connection = connectionFactory.createConnection();
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            filaValidador = session.createQueue("fila_validador");
//            MessageProducer producer = session.createProducer(filaValidador);
//
//            // Converter a transação em uma mensagem JMS
//            TextMessage textMessage = session.createTextMessage(transacaoModel.toJson());
//
//            // Definir a fila de retorno para receber a resposta do servidor
//            Destination filaRetorno = session.createTemporaryQueue();
//            textMessage.setJMSReplyTo(filaRetorno);
//
//            // Criar um consumidor para receber a resposta
//            MessageConsumer consumer = session.createConsumer(filaRetorno);
//
//            consumer.setMessageListener(new MessageListener() {
//                public void onMessage(Message message) {
//                    try {
//                        if (message instanceof TextMessage) {
//                            TextMessage respostaMessage = (TextMessage) message;
//                            String resposta = respostaMessage.getText();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            producer.send(textMessage);
//
//            producer.close();
//            session.close();
//            connection.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void sendMessage(TransacaoModel transacaoModel) {
        try {
            TextMessage message = session.createTextMessage();
            message.setText(transacaoModel.toJson());
            publisher.publish(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Mensagem recebida: " + textMessage.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
