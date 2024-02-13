package org.DI;

import org.DI.dependencyinjection.consumer.Consumer;
import org.DI.dependencyinjection.consumer.DIApplication;
import org.DI.dependencyinjection.injector.MessageServiceInjector;
import org.DI.dependencyinjection.service.MessageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DIApplicationJUnitTest {

    private MessageServiceInjector injector;
    @BeforeEach
    public void setUp(){
        //mock the injector with anonymous class
        injector = new MessageServiceInjector() {

            @Override
            public Consumer getConsumer() {
                //mock the message service
                return new DIApplication(new MessageService() {

                    @Override
                    public void sendMessage(String msg, String rec) {
                        System.out.println("Mock Message Service implementation");

                    }
                });
            }
        };
    }

    @Test
    public void test() {
        Consumer consumer = injector.getConsumer();
        consumer.processMessages("Hi Pankaj", "pankaj@abc.com");
    }

    @AfterEach
    public void tear(){
        injector = null;
    }

}