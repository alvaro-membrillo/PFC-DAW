package com.pfc.todoempleos.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.pfc.todoempleos.services.AdServiceImpl;
import com.pfc.todoempleos.services.UsuarioServiceImpl;


@Controller
public class MainController {

	@Autowired
	UsuarioServiceImpl userService;

	@Autowired
	AdServiceImpl adService;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("contenido", "INICIO");
		model.addAttribute("listaAds", adService.getAds());
		model.addAttribute("ad", adService.getAds().get(0));
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
		
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
		Ad addAdBD = new Ad();
		addAdBD.setTitle(ad.getTitle());
		addAdBD.setDescription(ad.getDescription());
		addAdBD.setPrice(ad.getPrice());
		addAdBD.setDate(new Date());
		//Pasar de optional a usuario
		Optional<Usuario> usuario = userService.getUserByName(userDetail.getUsername());
		Usuario user = usuario.get();
		addAdBD.setUsuario(user);

		Ad adInsertado = adService.insertAd(addAdBD);

		if (adInsertado == null) {
			return "redirect:/addAd";
		}

		return "redirect:/";
	}

}
