package co.com.bvc.fileservice.restapi;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.com.bvc.fileservice.response.Response;
import co.com.bvc.fileservice.services.FileService;
import co.com.bvc.fileservice.utils.AppConstants;

/**
 * Controlador REST Spring encargado de atender todas las solicitudes de
 * 
 * @author Diego_Babativa
 * @since 17/05/2017
 * @version 1.0.0
 *
 */

@RestController
@RequestMapping(AppConstants.SERVICE_ROOT_CONTEXT)
@Api(value = "FileUpload Controller")
public class FileUploadController {

	@Autowired
	private FileService fs;

	@PostMapping(value = (AppConstants.FILE_UPLOAD_PATH), headers = ("content-type=multipart/*"), produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> uploadFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("idOp") String operationId,
			@RequestParam("fileType") String fileType,
			@RequestParam("fileConsecutive") String fileConsecutive) {
		Response response = fs.createFile(file, fileType, operationId, fileConsecutive);
		if (response.getCode() == -1)
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(
					response);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}