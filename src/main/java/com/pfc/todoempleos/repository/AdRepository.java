package com.pfc.todoempleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfc.todoempleos.model.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
	
	public Ad findAd(String title);

}
