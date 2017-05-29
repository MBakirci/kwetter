/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mehmet.kwetter.socket;

import com.google.gson.Gson;
import com.mehmet.kwetter.domain.Tweet;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Encodes {@link Message}s to JSON
 * @author jgeenen
 */
public class JsonEncoder implements Encoder.Text<Tweet> {
    
    private final Gson gson = new Gson();

    @Override
    public String encode(Tweet arg0) throws EncodeException {
        return gson.toJson(arg0);
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
