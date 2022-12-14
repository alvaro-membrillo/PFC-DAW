package com.pfc.todoempleos.services;

import java.util.List;

import com.pfc.todoempleos.dto.AdDTO;
import com.pfc.todoempleos.model.Ad;

public interface AdService {
	
	public List<Ad> getAds();
	public Ad insertAd(Ad ad);
	public List<Ad> findByTitle(String title);
	public Ad findById(Long id);
	public Ad updateAd(AdDTO ad);
	public void deleteAd(Long id);
	/*public List<Ad> getAdsByUser(Long id);*/
//	public List<Ad> findByTitle(String q) throws Exception;
	
}
