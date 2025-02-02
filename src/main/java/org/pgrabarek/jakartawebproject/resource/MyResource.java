package org.pgrabarek.jakartawebproject.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.hibernate.SessionFactory;
import org.pgrabarek.jakartawebproject.model.ExamplePojo;
import org.pgrabarek.jakartawebproject.spring.SpringExampleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("myresource")
public class MyResource {
    private static final Logger logger = LoggerFactory.getLogger(MyResource.class);
    private final SessionFactory sessionFactory;
    private final SpringExampleBean springExampleBean;

    @Inject
    public MyResource(SessionFactory sessionFactory, SpringExampleBean springExampleBean) {
        this.sessionFactory = sessionFactory;
        this.springExampleBean = springExampleBean;
    }

    @GET
    public String getExamplePojo() {
        logger.debug("persisting data using HQL");
        sessionFactory.inTransaction(session -> {
            session.persist(new ExamplePojo("World"));
        });
        logger.debug("querying data using HQL");
        sessionFactory.inSession(session -> {
            ExamplePojo examplePojo = session.createQuery("from ExamplePojo", ExamplePojo.class)
                    .getResultList()
                    .getFirst();
        });
        ExamplePojo examplePojo = sessionFactory.openSession().createQuery("from ExamplePojo", ExamplePojo.class).getResultList().getFirst();
        logger.info("ExamplePojo: {}", examplePojo.toString());
        return "Hello " + examplePojo.getName();
    }
    @GET
    @Path("/spring")
    public String getExampleSpringPojo() {
        return "Hello " + springExampleBean.getName();
    }
}
