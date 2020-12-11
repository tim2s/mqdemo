package de.tim2s.demo.mqdemo.mqreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@RestController
public class MessageController {

    private final MessageService messageService;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/add")
    public void add(@RequestParam("message") String text) {
        try {
            messageService.sendMessage(text);
        } catch (JMSException e) {
            LOG.error("Could not send message", e);
        }
    }
}
