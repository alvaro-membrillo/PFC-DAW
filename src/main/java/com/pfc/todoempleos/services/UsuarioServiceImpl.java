package com.pfc.todoempleos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfc.todoempleos.model.Usuario;
import com.pfc.todoempleos.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepo;
	
	@Override
	public List<Usuario> getUsuarios() {
		
		return userRepo.findAll();
	}
	
	@Override
	public Usuario insertUsuario(Usuario usuario) {
		
		if (usuario!=null) {
			
			return userRepo.save(usuario);
		}
		
		return null;
	}

	
}
