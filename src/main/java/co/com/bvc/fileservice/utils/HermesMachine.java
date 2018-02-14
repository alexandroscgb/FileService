/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.bvc.fileservice.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Clase utilitaria, en honor a Hermes, el dios de la transici�n y los l�mites, mensajero de los dioses.
 * @author Hernan_Quevedo
 */
public class HermesMachine {
        /**
     * Get the method name for a depth in call stack. <br />
     * Utility function
     * @param depth depth in the call stack (0 means current method, 1 means call method, ...)
     * @return method name
     */
    public static String getMethodName(final int depth)
    {
      final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

      //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
      // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
      return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
    }
    
    public static boolean isThereNull(String... vars)
    {
    	for(String var : vars)
    		if(var == null)
    			return true;
    	return false;
    }
    
    /**
     * Consume un servicio web RESTful
     * @param headerContentType
     * @param serviceUrl
     * @param method
     * @param responseType
     * @param httpEntitty
     * @return
     */
    public static <T> ResponseEntity<?> consumeRestService(MediaType headerContentType, String serviceUrl, 
			 HttpMethod method, Class<T> responseType, Object httpEntitty)
    {
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(headerContentType);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> httpEntity = new HttpEntity<>(httpEntitty, headers);
		return restTemplate.exchange(serviceUrl , method, httpEntity, responseType);		
	}
}