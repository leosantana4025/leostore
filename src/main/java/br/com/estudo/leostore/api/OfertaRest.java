package br.com.estudo.leostore.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudo.leostore.dto.RequisicaoNovaOferta;
import br.com.estudo.leostore.model.Oferta;
import br.com.estudo.leostore.model.Pedido;
import br.com.estudo.leostore.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(RequisicaoNovaOferta requisicaoNovaOferta) {
		
		Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(requisicaoNovaOferta.getPedidoId());
		
		if (!pedidoEncontrado.isPresent() ) {
			return null;
		}
		
		Pedido pedido = pedidoEncontrado.get();
		
		Oferta oferta = requisicaoNovaOferta.toOferta();
		oferta.setPedido(pedido);
		pedido.getOfertas().add(oferta);
		
		pedidoRepository.save(pedido);
		
		return oferta;
		
	}
	
}
