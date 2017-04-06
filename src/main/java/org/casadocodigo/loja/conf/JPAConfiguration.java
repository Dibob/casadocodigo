package org.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
@EnableTransactionManagement
// @PropertySource({ "classpath:config.properties" })
//@EnableJpaRepositories("org.casadocodigo.loja.daos")
//@ComponentScan({ "org.casadocodigo.loja" })
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		factoryBean.setJpaVendorAdapter(vendorAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("33838449");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_estudo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		factoryBean.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");

		factoryBean.setJpaProperties(props);

		factoryBean.setPackagesToScan("org.casadocodigo.loja.models");

		return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}


	
	// @Autowired
	// private Environment env;
	//
	// @Bean
	// public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	// throws NamingException {
	// final LocalContainerEntityManagerFactoryBean em = new
	// LocalContainerEntityManagerFactoryBean();
	// em.setDataSource(dataSource());
	// em.setPackagesToScan("org.casadocodigo.loja.models");
	// em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	// em.setJpaProperties(additionalProperties());
	//
	// return em;
	// }
	//
	// @Bean
	// public DataSource dataSource() throws NamingException {
	// return (DataSource) new
	// JndiTemplate().lookup(env.getProperty("jdbc.url"));
	// }
	//
	// @Bean
	// public JpaTransactionManager transactionManager(EntityManagerFactory
	// emf){
	// return new JpaTransactionManager(emf);
	// }

	 @Bean
	 public PersistenceExceptionTranslationPostProcessor
	 exceptionTranslation() {
	 return new PersistenceExceptionTranslationPostProcessor();
	 }
	//
	// final Properties additionalProperties() {
	// final Properties hibernateProperties = new Properties();
	// hibernateProperties.setProperty("hibernate.hbm2ddl.auto",
	// env.getProperty("hibernate.hbm2ddl.auto"));
	// hibernateProperties.setProperty("hibernate.dialect",
	// env.getProperty("hibernate.dialect"));
	// hibernateProperties.setProperty("hibernate.cache.use_second_level_cache",
	// "false");
	// return hibernateProperties;
	// }
}