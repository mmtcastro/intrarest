package br.tdec.com.intrarest;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public abstract class AbstractModelLista<T> {

	private List<T> lista;

	public AbstractModelLista() {
		super();
		this.lista = new ArrayList<T>();
	}
	
	
}
