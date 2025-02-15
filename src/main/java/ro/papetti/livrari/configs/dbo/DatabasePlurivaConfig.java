/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.configs.dbo;

import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db-link-pluriva.properties")
@EnableJpaRepositories(basePackages = "${spring.datasource.repozitories.path}", 
        entityManagerFactoryRef = "plurivaEntityManagerFactory", 
        transactionManagerRef = "plurivaTransactionManager")
public class DatabasePlurivaConfig {
    
    @Value("${spring.jpa.properties.hibernate.show_sql}")
    private boolean hibernate_show_sql;
    
    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private boolean hibernate_format_sql;

    @Value("${spring.datasource.hibernate.dialect}")
    private String hibernate_dialect;
    
    @Value("${spring.datasource.packages.to.scan}")
    private String packages_to_scan;
    
//    @Bean(name= "plurivaDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.pluriva")
//    public DataSource plurivaDataSource(){
//        return DataSourceBuilder.create().build();
//    }
    
    @Bean(name= "plurivaDataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource plurivaDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="plurivaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean plurivaEntityManagerFactory(@Qualifier("plurivaDataSource") DataSource dataSource){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(packages_to_scan); // pachetul unde sunt entitățile pentru pluriva  sau setPackagesToScan(new String[] { "com.example.entity.primary" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", hibernate_dialect);
        properties.put("hibernate.show_sql", hibernate_show_sql);
        properties.put("hibernate.format_sql", hibernate_format_sql);

//        properties.put("hibernate.generate_statitics",true);
//        properties.put("org.hibernate.stat","DEBUG");
        
        
        em.setJpaPropertyMap(properties);
        return em;
    }
    
    @Bean(name = "plurivaTransactionManager")
    public PlatformTransactionManager plurivaTransactionManager(
            @Qualifier("plurivaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);

    }

        
       
    
}
