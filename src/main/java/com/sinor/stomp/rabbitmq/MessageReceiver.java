package com.sinor.stomp.rabbitmq;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private final CountDownLatch latch = new CountDownLatch(1);
    private final MessageConverter messageConverter;

    @Autowired
    public MessageReceiver(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    /**
     * @param received byte[]
     * @see com.sinor.stomp.config.RabbitMQConfig
     */
    public void receiveMessage(byte[] received) {
        String message = new String(received, StandardCharsets.UTF_8);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message message1 = messageConverter.toMessage(message, messageProperties);
        String message2 = (String) messageConverter.fromMessage(message1);

        System.out.println(message2);
        latch.countDown();
    }

}