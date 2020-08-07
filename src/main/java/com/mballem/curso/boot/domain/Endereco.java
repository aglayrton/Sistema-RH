package com.mballem.curso.boot.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENDERECOS")
public class Endereco extends AbstractEntity<Long> {
	
	@NotNull
	@Size(min = 3, max = 255)
	@Column(nullable = false)
	private String logradouro;
	
	@NotBlank
	@Size(min = 3, max = 255)
	@Column(nullable = false)
	private String bairro;
	
	@NotBlank
	@Size(min = 3, max = 255)
	@Column(nullable = false)
	private String cidade;
	
	@NotNull(message = "{NotNull.endereco.uf}")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)//vai salvar esse tipo de dados pelo JPA
	private UF uf;
	
	@NotBlank
	@Size(max = 9, min = 9, message = "{Size.endereco.cep}")
	@Column(nullable = false, length = 9)
	private String cep;
	
	@NotNull(message = "{NotNull.endereco.numero}")
	@Digits(integer = 5, fraction = 0)
	@Column(nullable = false, length = 5)
	private Integer numero;
	
	private String complemento;//Não é obrigatório então nao precisamos colocar a anotação e os carcteres serão 255
	
	/*GETTERS E SETTERS*/
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}
