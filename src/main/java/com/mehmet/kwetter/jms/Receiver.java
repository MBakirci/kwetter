package com.mehmet.kwetter.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by mehmet on 13-5-17.
 */
@javax.ejb.Startup
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/test"),
})
public class Receiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage m = (TextMessage) message;
            try {
                System.out.println("JSM || Reading message: " + m.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
