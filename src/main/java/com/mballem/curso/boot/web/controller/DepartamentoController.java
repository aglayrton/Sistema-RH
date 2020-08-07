package com.mballem.curso.boot.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;
	
	@GetMapping("/cadastrar")//Como a gente tem la no th:object um objeto, o spring mvc espera que exista um objeto no contexto, então
	public String cadastrar(Departamento departamento) {//devemos colocar o objeto departamento no argumento, pois quando o path for acionado
		return "departamento/cadastro";// o objeto departamento é lançado pelo controller para página
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {//a variavel que envia a lista é o model
		model.addAttribute("departamentos", service.buscarTodos());//primeiro o nome da variavelo e o segundo a lista
		return "departamento/lista";
	}
	
	/*Dados que vem com th:action via instancia th:object recebido o dado do th:field*/
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "departamento/cadastro";
		}
		
		service.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")//recupera o id enviado como path
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {//model para listar os dados
		model.addAttribute("departamento", service.buscarPorId(id));//recupero o departamento que vai ser editado
		return "departamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		
		service.editar(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso");
		return "redirect:/departamentos/cadastrar";
	}
	
	/*Não podemos excluir um departamento caso tenha um cargo vinculado a esse departamento*/
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if(service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido, possui cargos vinculado(s)");
		}else {
			service.excluir(id);
			model.addAttribute("success", "Departamento excluido com sucesso");
		}
		return listar(model);
	}
	
}
