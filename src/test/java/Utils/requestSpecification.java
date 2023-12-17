package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class requestSpecification

{
	public static RequestSpecification addPlaceBaseRequest;
	public RequestSpecification requestSpec() throws IOException
	{
		if (addPlaceBaseRequest==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logfile.txt"));
		addPlaceBaseRequest = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseURI")).
				addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)) //should be part of request payload ?
				.setContentType(ContentType.JSON).build();
		
		return addPlaceBaseRequest;
	}
		return addPlaceBaseRequest;
	}
	
	public String getGlobalProperty(String globalProperty) throws IOException
	{
		Properties prop  = new Properties();
		FileInputStream fileinput = new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\RestAssuredFramework\\src\\test\\java\\Constants\\global.properties");
		prop.load(fileinput);
		return prop.getProperty(globalProperty);
	}
	
	public String getJsonParse(Response response, String keyValue)
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(keyValue).toString();
	}
	
}
