package br.com.estudo.leostore.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudo.leostore.dto.RequisicaoNovaOferta;
import br.com.estudo.leostore.model.Oferta;
import br.com.estudo.leostore.model.Pedido;
import br.com.estudo.leostore.repository.OfertaRepository;
import br.com.estudo.leostore.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaRest {
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicaoNovaOferta) {
		
		Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(requisicaoNovaOferta.getPedidoId());
		
		if (!pedidoEncontrado.isPresent() ) {
			return null;
		}
		
		Pedido pedido = pedidoEncontrado.get();
		
		Oferta oferta = requisicaoNovaOferta.toOferta();
		oferta.setPedido(pedido);
		
		ofertaRepository.save(oferta);
		
		return oferta;
		
	}
	
}
