package com.wemerson.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wemerson.cursomc.domain.Categoria;
import com.wemerson.cursomc.repositories.CategoriaRepository;
import com.wemerson.cursomc.services.exceptions.DataIntegrityException;
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
   public void delete(Integer id) {
	   find(id);
	   try {
	        repo.deleteById(id);
       }
	   catch (DataIntegrityViolationException e) {
		   throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
       }
   }
   
   public List<Categoria> findAll() {
	   return repo.findAll();
   }
   
   public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy,String direction) {
	   PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
	   return repo.findAll(pageRequest);
   
}
}
