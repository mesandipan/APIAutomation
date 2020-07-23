package com.ig.qa.commons;

public class Constant {

	// To fetch User Directory
	public static final String USER_DIR = "user.dir";

	// Column Names fetch from ExcelSheet
	public static final String REQUEST_BODY = "RequestBody";
	public static final String API_ENDPOINT = "APIEndpoint";
	public static final String EXPECTED_CODE = "ExpectedCode";
	public static final String SCENARIO_NAME = "ScenarioName";
	public static final String REQUEST_VERB = "RequestVerb";
	public static final String TC_ID = "TCID";
	public static final String HEADER = "Header";
	public static final String RESPONSE_BODY = "ResponseBody";

	// Common Exceptions
	public static final String ILLEGAL_EXCEPTION = "Error while fetching data";
	public static final String EXCEPTION_MESSAGE = "Exception Occurred";
	public static final String IO_EXCEPTION = "IOException Occurred";
	public static final String FILE_EXCEPTION = "File Not Found";
	public static final String SUCCESSFUL_MESSAGE = " test case has been Successful";
	public static final String UNSUCCESSFUL_MESSAGE = " test case has been failed";
	public static final String NULL_POINTER_EXCEPTION = "Failed due to null pointer exception";
	public static final String JSON_EXCEPTION = "Failed due to JSON exception";

	// Request Verbs fetch from ExcelSheet
	public static final String REQUEST_GET = "get";

	// Standard HTTP Status Code
	public static final int STATUS_OK = 200;
	public static final int STATUS_CREATED = 201;
	public static final int STATUS_ACCEPTED = 202;
	public static final int STATUS_NO_CONTENT = 204;
	public static final int STATUS_BAD_REQUEST = 400;
	public static final int STATUS_UNAUTHORIZED = 401;
	public static final int STATUS_FORBIDDEN = 403;
	public static final int STATUS_NOT_FOUND = 404;
	public static final int STATUS_NOT_ALLOWED = 405;
	public static final int STATUS_CONFLICT = 409;
	public static final int STATUS_INT_SERV_ERROR = 500;
	public static final int STATUS_BAD_GATEWAY = 502;

}
