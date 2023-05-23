package br.tdec.com.intrarest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import br.tdec.com.intrarest.model.empresas.Vertical;
import lombok.Data;

@Data
@Service
public class VerticalServiceImpl implements VerticalService {

	@Autowired
	private final WebClient webClient;
	@Autowired
	private final String token;

	public VerticalServiceImpl(WebClient webClient, String token) {
		this.webClient = webClient;
		this.token = token;
	}

	@Override
	public List<Vertical> findAll() {
		List<Vertical> ret = null;
		try {
			String uri = "http://mcastro.tdec.com.br:8880/api/v1/lists/Verticais?dataSource=empresas&scope=documents";
			String response = webClient.get().uri(uri).header(HttpHeaders.CONTENT_TYPE, "application/json")
					.header(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
					.header(HttpHeaders.CONNECTION, "keep-alive").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
					.retrieve().bodyToMono(String.class).block();
			System.out.println(response);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper = JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
					.build();

			ret = objectMapper.readValue(response, new TypeReference<List<Vertical>>() {
			});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		// TODO Auto-generated method stub
		return null;
	}

}
