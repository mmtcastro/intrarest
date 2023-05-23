package br.tdec.com.intrarest.service;

import java.util.List;

import br.tdec.com.intrarest.model.empresas.Vertical;

public interface VerticalService {

	public List<Vertical> findAll();
	public String findById();
	public String delete();
	public String save();
	
}
