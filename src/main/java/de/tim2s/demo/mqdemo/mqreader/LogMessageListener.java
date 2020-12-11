package de.tim2s.demo.mqdemo.mqreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class LogMessageListener implements MessageListener {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message) {
        try {
            LOG.info("Received a message");
            TextMessage textMessage = (TextMessage) message;
            LOG.info("MessageText: {}", textMessage.getText());
            message.acknowledge();
        } catch (JMSException e) {
            LOG.error("Error while reading the message", e);
        }
    }
}
