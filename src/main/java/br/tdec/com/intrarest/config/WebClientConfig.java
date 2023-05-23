package br.tdec.com.intrarest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient() {

		WebClient webClient = WebClient.create();
		// Prepare the request body with username and password
		//authenticate(webClient);
		return webClient;
	}
	
	@Bean
	public String token(WebClient webClient) {
		String authorizationHeader = "Basic bWNhc3RybzpIb2RnZSQ0MDQ=";
		String requestBody = "{\"username\": \"" + "mcastro" + "\", \"password\": \"" + "Hodge$404" + "\"}";
		// Make the POST request with Basic Authentication
		String url = "http://mcastro.tdec.com.br:8880/api/v1/auth";
		String token = webClient.post().uri(url).header(HttpHeaders.AUTHORIZATION, authorizationHeader)
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(requestBody)).retrieve()
				.bodyToMono(String.class).block();

		// Store the bearer token for future use
		System.out.println("Bearer Token: " + token);
		return getTokenOnly(token);
	}
	
	public String getTokenOnly(String token) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(token);
			token = jsonNode.get("bearer").asText();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	
//	private void authenticate(WebClient webClient) {
//		String authorizationHeader = "Basic bWNhc3RybzpIb2RnZSQ0MDQ=";
//		String requestBody = "{\"username\": \"" + "mcastro" + "\", \"password\": \"" + "Hodge$404" + "\"}";
//		// Make the POST request with Basic Authentication
//		String url = "http://mcastro.tdec.com.br:8880/api/v1/auth";
//		String token = webClient.post().uri(url).header(HttpHeaders.AUTHORIZATION, authorizationHeader)
//				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(requestBody)).retrieve()
//				.bodyToMono(String.class).block();
//
//		// Store the bearer token for future use
//		System.out.println("Bearer Token: " + token);
//	}
}
