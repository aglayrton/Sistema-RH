package com.mballem.curso.boot.dao;

import java.util.List;

import com.mballem.curso.boot.domain.Departamento;

public interface DepartamentoDao {
	void save(Departamento departamento);
	void update(Departamento departamento);
	void delete(Long id);
	Departamento findById(Long id);
	List<Departamento> findAll();
}
