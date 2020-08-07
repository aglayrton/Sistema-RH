package com.mballem.curso.boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.FuncionarioDao;
import com.mballem.curso.boot.domain.Funcionario;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

	@Autowired
	FuncionarioDao dao;
	
	@Override @Transactional(readOnly = false)
	public void salvar(Funcionario funcionario) {
		// TODO Auto-generated method stub
		dao.save(funcionario);
	}

	@Override @Transactional(readOnly = false)
	public void editar(Funcionario funcionario) {
		// TODO Auto-generated method stub
		dao.update(funcionario);
	}

	@Override @Transactional(readOnly = false)
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}
 
	@Override @Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override @Transactional(readOnly = true)
	public List<Funcionario> buscarPorNome(String nome) {
		return dao.findByNome(nome);
	}

	@Override
	public List <Funcionario> buscarPorCargo(long id) {
		// TODO Auto-generated method stub
		return dao.findByCargo(id);
	}

	@Override
	public List<Funcionario> buscarPorData(LocalDate entrada, LocalDate saida) {
		if(entrada != null && saida != null) {
			return dao.findByData(entrada, saida);
		}else if(entrada != null) {
			return dao.findByDataEntrada(entrada);
		}else if(saida != null) {
			return dao.findByDataEntrada(saida);
		}else {
			return new ArrayList<>();// se o usuario nao tiver selecionado nada, ele vai retornar uma lista vazia
		}
	}

}
