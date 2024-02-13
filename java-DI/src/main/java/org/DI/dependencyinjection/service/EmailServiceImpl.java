package org.DI.dependencyinjection.service;


public class EmailServiceImpl implements MessageService {

    @Override
    public void sendMessage(String msg, String rec) {
        //logic to send email
        System.out.println("Email sent to "+rec+ " with Message="+msg);
    }

}

// step-1
//public class EmailServiceIml {
//
//    public void sendEmail(String message, String receiver){
//        //logic to send email
//        System.out.println("Email sent to "+receiver+ " with Message="+message);
//    }
//}