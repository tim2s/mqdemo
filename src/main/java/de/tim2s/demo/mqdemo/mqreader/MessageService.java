package de.tim2s.demo.mqdemo.mqreader;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.jms.*;

@Service
public class MessageService {

    private ActiveMQConnectionFactory connectionFactory;

    @Value("${mq.topic.name}")
    private String topicName;

    @Value("${mq.user}")
    private String user;

    @Value("${mq.password}")
    private String password;

    @Value("${mq.host}")
    private String host;

    @PostConstruct()
    private void startToListen() throws JMSException {
        this.connectionFactory = new ActiveMQConnectionFactory(user, password, "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Topic destination = new ActiveMQTopic(topicName);
        Session receiverSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer receiver = receiverSession.createConsumer(destination);
        receiver.setMessageListener(new LogMessageListener());
    }

    void sendMessage(String text) throws JMSException {
        Connection connection = connectionFactory.createConnection();
        Session senderSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic destination = new ActiveMQTopic(topicName);
        TextMessage message = senderSession.createTextMessage();
        message.setText(text);
        MessageProducer sender = senderSession.createProducer(destination);
        sender.send(message);
        senderSession.close();
    }

}
