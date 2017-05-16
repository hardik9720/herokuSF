package com.ccpoc.ccpoc.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ccpoc.ccpoc")
@EnableTransactionManagement
@Import({SecurityConfig.class})
public class AppConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Bean
    public SessionFactory sessionFactory() throws URISyntaxException {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder
                .scanPackages("com.ccpoc.ccpoc.model")
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }

    @Bean(name = "dataSource")
    public BasicDataSource dataSource() throws URISyntaxException {
        BasicDataSource ds = new BasicDataSource();

        String dbu = null, driver = "", url = "", username = "", password = "";
        Properties prop = new Properties();
        dbu = System.getenv("DATABASE_URL");

        if (dbu == null) {
            ds.setDriverClassName("org.postgresql.Driver");
            ds.setUrl("jdbc:postgresql://localhost:5432/CCPOC");
            ds.setUsername("postgres");
            ds.setPassword("hardik");
            System.out.println("in dataSource method");
        } else {
            URI dbUri = new URI(dbu);
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            url = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + "" + dbUri.getPath() + "?user=" + username + "&password=" + password;
            driver = "org.postgresql.Driver";
            System.out.println("JDBC Url :" + url);
            
            ds.setDriverClassName("org.postgresql.Driver");
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
        }
        return ds;
    }

    @Bean
    public HibernateTransactionManager txManager() throws URISyntaxException {
        return new HibernateTransactionManager(sessionFactory());
    }

}
