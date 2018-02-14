package co.com.bvc.fileservice.services;

import org.springframework.web.multipart.MultipartFile;

import co.com.bvc.fileservice.config.appconfig.ServiceRegistrator;
import co.com.bvc.fileservice.response.Response;

public interface FileService extends ServiceRegistrator {
	/**
	 * Creates a copy of the file uploaded from the frontend
	 * @param file
	 * @param fileType
	 * @param idOp
	 * @return
	 */
	public Response createFile(MultipartFile file, String fileType, String idOp, String fileConsecutive);
}