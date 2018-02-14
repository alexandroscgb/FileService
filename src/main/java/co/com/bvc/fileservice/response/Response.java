package co.com.bvc.fileservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Estructura general de respuesta que se enviará al cliente
 * 
 * @author Diego_Babativa
 *
 */
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Response {
	private Integer code;
	private String message;
	private String accessToken;
	private String resource;
}