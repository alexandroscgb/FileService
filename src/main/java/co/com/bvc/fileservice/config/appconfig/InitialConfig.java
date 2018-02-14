package co.com.bvc.fileservice.config.appconfig;


import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import co.com.bvc.fileservice.services.FileService;

@Configuration
public class InitialConfig extends SpringBootServletInitializer {

	/**
	 * logger de Log4j2 Apache
	 */
	private static final Logger logger = LogManager.getLogger(InitialConfig.class);

	/**
	 * variable de acceso a los recursos Bundle de Mensajes de la aplciación.
	 */
	private static MessageSource mrc = MessageSourceConfig.messageSource();
	
	@Autowired
	private FileService fservice;
	
	@Autowired
	private Environment springEnv;


	/**
	 * Método que inicializa la carga de configuración inicial del sistema
	 */
	@PostConstruct
	public void init() {
		initializeConfigFS();
		try {
			registerItself();
		} catch (Exception e) {
			logger.error("Excepción registrando el servicio en el Service Registry\n"+e.getLocalizedMessage());
			e.printStackTrace();
			return;
		}
	}
	/**
	 * Carga la configuración inicial de sistema, como validación por IP,
	 * conexión con servidor GLuu
	 */
	public void initializeConfigFS() {
		logger.info(mrc.getMessage("see.initialize.config.info.begin", null, null));
		
	}
	
	public String registerItself() throws Exception {
		logger.info("Service about to register itself to the Service Registry: " + fservice);
		fservice.registerItself(springEnv, logger);
		return null;
	}		
}