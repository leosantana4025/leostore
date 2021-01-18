package br.com.estudo.leostore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.estudo.leostore.dto.RequisicaoNovoPedido;
import br.com.estudo.leostore.model.Pedido;
import br.com.estudo.leostore.model.User;
import br.com.estudo.leostore.repository.PedidoRepository;
import br.com.estudo.leostore.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {
		
		return "pedido/formulario";
		
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "pedido/formulario";
			
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userRepository.findByUsername(username);
		
		Pedido pedido = requisicaoNovoPedido.toPedido();
		pedido.setUser(user);
		
		pedidoRepository.save(pedido);
		
		return "redirect:/home";
		
	}
	
}
