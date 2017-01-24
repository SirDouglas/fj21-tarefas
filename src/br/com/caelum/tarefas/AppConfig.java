package br.com.caelum.tarefas;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.caelum.tarefas.interceptor.AutorizadorInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages={"br.com.caelum"})
public class AppConfig extends WebMvcConfigurerAdapter {
	
    @Value("com.mysql.jdbc.Driver")     
    private String driverClassName;
    @Value("jdbc:mysql://localhost/fj21")                 
    private String url;
    @Value("root")             
    private String username;
    @Value("vertrigo")             
    private String password;
    
	  @Override
	  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	  }
	  
	  @Bean
	  public InternalResourceViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/views/");
	    resolver.setSuffix(".jsp");
	
	   
	    return resolver;
	  }
	  
	  @Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		    registry.addInterceptor(new AutorizadorInterceptor());
	}
	  @Bean
	  public DataSource dataSource() {
		  DriverManagerDataSource ds = new DriverManagerDataSource();        
	        ds.setDriverClassName(driverClassName);
	        ds.setUrl(url);
	        ds.setUsername(username);
	        ds.setPassword(password);        
	        return ds;
	  }
	  
	  

}
