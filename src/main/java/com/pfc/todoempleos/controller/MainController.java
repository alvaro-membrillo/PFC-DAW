package com.pfc.todoempleos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/")
	public String home(@RequestParam String titulo, @ModelAttribute("ad") Ad anuncio, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Optional<Usuario> usuario = userService.getUserByName(userDetail.getUsername());
		Usuario user = usuario.get();

		List<Ad> listaAnuncios = adService.getAds();
		List<Ad> anunciosUsuario = new ArrayList<>();

		for (Ad ad : listaAnuncios) {
			if (ad.getUsuario().getId() == user.getId()) {
				anunciosUsuario.add(ad);
			}
		}

		/*
		 * model.addAttribute("listaAds", adService.getAds());
		 * model.addAttribute("user", user);
		 */
		model.addAttribute("adList", anunciosUsuario);
		model.addAttribute("adByTitle", adService.findByTitle(titulo));
		
		/*model.addAttribute("contenido", "INICIO");
		model.addAttribute("ads", adService.getAds());*/

		/* model.addAttribute("ad", adService.getAds().get(0)); */
		return "index";
	}

	/* ZONA DE ADMINISTRADOR */

	@GetMapping("/admin")
	public String adminHome(Model model) {

		model.addAttribute("usuarios", userService.getUsuarios());

		// Vamos a ocultar al usuario que ha iniciado sesión sus propios anuncios dentro
		// del buscador

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Optional<Usuario> usuario = userService.getUserByName(userDetail.getUsername());
		Usuario user = usuario.get();

		List<Ad> listaAnuncios = adService.getAds();
		List<Ad> otrosAnuncios = new ArrayList<>();

		for (Ad ad : listaAnuncios) {
			if (ad.getUsuario().getId() == user.getId()) {
				otrosAnuncios.add(ad);
			}
		}

		/*
		 * model.addAttribute("listaAds", adService.getAds());
		 * model.addAttribute("user", user);
		 */
		model.addAttribute("adList", otrosAnuncios);

		return "usuarios";
	}

	@GetMapping("/admin/update")
	public String updateUsuario(@RequestParam(name = "user") String user, Model model) {

		if (user == null) {
			return "redirect:/admin/usuarios/";
		}

		Optional<Usuario> usuario = userService.findUserById(Long.parseLong(user));
		model.addAttribute("usuario", usuario.get());

		return "updateUsuario";
	}

	@PostMapping("/admin/update")
	public String postUpdateUsuario(@ModelAttribute UsuarioDTO user, Model model) {

		userService.updateUsuario(user);

		return "redirect:/admin";
	}

	@RequestMapping("/admin/delete")
	public String clientesDelete(@RequestParam(name = "user") String user, Model model) {

		userService.deleteUsuario(Long.parseLong(user));

		return "redirect:/admin";
	}

	/* ZONA DE USUARIO */

	/*@GetMapping("/user")
	public String userHome(@RequestParam String titulo, @ModelAttribute("ad") Ad anuncio, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Optional<Usuario> usuario = userService.getUserByName(userDetail.getUsername());
		Usuario user = usuario.get();

		List<Ad> listaAnuncios = adService.getAds();
		List<Ad> anunciosUsuario = new ArrayList<>();

		for (Ad ad : listaAnuncios) {
			if (ad.getUsuario().getId() == user.getId()) {
				anunciosUsuario.add(ad);
			}
		}*/

		/*
		 * model.addAttribute("listaAds", adService.getAds());
		 * model.addAttribute("user", user);
		 */
		/*model.addAttribute("adList", anunciosUsuario);
		model.addAttribute("adByTitle", adService.findByTitle(titulo));*/
		
		/*AdDTO adDTO = new AdDTO();
		model.addAttribute("ad", adDTO);*/

		/*return "userAds";
	}*/
	
	/*@PostMapping("/user")
	public String userHomePost(@ModelAttribute AdDTO ad) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		Ad addAdBD = new Ad();
		addAdBD.setTitle(ad.getTitle());
		addAdBD.setDescription(ad.getDescription());
		addAdBD.setPrice(ad.getPrice());
		addAdBD.setDate(new Date());
		addAdBD.setTipo(ad.getTipo());
		// Pasar de optional a usuario
		Optional<Usuario> usuario = userService.getUserByName(userDetail.getUsername());
		Usuario user = usuario.get();
		addAdBD.setUsuario(user);

		Ad adInsertado = adService.insertAd(addAdBD);

		if (adInsertado == null) {
			return "redirect:/addAd";
		}

		return "redirect:/";
	}*/

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

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		Ad addAdBD = new Ad();
		addAdBD.setTitle(ad.getTitle());
		addAdBD.setDescription(ad.getDescription());
		addAdBD.setPrice(ad.getPrice());
		addAdBD.setDate(new Date());
		addAdBD.setTipo(ad.getTipo());
		// Pasar de optional a usuario
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
