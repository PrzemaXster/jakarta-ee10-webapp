package org.pgrabarek.jakartawebproject.spring;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Jersey DI mechanism will call {@link #justInTimeResolution(Injectee)} if it cannot find resources to inject in its scope.
 * Missing resource is searched in module's spring local bean registry and registered in Jersey if found.
 * <br>
 * <br>
 * This approach leaves Jersey DI all the heavy stuff (e.g. injecting fields marked with {@link jakarta.ws.rs.core.Context})
 * and gives possibility to inject beans defined in the module.
 */
public class SpringJustInTimeResolver implements JustInTimeInjectionResolver {

    private final ServiceLocator locator;

    @Inject
    public SpringJustInTimeResolver(ServiceLocator locator) {
        this.locator = locator;
    }

    @Override
    public boolean justInTimeResolution(Injectee failedInjectionPoint) {
        Class<?> aClass = getClass(failedInjectionPoint.getRequiredType());
        String name = getName(failedInjectionPoint);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringExports.class);

        try {

            Object bean = name != null
                    ? ctx.getBean(name)
                    : ctx.getBean(aClass);
            ServiceLocatorUtilities.addOneConstant(locator, bean, name, failedInjectionPoint.getRequiredType());

            return true;
        } catch (NoSuchBeanDefinitionException ignored) {
            // if we cannot find bean, simply return false
            return false;
        }
    }

    private String getName(Injectee injectee) {
        return injectee.getRequiredQualifiers().stream()
                .filter(Named.class::isInstance)
                .map(an -> ((Named) an).value())
                .findFirst()
                .orElse(null);
    }

    private Class<?> getClass(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;

            return (Class<?>) pt.getRawType();
        }

        return null;
    }
}