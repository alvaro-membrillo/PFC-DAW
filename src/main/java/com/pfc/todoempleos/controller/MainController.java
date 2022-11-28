package com.pfc.todoempleos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pfc.todoempleos.dto.AdDTO;
import com.pfc.todoempleos.dto.UsuarioDTO;
import com.pfc.todoempleos.model.Ad;
import com.pfc.todoempleos.model.Usuario;
import com.pfc.todoempleos.services.UsuarioService;

@Controller
public class MainController {

	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private AdService adService;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("contenido", "INICIO");
		return "index";
	}

	@GetMapping("usuarios")
	public String getUsuarios(Model model) {
		model.addAttribute("usuarios", userService.getUsuarios());
		return "usuarios";
	}

	@GetMapping("/register")
	public String registerGet(Model model) {

		UsuarioDTO userDTO = new UsuarioDTO();
		model.addAttribute("usuario", userDTO);
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@ModelAttribute UsuarioDTO usuario) {

		Usuario userBD = new Usuario();
		userBD.setUserName(usuario.getUserName());
		userBD.setPassword(new BCryptPasswordEncoder(15).encode(usuario.getPassword()));
		userBD.setEmail(usuario.getEmail());
		userBD.setFirstName(usuario.getFirstName());
		userBD.setLastName(usuario.getLastName());
		userBD.setRole("ROLE_USER");
		userBD.setActivo(true);
		userBD.setBirthDate(usuario.getBirthDate());

		userBD = userService.insertUsuario(userBD);

		if (userBD == null) {
			return "redirect:/register";
		}

		return "redirect:/";
	}
	
	@GetMapping("/addAd")
	public String addAdGet(Model model) {

		AdDTO adDTO = new AdDTO();
		model.addAttribute("ad", adDTO);
		return "addAd";
	}

	@PostMapping("/addAd")
	public String addAdPost(@ModelAttribute AdDTO ad) {

		Ad adAdBD = new Ad();
		adAdBD.setUserName(ad.getUserName());
		adAdBD.setEmail(ad.getEmail());
		adAdBD.setFirstName(ad.getFirstName());
		adAdBD.setLastName(ad.getLastName());

		adAdBD = userService.insertUsuario(adAdBD);

		if (adAdBD == null) {
			return "redirect:/register";
		}

		return "redirect:/";
	}

}
