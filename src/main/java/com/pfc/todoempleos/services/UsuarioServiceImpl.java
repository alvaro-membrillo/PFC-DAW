package com.pfc.todoempleos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfc.todoempleos.dto.UsuarioDTO;
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
	public Optional<Usuario> findUserById(Long id) {

		if (id != null) {
			return userRepo.findById(id);
		}

		return null;
	}

	@Override
	public Optional<Usuario> getUserByName(String name) {

		// Optional<Usuario> usuario = Optional.of(userRepo.findByUserName(name));
		Optional<Usuario> usuario = Optional.ofNullable(userRepo.findByUserName(name));
		if (usuario.get().getUserName() != null || usuario.isEmpty()) {
			return usuario;
		}

		return null;
	}

	/*
	 * @Override public Usuario updateUsuario(UsuarioDTO usuario) {
	 * 
	 * if (usuario == null || usuario.getUserName() == null ||
	 * usuario.getFirstName() == null || usuario.getLastName() == null ||
	 * usuario.getPassword() == null) { return null; }
	 * 
	 * Usuario userBD = new Usuario(); userBD.setId(usuario.getId());
	 * userBD.setUserName(usuario.getUserName());
	 * userBD.setFirstName(usuario.getFirstName());
	 * userBD.setLastName(usuario.getLastName());
	 * userBD.setEmail(usuario.getEmail());
	 * userBD.setPassword(usuario.getPassword());
	 * 
	 * return userRepo.save(usuario); }
	 */

	@Override
	public Usuario updateUsuario(UsuarioDTO usuario) {

		if (usuario == null || usuario.getId() == null || usuario.getUserName() == null
				|| usuario.getFirstName() == null || usuario.getLastName() == null || usuario.getEmail() == null
				|| usuario.getPassword() == null) {
			return null;
		}

		Usuario userBD = new Usuario();
		userBD.setId(usuario.getId());
		userBD.setUserName(usuario.getUserName());
		userBD.setFirstName(usuario.getFirstName());
		userBD.setLastName(usuario.getLastName());
		userBD.setEmail(usuario.getEmail());
		userBD.setPassword(usuario.getPassword());

		return userRepo.save(userBD);
	}

	@Override
	public void deleteUsuario(Long id) {

		userRepo.deleteById(id);

	}

}
