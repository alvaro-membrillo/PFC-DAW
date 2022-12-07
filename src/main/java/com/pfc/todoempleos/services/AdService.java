package com.pfc.todoempleos.services;

import java.util.List;

import com.pfc.todoempleos.model.Ad;

public interface AdService {
	
	public List<Ad> getAds();
	public Ad insertAd(Ad ad);
	public Ad findByTitle(String title);
	
}
