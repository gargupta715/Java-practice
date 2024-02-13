package org.DI.dependencyinjection.consumer;

import org.DI.dependencyinjection.service.MessageService;

// Setter injection
public class DIApplication1 implements Consumer{

    private MessageService service;

    public DIApplication1(){}

    //setter dependency injection
    public void setService(MessageService service) {
        this.service = service;
    }

    @Override
    public void processMessages(String msg, String rec){
        //do some msg validation, manipulation logic etc
        this.service.sendMessage(msg, rec);
    }

}
