package core;

import io.restassured.http.ContentType;

public interface Constantes {
	
	String APP_BASE_URL = "https://inm-api-test.herokuapp.com/"; 
	Integer APP_PORT = 443; 
	String APP_BASE_PATH = "";
	
	ContentType APP_CONTENTE_TYPE = ContentType.JSON;
	Long MAX_TIMEOUT = 50000L;
	
}
