package com.pfc.todoempleos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfc.todoempleos.dto.AdDTO;
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
	public List<Ad> findByTitle(String title) {

		return adRepo.findAdByTitle(title);
	}

	@Override
	public Ad findById(Long id) {

		return adRepo.findById(id).get();
	}

	@Override
	public Ad updateAd(AdDTO ad) {

		if (ad == null || ad.getIdAd() == null || ad.getTitle() == null || ad.getDescription() == null
				|| ad.getTipo() == null || ad.getUsuario() == null) {
			return null;
		}

		Ad adBD = new Ad();

		adBD.setIdAd(ad.getIdAd());
		adBD.setTitle(ad.getTitle());
		adBD.setDescription(ad.getDescription());
		adBD.setPrice(ad.getPrice());
		adBD.setUsuario(ad.getUsuario());
		adBD.setTipo(ad.getTipo());
		adBD.setDate(ad.getDate());

		return adRepo.save(adBD);
	}

	@Override
	public void deleteAd(Long id) {
		
		adRepo.deleteById(id);
	}

}
