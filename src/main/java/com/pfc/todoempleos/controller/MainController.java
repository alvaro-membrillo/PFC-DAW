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
import com.pfc.todoempleos.services.AgreementServiceImpl;
import com.pfc.todoempleos.services.UsuarioServiceImpl;

@Controller
public class MainController {

	@Autowired
	UsuarioServiceImpl userService;

	@Autowired
	AdServiceImpl adService;

	@Autowired
	AgreementServiceImpl agService;

	@GetMapping("/")
	public String home(@RequestParam(name = "title", required = false) String titulo, @ModelAttribute("ad") Ad anuncio,
			Model model) {

		try {

			List<Ad> listaAnuncios = adService.getAds();
			List<Ad> busqueda = adService.findByTitle(titulo);

			model.addAttribute("anuncios", listaAnuncios);
			model.addAttribute("busqueda", busqueda);
			model.addAttribute("title", anuncio.getTitle());

			return "index";

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "index";

	}

	/* ZONA DE ADMINISTRADOR */

	@GetMapping("/admin")
	public String adminHome(Model model) {

		model.addAttribute("usuarios", userService.getUsuarios());

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

	@GetMapping("/contact")
	public String userContact(@RequestParam(name = "ad") String ad, /* @ModelAttribute AdDTO adDTO, */ Model model) {

		// AgreementDTO agDTO = new AgreementDTO();
		// model.addAttribute("ag", agDTO);
		Ad anuncio = adService.findById(Long.parseLong(ad));
		Usuario usuario = userService.findUserById(anuncio.getUsuario().getId()).get();
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("nombre", anuncio.getTitle());
		model.addAttribute("usuario", usuario);

		return "contact";
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
		/*userBD.setPhone(usuario.getPhone());*/
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

	@GetMapping("/ads")
	public String adList(Model model) {

		model.addAttribute("anuncios", adService.getAds());

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

		model.addAttribute("adList", otrosAnuncios);

		return "anuncios";
	}

	@GetMapping("/ads/update")
	public String updateAnuncio(@RequestParam(name = "id") String id, Model model) {

		if (id == null) {
			return "redirect:/ads";
		}

		Ad anuncio = adService.findById(Long.parseLong(id));
		model.addAttribute("anuncio", anuncio);

		return "updateAnuncio";
	}

	@PostMapping("/ads/update")
	public String postUpdateAnuncio(@ModelAttribute AdDTO ad, Model model) {

		adService.updateAd(ad);

		return "redirect:/ads";
	}
	
	@RequestMapping("/ads/delete")
	public String adsDelete(@RequestParam(name = "id") String id, Model model) {

		adService.deleteAd(Long.parseLong(id));

		return "redirect:/ads";
	}

}
