package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	
	public static RequestSpecification req;//use the single instance through out the programm
	public RequestSpecification requestSpecification() throws IOException
	{
		
		//In .feature file we are running two TC's ata a time so after execution of one TC other TC is not logging
		//so we have to follow if condition
		
		if(req==null)
		{
		
		//To create a new file at run time,in java there is a class called FileOutputStream
		PrintStream log= new PrintStream(new FileOutputStream("logging.text"));
		
		req=new RequestSpecBuilder()
				.setBaseUri(getGlobalvariables("baseUrl"))
				.addQueryParam("key", "qaclick123")
				//RequestLoggingFilter: this filter will apply to your object (req) so that your object will have knowledge on logging and it will log every thing,
				//RequestLoggingFilter.logRequestTo(PrintStream stream)
				//so we have to create a object for PrintStream
				.addFilter(RequestLoggingFilter.logRequestTo(log))// to log request into the file logging.text
				.addFilter(ResponseLoggingFilter.logResponseTo(log))// to log response into the file logging.text
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalvariables(String key) throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream("./src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		 
	}
	
	public static String getJsonPath(Response response,String key)
	{
		String respons=response.asString();
		JsonPath js= new JsonPath(respons);
		return js.get(key).toString();
	}

}
