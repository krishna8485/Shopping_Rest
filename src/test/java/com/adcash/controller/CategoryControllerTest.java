package com.adcash.controller;


import com.adcash.ShoppingApplication;
import com.adcash.model.request.CategoryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
	private int port;


	HttpHeaders headers = new HttpHeaders();


    @Test
    public void category1getAll() throws Exception {

        String expected = "[\n" + 
        		"    {\n" + 
        		"        \"id\": 1,\n" + 
        		"        \"name\": \"Electorics\"\n" + 
        		"    }\n" + 
        		"]";

        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/categories", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }
    
    @Test
    public void category2create() throws Exception {

        String expected = 
        		"    {\n" + 
        		"        \"id\": 2,\n" + 
        		"        \"name\": \"Food\"\n" + 
        		"    }\n" ;
        
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Food");
        
        HttpEntity<CategoryRequest> entity = new HttpEntity<CategoryRequest>(categoryRequest, headers);
		ResponseEntity<String> response = restTemplate
				.withBasicAuth("admin", "password")
				.postForEntity("/category",entity, String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }
    
    @Test
    public void category3find() throws Exception {

        String expected = 
        		"    {\n" + 
        		"        \"id\": 2,\n" + 
        		"        \"name\": \"Food\"\n" + 
        		"    }\n" ;
       
		ResponseEntity<String> response = restTemplate
				.withBasicAuth("admin", "password")
				.getForEntity("/category/2",String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }
    
    
    @Test
    public void category4update() throws Exception {

        String expected = 
        		"    {\n" + 
        		"        \"id\": 2,\n" + 
        		"        \"name\": \"FoodUpdate\"\n" + 
        		"    }\n" ;
        
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("FoodUpdate");
		
		HttpEntity<CategoryRequest> entity = new HttpEntity<CategoryRequest>(categoryRequest, headers);
		ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "password").exchange(
				"/category/2",
				HttpMethod.PUT, entity, String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }
    
    @Test
    public void category5delete() throws Exception {

       restTemplate.withBasicAuth("admin", "password").delete("/category/2");
       
       String expected = "[\n" + 
       		"    {\n" + 
       		"        \"id\": 1,\n" + 
       		"        \"name\": \"Electorics\"\n" + 
       		"    }\n" + 
       		"]";

       ResponseEntity<String> response = restTemplate
               .withBasicAuth("user", "password")
               .getForEntity("/categories", String.class);

       printJSON(response);

       assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
       assertEquals(HttpStatus.OK, response.getStatusCode());

       JSONAssert.assertEquals(expected, response.getBody(), false);

    }
    
    

    @Test
    public void category6UNAUTHORIZED() throws Exception {

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Food");
        
        HttpEntity<CategoryRequest> entity = new HttpEntity<CategoryRequest>(categoryRequest, headers);
		ResponseEntity<String> response = restTemplate
				.withBasicAuth("user", "password")
				.postForEntity("/category",entity, String.class);


        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    }

    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
