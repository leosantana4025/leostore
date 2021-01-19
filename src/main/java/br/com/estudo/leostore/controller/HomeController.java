package br.com.estudo.leostore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.estudo.leostore.model.Pedido;
import br.com.estudo.leostore.model.Status;
import br.com.estudo.leostore.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public ModelAndView home(Model model, Principal principal) {
		
		List<Pedido> pedidos = pedidoRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		modelAndView.addObject("pedidos", pedidos);
		
		return modelAndView;
	
	}
	
	@GetMapping("/{status}")
	public ModelAndView status(@PathVariable("status") String status, Model model) {
		
		Sort sort = Sort.by("dataEntrega").descending();
		
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(Status.valueOf(status.toUpperCase()), paginacao);
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		modelAndView.addObject("pedidos", pedidos);
		modelAndView.addObject("status", status);
		
		return modelAndView;
	
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String statusErro() {
		
		return "redirect:/home";
	
	}

}
