package com.pfc.todoempleos.services;

import java.util.List;

import com.pfc.todoempleos.model.Ad;
import com.pfc.todoempleos.model.Usuario;

public interface AdService {
	
	public List<Usuario> getAds();
	public Ad insertUsuario(Ad ad);
	
}
