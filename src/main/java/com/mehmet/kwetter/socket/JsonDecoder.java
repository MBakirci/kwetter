/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mehmet.kwetter.socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mehmet.kwetter.domain.Tweet;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Decodes {@link Message}s from JSON
 * @author jgeenen
 */
public class JsonDecoder implements Decoder.Text<Tweet>{

    private final Gson gson =new GsonBuilder().setDateFormat("dd-MM-yyyy hh:mm:ss").create();

    @Override
    public void init(EndpointConfig config) {

    }
    
    @Override
    public Tweet decode(String arg0) throws DecodeException {
        Tweet t = gson.fromJson(arg0, Tweet.class);
        return t;
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }


    @Override
    public void destroy() {

    }
    
}
