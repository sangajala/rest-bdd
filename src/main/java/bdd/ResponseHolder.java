package bdd;


import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;

public class ResponseHolder {

	public static Response response;
	public static int responseCode;
	public static String responseBody;
	public static Headers responseHeaders;
	
	public static void setResponse(Response response) {
		ResponseHolder.response = response;
	}

	public static Response getResponse() {
		return response;
	}
	
	public static int getResponseCode() {
		
		responseCode = response.getStatusCode();
		return responseCode;
	}
	
	public static String getResponseBody() {
		
		responseBody = response.asString();
		return responseBody;
	}

	public static Headers getResponseHeaders() {
		
		responseHeaders = response.getHeaders();
		return responseHeaders;
	}
	
}
