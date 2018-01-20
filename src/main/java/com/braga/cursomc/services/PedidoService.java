package com.braga.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braga.cursomc.domain.Pedido;
import com.braga.cursomc.repositories.PedidoRepository;
import com.braga.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		Pedido pedido = pedidoRepository.findOne(id);
		if(pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: [" + id 
					+ "], Tipo: [" + Pedido.class.getName() + "]");
		}
		return pedido;
	}
}
