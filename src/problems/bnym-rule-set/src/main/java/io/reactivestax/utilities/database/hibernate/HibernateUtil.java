package io.reactivestax.utilities.database.hibernate;

import io.reactivestax.exception.FileReadingRuntimeException;
import io.reactivestax.service.Node;
import io.reactivestax.utilities.ConnectionUtil;
import io.reactivestax.utilities.Properties;
import io.reactivestax.utilities.TransactionUtil;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class HibernateUtil {
    private static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<>();

    @Setter
    private static String DEFAULT_RESOURCE = "hibernate.cfg.xml";

    private static io.reactivestax.utilities.database.hibernate.HibernateUtil instance;
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        // private constructor to prevent instantiation
    }

    public static synchronized io.reactivestax.utilities.database.hibernate.HibernateUtil getInstance() {
        return Optional.ofNullable(instance).orElseGet(() -> instance = new io.reactivestax.utilities.database.hibernate.HibernateUtil());
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();
                configuration.configure(io.reactivestax.utilities.database.hibernate.HibernateUtil.DEFAULT_RESOURCE);

                sessionFactory = configuration.buildSessionFactory();
            } catch (FileReadingRuntimeException e) {
                throw new FileReadingRuntimeException("Unable to read file.");
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Properties applicationPropertiesUtils = Properties.getInstance();
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", applicationPropertiesUtils.getDbDriverClass());
        configuration.setProperty("hibernate.connection.url", applicationPropertiesUtils.getDbUrl());
        configuration.setProperty("hibernate.connection.username", applicationPropertiesUtils.getDbUsername());
        configuration.setProperty("hibernate.connection.password", applicationPropertiesUtils.getDbPassword());
        configuration.setProperty("hibernate.dialect", applicationPropertiesUtils.getHibernateDialect());
        configuration.setProperty("hibernate.hbm2ddl.auto", applicationPropertiesUtils.getHibernateDBCreationMode());

        return configuration;
    }

//    @Override
    public static Session getConnection() {
        Session session = sessionThreadLocal.get();
        if (session == null || !session.isOpen()) {
            session = getSessionFactory().openSession();
            sessionThreadLocal.set(session);
        }
        return session;
    }

//    @Override
    public static void startTransaction() {
        getConnection().beginTransaction();
    }

//    @Override
    public static void commitTransaction() {
        getConnection().getTransaction().commit();
        closeConnection();
    }

//    @Override
    public void rollbackTransaction() {
        getConnection().getTransaction().rollback();
        closeConnection();
    }

    public static void closeConnection() {
        Session session = sessionThreadLocal.get();
        if (session != null) {
            session.close();
            sessionThreadLocal.remove();
        }
    }
}
