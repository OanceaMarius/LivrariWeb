/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.configs.dbo;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
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
    private boolean show_sql;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private boolean format_sql;

    @Value("${spring.datasource.hibernate.dialect}")
    private String dialect;

    @Value("${spring.datasource.packages.to.scan}")
    private String packages_to_scan;

    @Value("${spring.datasource.hibernate.generate_statistics}")
    private String generate_statistics;

    @Value("${spring.datasource.provider_disables_autocommit}")
    private String provider_disables_autocommit;

    @Value("${spring.datasource.datasource.hikari.auto-commit}")
    private boolean datasource_hikari_auto_commit;

    @Bean(name = "plurivaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource plurivaDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setAutoCommit(datasource_hikari_auto_commit);
        return dataSource;
    }

    @Bean(name = "plurivaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean plurivaEntityManagerFactory(@Qualifier("plurivaDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(packages_to_scan); // pachetul unde sunt entitățile pentru pluriva  sau setPackagesToScan(new String[] { "com.example.entity.primary" });

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

    @Bean(name = "plurivaTransactionManager")
    public PlatformTransactionManager plurivaTransactionManager(
            @Qualifier("plurivaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);

    }


}
