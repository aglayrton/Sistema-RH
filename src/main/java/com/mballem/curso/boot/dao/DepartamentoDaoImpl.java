package com.mballem.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.Departamento;

//Essa anotação serve para que essa classe seja um bean gerenciado pelo spring
@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao{

}
