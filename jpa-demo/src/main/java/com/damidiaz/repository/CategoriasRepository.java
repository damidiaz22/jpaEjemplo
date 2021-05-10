package com.damidiaz.repository;


//hola
import org.springframework.data.jpa.repository.JpaRepository;

import com.damidiaz.model.Categoria;
//public interface CategoriasRepository extends CrudRepository<Categoria, Integer>
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
