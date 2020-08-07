package com.mballem.curso.boot.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {
	
	/*########################################################################################################*/
	@NotBlank(message = "Nome do cargo é obrigatório.")
	@Size(max = 60, message = "O nome do cargo deve conter no máximo 60 caracteres")
	@Column(nullable = false, unique = true, length = 60)
	private String nome;
	
	/*########################################################################################################*/
	@NotNull(message = "Selecione o departamento relativo ao cargo")
	@ManyToOne
	@JoinColumn(name = "id_departamento_fk")//nome da chave estrangeira na junção das duas entidades
	private Departamento departamento;//chave estrangeira
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;
	
	/*########################################################################################################*/
	
	/*GETTERS E SETTERS*/
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
}
