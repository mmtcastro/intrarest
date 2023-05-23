package br.tdec.com.intrarest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public abstract class AbstractModelDoc {
	@JsonProperty("@unid")
	// @JsonIgnoreProperties(ignoreUnknown = true)
	private String unid;
	@JsonProperty("@noteid")
	// @JsonIgnoreProperties(ignoreUnknown = true)
	private Integer noteid;
	@JsonProperty("@index")
	// @JsonIgnoreProperties(ignoreUnknown = true)
	private String index;
	@JsonProperty("Id")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String id;
	// @JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("Criacao")
	private Date criacao;
	// @JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("Autor")
	private String autor;

	// @JsonProperty("Codigo")
	private String codigo;

	// @JsonProperty("Descricao")
	private String descricao;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("Status")
	private String status;

}
