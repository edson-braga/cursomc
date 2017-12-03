package com.braga.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braga.cursomc.domain.Cliente;
import com.braga.cursomc.repositories.ClienteRepository;
import com.braga.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		Cliente cliente = clienteRepository.findOne(id);
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: [" + id 
					+ "], Tipo: [" + Cliente.class.getName() + "]");
		}
		return cliente;
	}
}
