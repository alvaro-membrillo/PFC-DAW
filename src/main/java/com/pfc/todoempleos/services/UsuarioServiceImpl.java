package com.pfc.todoempleos.services;

import java.util.List;
import java.util.Optional;

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

		if (usuario != null) {

			return userRepo.save(usuario);
		}

		return null;
	}

	@Override
	public Optional<Usuario> getUserById(Long id) {
		
		Optional<Usuario> usuario = userRepo.findById(id);
		if (usuario.get().getId() != null) {
			return usuario;
		}
		
		return null;
	}

	@Override
	public Optional<Usuario> getUserByName(String name) {
		
		//Optional<Usuario> usuario = Optional.of(userRepo.findByUserName(name));
		Optional<Usuario> usuario = Optional.ofNullable(userRepo.findByUserName(name));
		if (usuario.get().getUserName() != null || usuario.isEmpty()) {
			return usuario;
		}
		
		return null;
	}

}
