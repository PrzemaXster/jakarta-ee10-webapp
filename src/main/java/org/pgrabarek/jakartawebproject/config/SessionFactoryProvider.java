package org.pgrabarek.jakartawebproject.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.pgrabarek.jakartawebproject.model.ExamplePojo;

import java.util.function.Supplier;

public class SessionFactoryProvider implements Supplier<SessionFactory> {

    @Override
    public SessionFactory get() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(ExamplePojo.class)
                .buildSessionFactory();
        sessionFactory.getSchemaManager().exportMappedObjects(true);
        return sessionFactory;
    }
}