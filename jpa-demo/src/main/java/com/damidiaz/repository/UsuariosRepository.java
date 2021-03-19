package com.damidiaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damidiaz.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
