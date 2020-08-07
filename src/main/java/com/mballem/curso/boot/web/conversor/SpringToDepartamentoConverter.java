package com.mballem.curso.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.service.DepartamentoService;


//recebemos um string, e o tipo da classe que deve ser convertida na interface generica
@Component
public class SpringToDepartamentoConverter implements Converter<String, Departamento> {
	
	//injeto 
	@Autowired
	DepartamentoService service;
	
	//verificamos se esta vazio, caso nao esteja, passamos para long o text que vem la da th:value 
	@Override
	public Departamento convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

}
