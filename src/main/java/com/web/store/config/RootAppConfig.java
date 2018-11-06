package com.web.store.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class RootAppConfig {		//RootApplicationContext_JavaConfig   
													//	Web版IoC容器，定義所有子系統共用的bean
/*
 *  <bean id="dataSourceC3P0" 
      class="com.mchange.v2.c3p0.ComboPooledDataSource" destroy-method='close'>
      <property name="driverClass" value="com.mysql.jdbc.Driver"/>
      <property name="jdbcUrl" 
          value="jdbc:mysql://localhost:3306/jspdb?useUnicode=true&amp;characterEncoding=utf8"/>
      <property name="user" value="root"/>
      <property name="password" value="password"/>
   </bean>
 * 
 * <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
   		<property name="dataSource" ref="dataSourceC3P0"/>
   		<property name="packagesToScan" value="_01_register.model, _03_listBooks.model, _04_ShoppingCart"/>
   		<property name="configLocation" value="classpath:hibernate.cfg.xml"/>
   	</bean>
   	
   	<bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
   		<property name="sessionFactory" ref="sessionFactory"/>
   	</bean>
 * 
 * 
 *   <property name="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</property>
        <property name="hbm2ddl.auto">update</property>
        <property name="show_sql">true</property>
        <property name="format_sql">true</property>
        
 */
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource source = new ComboPooledDataSource();
		source.setUser("root");
		source.setPassword("password");
		try {
			source.setDriverClass("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		source.setJdbcUrl("jdbc:mysql://localhost:3306/star_up?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
		source.setInitialPoolSize(4);
		source.setMaxPoolSize(8);
		return source;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory () {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan(new String[] {"com.web.store"});
		factory.setHibernateProperties(additionalProperties());
		return factory;
	}

	private Properties additionalProperties() {
		Properties p = new Properties();
		p.put("hibernate.dialect", org.hibernate.dialect.MySQL55Dialect.class);
		p.put("hbm2ddl.auto", "update");
		p.put("show_sql", Boolean.TRUE);
		p.put("format_sql", Boolean.TRUE);
		p.put("default_batch_fetch_size", 10);
		return p;
	}
	
	@Bean(name="transactionManager")
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}	
}
