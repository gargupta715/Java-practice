package org.DI.dependencyinjection.test;

import org.DI.dependencyinjection.consumer.Consumer;
import org.DI.dependencyinjection.injector.EmailServiceInjector;
import org.DI.dependencyinjection.injector.MessageServiceInjector;
import org.DI.dependencyinjection.injector.SMSServiceInjector;

public class DITest {
        public static void main(String[] args){
            String msg = "Hi Pankaj";
            String email = "pankaj@abc.com";
            String phone = "4088888888";
            MessageServiceInjector injector = null;
            Consumer app = null;

            //Send email
            injector = new EmailServiceInjector();
            app = injector.getConsumer();
            app.processMessages(msg, email);

            //Send SMS
            injector = new SMSServiceInjector();
            app = injector.getConsumer();
            app.processMessages(msg, phone);
//              DIApplication app = new DIApplication();
//              app.processMessages("Hi Pankaj", "pankaj@abc.com");
        }
}
