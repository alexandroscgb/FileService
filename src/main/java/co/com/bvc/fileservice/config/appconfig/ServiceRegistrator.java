package co.com.bvc.fileservice.config.appconfig;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import co.com.bvc.fileservice.utils.AppConstants;
import co.com.bvc.fileservice.utils.HermesMachine;
import co.com.bvc.serviceregistry.ServiceDTO;

/**
 * 
 * @author Hernan_Quevedo
 *
 */
public interface ServiceRegistrator {
	
	/**
	 * This code executes once per service instance initialization.
	 * @param springEnv Spring environment expressed in the application.yaml file
	 * @param logger 
	 * @return whether or not is successfully initialized
	 * @throws Exception
	 */
	default String registerItself(Environment springEnv, Logger logger) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		sb.append(springEnv.getProperty("sregistry.protocol")).append(springEnv.getProperty("sregistry.host"))
		  .append(springEnv.getProperty("sregistry.port")).append(springEnv.getProperty("sregistry.context"))
		  .append(springEnv.getProperty("sregistry.registerResource"));
		String srServiceUrl = sb.toString();
		
		logger.info("Service register at: " + srServiceUrl);
		
	    String serviceIp = null;
	    InetAddress ip;
		  try {
			ip = InetAddress.getLocalHost();
			serviceIp = ip.getHostAddress();
			logger.info("Current service IP address: " + serviceIp);
		  }catch (UnknownHostException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		  }
	    
	    ServiceDTO thisServiceDTO = new ServiceDTO();
	    thisServiceDTO.setService_active(true);
	    thisServiceDTO.setRegistration_date(Calendar.getInstance().getTime());
	    thisServiceDTO.setService_host_ip(serviceIp);
	    thisServiceDTO.setService_name(AppConstants.THIS_SERVICE_NAME);
	    thisServiceDTO.setService_root_context(AppConstants.SERVICE_ROOT_CONTEXT);
	    thisServiceDTO.setService_port(springEnv.getProperty("server.port"));
	    
	    ResponseEntity<?> response = HermesMachine.consumeRestService(MediaType.APPLICATION_JSON, srServiceUrl,
	    															  HttpMethod.POST,
	    															  String.class, thisServiceDTO);
	    
	    String body = (String) response.getBody();
	    logger.info("Service Registry response: " + body);
		return body;
	}
}