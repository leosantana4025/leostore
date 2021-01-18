package br.com.estudo.leostore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.estudo.leostore.model.Pedido;
import br.com.estudo.leostore.model.Status;
import br.com.estudo.leostore.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("pedido")
	public String home(Model model, Principal principal) {
		
		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
		
		model.addAttribute("pedidos", pedidos);
		
		return "usuario/usuarioHome";
	
	}
	
	@GetMapping("pedido/{status}")
	public String status(@PathVariable("status") String status, Model model, Principal principal) {
		
		List<Pedido> pedidos = pedidoRepository.findByStatusAndUser(Status.valueOf(status.toUpperCase()), principal.getName());
		
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		
		return "usuario/usuarioHome";
	
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String statusErro() {
		
		return "redirect:/usuario/home";
	
	}

}
