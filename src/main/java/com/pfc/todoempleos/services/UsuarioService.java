package com.pfc.todoempleos.services;

import java.util.List;
import java.util.Optional;

import com.pfc.todoempleos.dto.UsuarioDTO;
import com.pfc.todoempleos.model.Usuario;

public interface UsuarioService {
	
	public Optional<Usuario> findUserById(Long id);
	public Optional<Usuario> getUserByName(String name);
	public List<Usuario> getUsuarios();
	public Usuario insertUsuario(Usuario usuario);
	public Usuario updateUsuario(UsuarioDTO usuario);
	public void deleteUsuario(Long id);
	
}
