package com.wemerson.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wemerson.cursomc.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	List<Categoria> findByNome(String string);

	List<Categoria> findByNomeContaining(String string);

}
