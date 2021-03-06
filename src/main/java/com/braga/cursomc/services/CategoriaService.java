package com.braga.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braga.cursomc.domain.Categoria;
import com.braga.cursomc.repositories.CategoriaRepository;
import com.braga.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Categoria categoria = categoriaRepository.findOne(id);
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: [" + id 
					+ "], Tipo: [" + Categoria.class.getName() + "]");
		}
		return categoria;
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
}
