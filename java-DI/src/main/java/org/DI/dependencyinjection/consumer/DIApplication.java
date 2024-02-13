package org.DI.dependencyinjection.consumer;


import org.DI.dependencyinjection.service.MessageService;

public class DIApplication implements Consumer{

    private MessageService service;

    public DIApplication(MessageService svc){
        this.service=svc;
    }

    @Override
    public void processMessages(String msg, String rec){
        //do some msg validation, manipulation logic etc
        this.service.sendMessage(msg, rec);
    }

}

//One can argue that we can remove the email service instance creation from MyApplication class by
// having a constructor that requires email service as an argument But in this case, we are asking client applications or test classes to initializing the email service that is not a good design decision. Now let’s see how we can apply java dependency injection pattern to solve all the problems with the above implementation. Dependency Injection in java requires at least the following:
//
//Service components should be designed with base class or interface. It’s better to prefer interfaces or abstract classes that would define contract for the services.
//Consumer classes should be written in terms of service interface.
//Injector classes that will initialize the services and then the consumer classes.

//public class DIApplication {
//    private EmailService email = new EmailService();
//
//    public void processMessages(String msg, String rec){
//        //do some msg validation, manipulation logic etc
//        email.sendEmail(msg, rec);
//    }
//}