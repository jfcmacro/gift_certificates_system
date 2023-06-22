package com.epam.esm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.postgresql.Driver;
import javax.sql.DataSource;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.JdbcTagDao;

@Configuration
@ComponentScan(basePackages="com.epam.esm")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver getViewResolver(){
	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	resolver.setPrefix("/WEB-INF/views/");
	resolver.setSuffix(".jsp");
	return resolver;
    }

    @Bean
    public TagDao tagDao() {
	return new JdbcTagDao(dataSource());
    }

    @Bean
    public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName(Driver.class.getName());
	dataSource.setUrl("jdbc:postgresql://localhost:5432/gcsdb");
	dataSource.setUsername("gcsuser");
	dataSource.setPassword("gcspass");
 
	return dataSource;
    }

    // @Bean
    // public View jsonmembertemplate() {
    // 	MappingJackson2JsonView view = new MappingJackson2JsonView();
    // 	view.setPrettyPrint(true);
    // 	return view;
    // }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

	
}
