package br.com.estudo.leostore.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudo.leostore.model.Pedido;
import br.com.estudo.leostore.model.Status;
import br.com.estudo.leostore.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("aguardando")
	public List<Pedido> getPedidosAguardando() {
		
		return pedidoRepository.findByStatus(Status.valueOf(Status.AGUARDANDO.toString().toUpperCase()));
		
	}
	
}
