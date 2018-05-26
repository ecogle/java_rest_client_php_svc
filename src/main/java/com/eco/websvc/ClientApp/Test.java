package com.eco.websvc.ClientApp;

//import java.text.ParseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Test {

	public static void main(String[] args) {
		
		ClientConfig config = new DefaultClientConfig();
		
		Client client = Client.create(config);
		
		WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8282/testing_websvc").build());
		
		String results = service.path("/product").path("/read.php").accept(MediaType.APPLICATION_JSON).get(String.class);
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(results);
			
			JSONObject jsonobject = (JSONObject) obj;
			
			JSONArray array = (JSONArray)jsonobject.get("records");
			
			array.forEach(System.out::println);
		}
		catch(ParseException pe) {
			System.out.println("position: " + pe.getPosition());
		}
		
	}
}
