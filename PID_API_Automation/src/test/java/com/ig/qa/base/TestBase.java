package com.ig.qa.base;

import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.ig.qa.commons.Constant;
import com.ig.qa.commons.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	protected final static Logger logger = Logger.getLogger(TestBase.class);

	public static RequestSpecification httpRequest;
	public static Response response;
	public static String url;
	public static String xlPath;
	public static String ApiEndpoint;
	public static Map<String, String> headersMap;

	/*
	 * This method is used for Initializing Log generally
	 * 
	 */
	@BeforeClass
	@Parameters({ "log-level" })
	public void logInit(String logLevel) {

		logger.setLevel(Level.toLevel(logLevel));

	}

	/*
	 * This generic method is used for Get Call
	 * 
	 * @params map=It Contains Key(ColumeNames) and Values(Cell Values).
	 */
	public Response getCall(Map<Object, Object> map) {

		try {

			RestAssured.baseURI = url;
			ApiEndpoint = (String) map.get(Constant.API_ENDPOINT);
			headersMap = Utils.stringToMap((String) map.get(Constant.HEADER));

			httpRequest = RestAssured.given();

			response = httpRequest.when().get(ApiEndpoint);

		} catch (IllegalArgumentException e) {
			logger.error(Constant.ILLEGAL_EXCEPTION + e);
		}

		catch (Exception e) {
			logger.error(Constant.EXCEPTION_MESSAGE + e);
		}
		return response;
	}

}
