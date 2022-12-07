package com.pfc.todoempleos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfc.todoempleos.model.Ad;
import com.pfc.todoempleos.repository.AdRepository;

@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private AdRepository adRepo;

	@Override
	public List<Ad> getAds() {

		return adRepo.findAll();
	}

	@Override
	public Ad insertAd(Ad ad) {

		if (ad != null) {
			return adRepo.save(ad);
		}

		return null;
	}

	@Override
	public Ad findByTitle(String title) {
		
		return adRepo.findAdByTitle(title);
	}

}
