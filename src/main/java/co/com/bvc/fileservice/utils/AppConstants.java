package co.com.bvc.fileservice.utils;

/**
 * Clase que contiene los valores constantes de la aplicación
 * 
 * @author Diego_Babativa
 * @version 1.0
 * @since 23/05/2017
 *
 */
public interface AppConstants {
	public static final String UNIX_OP_FILE_PATH = "/var/www/html/see_files/operations/idOp/files/";
	public static final String UNIX_LOGO_PATH_SUFFIX ="logo/";
	public static final String UNIX_REPORTS_PATH_SUFFIX ="reports/";
	public static final String WIN_OP_FILE_PATH = "C:\\xampp\\htdocs\\see_files\\operations\\idOp\\files\\";
	public static final String WIN_LOGO_PATH_SUFFIX = "logo\\";
	public static final String WIN_REPORTS_PATH_SUFFIX = "reports";
	public static final String FILE_UPLOAD_PATH = "/uploadFile";
	public static final String SERVICE_ROOT_CONTEXT = "/fileservice";
	public static final String FILE_UPLOAD_REST_API = SERVICE_ROOT_CONTEXT + FILE_UPLOAD_PATH;
	public static final String THIS_SERVICE_NAME = "FileService";
}