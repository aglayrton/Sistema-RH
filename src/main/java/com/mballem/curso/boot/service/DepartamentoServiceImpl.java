package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.DepartamentoDao;
import com.mballem.curso.boot.domain.Departamento;

@Service
@Transactional(readOnly = true)
public class DepartamentoServiceImpl implements DepartamentoService	{
	
	@Autowired
	DepartamentoDao dao;

	@Override @Transactional(readOnly = false)
	public void salvar(Departamento departamento) {
		dao.save(departamento);
	}

	@Override @Transactional(readOnly = false)
	public void editar(Departamento departamento) {
		dao.update(departamento);
	}

	@Override @Transactional(readOnly = false)
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	public Departamento buscarPorId(Long id) {
			return dao.findById(id);
	}

	@Override
	public List<Departamento> buscarTodos() {
		return dao.findAll();
	}
	
	/*Não podemos excluir um departamento caso tenha um cargo vinculado a esse departamento*/
	@Override
	public boolean departamentoTemCargos(Long id) {
		if(buscarPorId(id).getCargos().isEmpty()) {
			return false;
		}else {
			 return true;
		}
	}

}
