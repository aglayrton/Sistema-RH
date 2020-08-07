package com.mballem.curso.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {
	
	@NotBlank(message = "Informe um nome")
	@Size(min = 3, max = 60, message = "O nome do departamento deve ter entre {min} e {max} caracteres")
	@Column(name = "nome", length = 60, nullable = false, unique = true)
	private String nome;
	
	//mappedBy serve para dizer qual o atributo faz parte do lado forte da relação
	@OneToMany(mappedBy = "departamento")//relacionamento com departamento
	private List<Cargo> cargos;//lado forte é aquele que contem a chave estrangeira, no caso cargo

	
	/*GETTERS E SETTERS*/
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	
}
