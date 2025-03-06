/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.configs.dbo;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author MariusO
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db-link-livrari.properties")
@EnableJpaRepositories(basePackages = "${spring.datasource.livrari.repozitories.path}", 
        entityManagerFactoryRef = "livrariEntityManagerFactory", 
        transactionManagerRef = "livrariTransactionManager")
public class DatabaseLivrariConfig {
    
    @Value("${spring.jpa.properties.hibernate.show_sql}")
    private boolean show_sql;
    
    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private boolean format_sql;
    
    @Value("${spring.datasource.livrari.hibernate.dialect}")
    private String dialect;
    
    @Value("${spring.datasource.livrari.packages.to.scan}")
    private String packages_to_scan;

    @Value("${spring.datasource.livrari.hibernate.generate_statistics}")
    private String generate_statistics;

    @Value("${hibernate.connection.provider_disables_autocommit}")
    private String provider_disables_autocommit;

    @Primary
    @Bean(name= "livrariDataSource")
    @ConfigurationProperties(prefix="spring.datasource.livrari")
    public DataSource livrariDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setAutoCommit(false);
        return dataSource;
    }
    
    
    @Primary
    @Bean(name="livrariEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean livrariEntityManagerFactory(@Qualifier("livrariDataSource") HikariDataSource dataSource){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan(packages_to_scan); // pachetul unde sunt entitățile de livrari  em.setPackagesToScan(new String[] { "com.example.entity.primary" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", show_sql);
        properties.put("hibernate.format_sql", format_sql);
        // Setările corecte pentru activarea statisticilor
        properties.put("hibernate.generate_statistics", generate_statistics);
        properties.put("hibernate.connection.provider_disables_autocommit",provider_disables_autocommit);

        em.setJpaPropertyMap(properties);

        return em;
    }
    
    @Primary
    @Bean(name = "livrariTransactionManager")
    public PlatformTransactionManager livrariTransactionManager(
            @Qualifier("livrariEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
    
}
