package br.tdec.com.intrarest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "restapi.properties")
public class RestApiProperties {
	@NotNull
	@NotEmpty
	private String baseUrl;
	@NotNull
	@NotEmpty
	private String basicAuthentication;
	@NotNull
	@NotEmpty
	private String u;
	@NotNull
	@NotEmpty
	private String p;

}
