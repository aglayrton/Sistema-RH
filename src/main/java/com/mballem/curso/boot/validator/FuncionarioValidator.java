package com.mballem.curso.boot.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mballem.curso.boot.domain.Funcionario;

public class FuncionarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// Valida o objeto que voce esta enviando a partir do formulario com o objeto que realmaente essa classe deve validar
		return Funcionario.class.equals(clazz);//objeto que temos la no formulario
	}

	@Override
	public void validate(Object object, Errors errors) {
		// object é o objeto que vc recebe do formulario, o errors é o objeto que voce vai lidar com a validacao
		Funcionario f = (Funcionario) object;
		
		LocalDate entrada = f.getDataEntrada();
		
		if(f.getDataSaida() != null) {
			if(f.getDataSaida().isBefore(entrada)) {
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
	}

}
