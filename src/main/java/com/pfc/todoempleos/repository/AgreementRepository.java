package com.pfc.todoempleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfc.todoempleos.model.Agreement;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
	
//	public boolean insertAgreement(Agreement agreement);

}
