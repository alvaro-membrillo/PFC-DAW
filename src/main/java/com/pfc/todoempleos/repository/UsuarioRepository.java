package com.pfc.todoempleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfc.todoempleos.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByUserName(String userName);
	public Usuario findByEmail(Usuario usuario);
	
}
