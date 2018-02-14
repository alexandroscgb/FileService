package co.com.bvc.fileservice.config.appconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Clase que configura un Interceptor: Se utiliza para centralizar todas las
 * peticiones y ser el centro de autorización a los recursos
 * 
 * @author Diego_Babativa
 *
 */
@Configuration
@EnableWebMvc
public class FSAuthorizatorConfiguration extends WebMvcConfigurerAdapter{

}
