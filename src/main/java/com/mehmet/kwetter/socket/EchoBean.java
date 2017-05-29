/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mehmet.kwetter.socket;

import javax.ejb.*;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EchoBean {

    @EJB
    private EchoBean delegate;

    @Asynchronous
    public void send(Session session, Message message, int repeats, long delay, double delayMultiplier ){
        try {
            synchronized(session){
                session.getBasicRemote().sendObject(message);
            }
            Thread.sleep(delay);
        } catch (InterruptedException | IOException | EncodeException ex) {
            throw new IllegalStateException(ex);
        }
        if(1<repeats){
            delegate.send(
                session, 
                new Message("." + message.getText()), 
                repeats-1, 
                Math.round(delay*delayMultiplier), 
                delayMultiplier
            );
        }

    }
}
