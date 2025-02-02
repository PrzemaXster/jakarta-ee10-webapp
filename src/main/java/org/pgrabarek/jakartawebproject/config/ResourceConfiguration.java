package org.pgrabarek.jakartawebproject.config;

import jakarta.inject.Singleton;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import org.pgrabarek.jakartawebproject.spring.SpringJustInTimeResolver;

@ApplicationPath("rest")
public class ResourceConfiguration extends ResourceConfig {

    public ResourceConfiguration() {
        packages("org.pgrabarek.jakartawebproject.resource");
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(SessionFactoryProvider.class).to(SessionFactory.class).in(Singleton.class);
                bind(SpringJustInTimeResolver.class).to(JustInTimeInjectionResolver.class);
            }
        });
    }

}