package by.goncharov.nc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ivan on 19.05.2017.
 */


@Configuration
@PropertySource("classpath:properties.xml")
public class DataAccessConfig {

    @Autowired
    private Environment properties;

    @Bean
    public SessionFactory sessionFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        try {
            sessionFactory.afterPropertiesSet(); // создание фактори
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory.getObject();
    }

    @Bean
    DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();

        dataSourceConfig.setDriverClassName(properties.getProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(properties.getProperty("db.url"));
        dataSourceConfig.setUsername(properties.getProperty("db.username"));
        dataSourceConfig.setPassword(properties.getProperty("db.pass"));

        return new HikariDataSource(dataSourceConfig);
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto",
                        properties.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.dialect",
                        properties.getProperty("hibernate.dialect"));
                setProperty("hibernate.show_sql",
                        properties.getProperty("hibernate.show_sql"));
            }
        };
    }



    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Autowired
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        return dataSourceInitializer;
    }

}
