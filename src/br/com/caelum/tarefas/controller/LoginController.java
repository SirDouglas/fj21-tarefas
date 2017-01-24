package br.com.caelum.tarefas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.modelo.Usuario;

@Controller
public class LoginController {
       
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario,HttpSession httpSession) {
		System.out.println("olá");
		System.out.println("senha " + usuario.getSenha());
		if (new JdbcUsuarioDao().existeUsuario(usuario)) {
			httpSession.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		return "redirect:loginForm";
		
	}
	

	    
	  @RequestMapping("loginForm")
	  public String loginForm() {
	    return "formulario-login";
	  }
	  
	  @RequestMapping("logout")
	  public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:loginForm";
	  }
	
}
