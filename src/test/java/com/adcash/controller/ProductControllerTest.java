package com.adcash.controller;

import com.adcash.ShoppingApplication;
import com.adcash.model.request.ProductRequest;
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
public class ProductControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
	private int port;


	HttpHeaders headers = new HttpHeaders();


    @Test
    public void product1getAll() throws Exception {

       String expected = "[{\"id\":1,\"name\":\"B&W Speaker\"},{\"id\":2,\"name\":\"Samsung TV\"}]";

        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/categories/1/products", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }
    
    @Test
    public void product2create() throws Exception {

        String expected = "{\"id\":3,\"name\":\"Iphone7\"}";
        
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Iphone7");
        
        HttpEntity<ProductRequest> entity = new HttpEntity<ProductRequest>(productRequest, headers);
		ResponseEntity<String> response = restTemplate
				.withBasicAuth("admin", "password")
				.postForEntity("/category/1/product",entity, String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);
        
        response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/categories/1/products", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        expected = "[{\"id\":1,\"name\":\"B&W Speaker\"},{\"id\":2,\"name\":\"Samsung TV\"},{\"id\":3,\"name\":\"Iphone7\"}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);

    }
    
    
    @Test
    public void product3update() throws Exception {

        String expected = 
        		"    {\n" + 
        		"        \"id\": 3,\n" + 
        		"        \"name\": \"Iphone7Updated\"\n" + 
        		"    }\n" ;
        
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Iphone7Updated");
		
		HttpEntity<ProductRequest> entity1 = new HttpEntity<ProductRequest>(productRequest, headers);
		ResponseEntity<String> response1 = restTemplate.withBasicAuth("admin", "password").exchange(
				"/category/1/product/3",
				HttpMethod.PUT, entity1, String.class);

        printJSON(response1);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response1.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response1.getStatusCode());

        JSONAssert.assertEquals(expected, response1.getBody(), false);
        
        response1 = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/categories/1/products", String.class);

        printJSON(response1);

    }
    
    @Test
    public void product4delete() throws Exception {

       restTemplate.withBasicAuth("admin", "password").delete("/category/1/product/3");
       
       String expected = "[{\"id\":1,\"name\":\"B&W Speaker\"},{\"id\":2,\"name\":\"Samsung TV\"}]";

       ResponseEntity<String> response = restTemplate
               .withBasicAuth("user", "password")
               .getForEntity("/categories/1/products", String.class);

       printJSON(response);

       assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
       assertEquals(HttpStatus.OK, response.getStatusCode());

       JSONAssert.assertEquals(expected, response.getBody(), false);

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

