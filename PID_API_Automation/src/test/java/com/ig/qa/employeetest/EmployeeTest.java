package com.ig.qa.employeetest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ig.qa.base.TestBase;
import com.ig.qa.commons.Constant;
import com.ig.qa.commons.ExcelUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class EmployeeTest extends TestBase {

	public String requestVerb;
	public Response res_empDetails;
	public int statusCode;
	JSONParser jsonParser = new JSONParser();
	public static String resBody_Status = "status";
	public static String resBody_Message = "message";
	public static String resBody_Age = "age";

	@BeforeClass
	@Parameters({ "url-parameter", "xl-parameter", "excel-version" })
	public void init(String urlParams, String xlPathparams, String excelVersion) {

		url = urlParams;
		xlPath = System.getProperty(Constant.USER_DIR) + xlPathparams + this.getClass().getSimpleName() + excelVersion;

	}

	@Test(dataProvider = "data", dataProviderClass = ExcelUtils.class)
	public void employeeTest1(Map<Object, Object> map) {

		try {
			requestVerb = (String) map.get(Constant.REQUEST_VERB);

			switch (requestVerb) {
			case Constant.REQUEST_GET:
				res_empDetails = super.getCall(map);

				statusCode = res_empDetails.getStatusCode();
				if (statusCode == Constant.STATUS_OK) {
					Assert.assertEquals(statusCode, map.get(Constant.EXPECTED_CODE)); // Assertion 1 : Validate Response
																						// Status code as 200
					res_empDetails.prettyPrint();
					logger.info(map.get(Constant.SCENARIO_NAME) + Constant.SUCCESSFUL_MESSAGE);
				} else {

					logger.info(map.get(Constant.SCENARIO_NAME) + Constant.UNSUCCESSFUL_MESSAGE);
				}

				// Assertion 2 : Validate Response Header for JSON response
				String contentType = response.header("Content-Type");
				Assert.assertEquals(contentType, "application/json; charset=UTF-8");
				break;
			}
		} catch (Exception e) {

			logger.error(Constant.EXCEPTION_MESSAGE + e);
		}

	}

	// Assertion 3 : Validate Response body
	@Test(dataProvider = "data", dataProviderClass = ExcelUtils.class)
	public void employeeTest2(Map<Object, Object> map) {

		try {

			// Get Response Body
			ResponseBody body = res_empDetails.getBody();

			// Get Response Body as String
			String bodyStringValue = body.asString();

			// Get JSON Representation from Response Body
			JsonPath jsonPathEvaluator = response.jsonPath();

			// Get specific element from JSON document
			int status = jsonPathEvaluator.get(resBody_Status);
			String message = jsonPathEvaluator.get(resBody_Message);

			// Validate if the specific JSON element is equal to expected value
			Assert.assertEquals(status, 200);
			Assert.assertTrue(message.equalsIgnoreCase("data retrieved successful"));

			given().get("http://demo4032024.mockable.io/apitest").then().body("employeeData.age", hasItem(25))
					.body("employeeData.role", hasItem("QA Automation Developer"))
					.body("employeeData.dob", hasItem("25-02-1994"));

		} catch (Exception e) {

			logger.error(Constant.EXCEPTION_MESSAGE + e);
		}

	}

	// Assertion 4 : Validate Response body
	@Test(dataProvider = "data", dataProviderClass = ExcelUtils.class)
	public void employeeTest3(Map<Object, Object> map) {

		try {

			given().get("http://demo4032024.mockable.io/apitest").then().body("employeeData.company",
					hasItem("ABC Infotech"));

		} catch (Exception e) {

			logger.error(Constant.EXCEPTION_MESSAGE + e);
		}

	}

}
