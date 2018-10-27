package com.web.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.web.store")
public class WebAppConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver  resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	 @Override  
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
	        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
	        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
	        registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/views/images/");
	    }  
	
}
