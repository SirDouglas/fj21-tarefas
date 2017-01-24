package br.com.caelum.tarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.modelo.Tarefa;

@Controller
public class TarefasController {
	
	 private final JdbcTarefaDao dao;
	 
	 public TarefasController(JdbcTarefaDao dao) {
		// TODO Auto-generated constructor stub
		 this.dao = dao; 
	}

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
		
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa,BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		

		dao.adiciona(tarefa);
		return "tarefa/adicionada";
		
	}
	
	@RequestMapping("listaTarefa")
	public String lista (Model model) {
		model.addAttribute("tarefas", dao.lista());
		return "tarefa/lista";
		
	}
	
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		dao.remove(tarefa);
		return "redirect:listaTarefa";
		
	}
	
	@RequestMapping("mostraTarefa")
	public String mostra(Long id,Model model) {
		model.addAttribute("tarefa",dao.pesquisa(id));
		return "tarefa/mostra";
		
	}
	
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		dao.altera(tarefa);
		return "redirect:listaTarefa";
		
	}
	
	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) {
	  System.out.println("olá");
	  dao.finaliza(id);
	 model.addAttribute("tarefa", dao.pesquisa(id));
	 return "tarefa/finalizada";
	}
	
}
