package com.pfc.todoempleos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfc.todoempleos.model.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
	
	public List<Ad> findAdByTitle(String title);
//	@Query(value="SELECT * FROM Ad WHERE Ad.title LIKE %:q%", nativeQuery = true)
//	public List<Ad> findByTitle(@Param("q") String q);

}
