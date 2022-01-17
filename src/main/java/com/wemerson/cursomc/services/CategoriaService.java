package com.wemerson.cursomc.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemerson.cursomc.domain.Categoria;
import com.wemerson.cursomc.repositories.CategoriaRepository;
import com.wemerson.cursomc.services.exceptions.ObjectNotFoundExceptions;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundExceptions(
		"Objeto não encontrado! Id: " + id 
		+ ", Tipo: " + Categoria.class.getName());
	}
	return obj.orElse(null); 
	}

   public Categoria insert(Categoria obj) {
	   obj.setId(null);
	   return repo.save(obj);
}
   
   public Categoria update(Categoria obj) {
	   find(obj.getId());
	   return repo.save(obj);
   }
   
   }
