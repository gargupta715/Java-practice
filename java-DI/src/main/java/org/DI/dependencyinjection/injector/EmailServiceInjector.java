package org.DI.dependencyinjection.injector;

import org.DI.dependencyinjection.consumer.Consumer;
import org.DI.dependencyinjection.service.EmailServiceImpl;
import org.DI.dependencyinjection.consumer.DIApplication;

public class EmailServiceInjector implements MessageServiceInjector {

    @Override
    public Consumer getConsumer() {
        return new DIApplication(new EmailServiceImpl());
    }

}
