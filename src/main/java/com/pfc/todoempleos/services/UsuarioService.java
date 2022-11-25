package com.pfc.todoempleos.services;

import java.util.List;

import com.pfc.todoempleos.model.Usuario;

public interface UsuarioService {
	
	public List<Usuario> getUsuarios();
	public Usuario insertUsuario(Usuario usuario);
	
}
