package com.mballem.curso.boot.domain;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long> {
	
	/*########################################################################################################*/
	
	@NotBlank
	@Size(max = 255, min = 3)
	@Column(nullable = false, unique = true)		
	private String nome;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")//Currency é tipo moeda, pattern é um padrão americano
	@Column(columnDefinition = "DECIMAL(7,2) DEFAULT 0.00") //defini o tipo de dados no mysql e o dado padrão
	private BigDecimal salario;
	
	@NotNull
	@PastOrPresent(message = "{PastOrPresent.funcionario.dataEntrada}")
	@DateTimeFormat(iso = ISO.DATE)//Aqui é o formato de como será salvo
	@Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_saida", nullable = true, columnDefinition = "DATE")
	private LocalDate dataSaida;
	
	/*########################################################################################################*/
	@Valid//estou dizendo que esse objeto deve ser validado conforme as instruções que tenho na classe Endereco
	@OneToOne(cascade = CascadeType.ALL)//cascade diz que se alterarmos funcionario também alteraremos endereco
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@NotNull(message = "{NotNull.funcionario.cargo}")
	@ManyToOne
	@JoinColumn(name = "cargo_id_fk")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}
	
	/*########################################################################################################*/
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
	
}
