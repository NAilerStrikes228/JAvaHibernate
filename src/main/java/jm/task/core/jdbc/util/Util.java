package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

        private static Connection connection = null;

        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ass", "root", "Qwerty228__");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static Connection getConnection() {
            return connection;
        }

        ////////////////////////////////Hibernate///////////////////////////////////////////////

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://127.0.0.1:3306/ass";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Qwerty228__";
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getConnection2() {

        try {
            Configuration configuration2 = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", HOST)
                    .setProperty("hibernate.connection.username", LOGIN)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .addAnnotatedClass(User.class);

            ServiceRegistry sr = new StandardServiceRegistryBuilder()
                    .applySettings(configuration2.getProperties()).build();
            sessionFactory = configuration2.buildSessionFactory(sr);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static void closeConnection() {
        if (sessionFactory != null)
            sessionFactory.close();
    }




}
