package co.com.bvc.fileservice.services.impl;

import static co.com.bvc.fileservice.utils.AppConstants.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import co.com.bvc.fileservice.response.Response;
import co.com.bvc.fileservice.services.FileService;

/**
 * 
 * @author hernan_quevedo
 */
@Component
public class FileServiceImpl implements FileService {

	private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);
	public static String UPLOAD_FOLDER = null;
	public static String OS = null;

	static {
		setOS();
	}

	@Override
	public Response createFile(MultipartFile file, String fileType,
			String idOp, String fileConsecutive) {
		try {
			byte[] bytes = file.getBytes();
			
			StringBuilder sb = new StringBuilder(
					createDirectory(idOp, fileType));
			
			String path = "";

			if (fileType.equalsIgnoreCase("logo")) {
				path = sb.append("file").append(fileConsecutive).append(".png")
						.toString();
			} else {
				path = sb.append("file").append(fileConsecutive).append(".pdf")
						.toString();
			}

			logger.info("File to write: " + path);

			Path patth = Paths.get(path);
			Files.write(patth, bytes);
			sb = null;

		} catch (IOException e) {
			logger.error("Logo file for operation " + idOp
					+ " failed to be created with exception: ");
			logger.error(e.getMessage());
			return new Response(-1,
					"Logo file not created:\n" + e.getMessage(), null, null);
		}
		return new Response(1, "Logo file created successfully", null, null);
	}

	/**
	 * Creates a folder structure based on OS.
	 * 
	 * @param idOp
	 *            Operation ID
	 * @param fileType
	 *            If the file to be uploaded is a logo file for the operation or
	 *            a report file (PDF, XLSX, ZIP)
	 * @throws IOException 
	 */
	private String createDirectory(String idOp, String fileType) throws IOException {
		StringBuilder pathsb = new StringBuilder();

		String realFilePath = UPLOAD_FOLDER.replaceFirst("idOp", idOp);
		pathsb.append(realFilePath);

		if (OS.equals("Windows")) {
			switch (fileType) {
			case "logo":
				pathsb.append(WIN_LOGO_PATH_SUFFIX);
				break;
			case "report":
				pathsb.append(WIN_REPORTS_PATH_SUFFIX);
				break;
			}
		} else {
			switch (fileType) {
			case "logo":
				pathsb.append(UNIX_LOGO_PATH_SUFFIX);
				break;
			case "report":
				pathsb.append(UNIX_REPORTS_PATH_SUFFIX);
				break;
			}
		}
		// Elimina primero el directorio
		deleteDirectory(pathsb.toString());
		
		File dir = new File(pathsb.toString());
		logger.info("Upload folder to be created: " + pathsb.toString());
		pathsb = null;
		pathsb = new StringBuilder("New operation folder for opId ");
		pathsb.append(idOp).append(" created? ").append(dir.mkdirs());
		pathsb = null;
		return dir.getAbsolutePath() + File.separator;
	}

	public void deleteDirectory(String path) throws IOException {

		Path directory = Paths.get(path);

		if (directory.toFile().exists()) {

			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir,
						IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});

		}
	}

	/**
	 * Sets the operative system name
	 */
	private static void setOS() {
		String os = System.getProperty("os.name");

		if (os.startsWith("win") || os.startsWith("Win")
				|| os.startsWith("WIN")) {
			UPLOAD_FOLDER = WIN_OP_FILE_PATH;
			OS = "Windows";
		} else {
			UPLOAD_FOLDER = UNIX_OP_FILE_PATH;
			OS = "Unix";
		}
		os = null;
	}
}