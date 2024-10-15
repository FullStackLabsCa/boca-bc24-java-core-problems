package io.reactivestax;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtilClass {

    private static final String DEFAULT_RESOURCE = "hibernate.cfg.xml";
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtilClass.class);
    private static final SessionFactory sessionFactory = buildSessionFactory(DEFAULT_RESOURCE);

    private static SessionFactory buildSessionFactory(String resource) {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(new File(resource));
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Enrollment.class);
            configuration.addAnnotatedClass(Grades.class);


            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
