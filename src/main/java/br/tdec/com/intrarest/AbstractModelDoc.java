package br.tdec.com.intrarest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public abstract class AbstractModelDoc {
	 @JsonProperty("@unid")
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private String unid;
	 @JsonProperty("@noteid")
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private String noteid;
	 @JsonProperty("@index")
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private String index;
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private String id;
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private Date criacao;
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private String autor;
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private String codigo;
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private String descricao;
	 @JsonIgnoreProperties(ignoreUnknown = true)
	private String status;

}
