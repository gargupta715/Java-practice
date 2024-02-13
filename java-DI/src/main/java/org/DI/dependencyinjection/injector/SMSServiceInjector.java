package org.DI.dependencyinjection.injector;

import org.DI.dependencyinjection.consumer.Consumer;
import org.DI.dependencyinjection.service.SMSServiceImpl;
import org.DI.dependencyinjection.consumer.DIApplication;

public class SMSServiceInjector implements MessageServiceInjector {

    @Override
    public Consumer getConsumer() {
        return new DIApplication(new SMSServiceImpl());
    }

}
