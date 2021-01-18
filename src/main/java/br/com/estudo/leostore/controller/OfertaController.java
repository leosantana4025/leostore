package br.com.estudo.leostore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.estudo.leostore.repository.PedidoRepository;

@Controller
@RequestMapping("/oferta")
public class OfertaController {
	
	@Autowired
	PedidoRepository pedidoRepository;

	@GetMapping
	public String getFormularioOferta() {
		
		return "oferta/ofertaHome";
		
	}
	
}
