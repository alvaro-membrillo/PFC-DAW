package com.pfc.todoempleos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfc.todoempleos.dto.AgreementDTO;
import com.pfc.todoempleos.model.Agreement;
import com.pfc.todoempleos.model.Usuario;
import com.pfc.todoempleos.repository.AgreementRepository;

@Service
public class AgreementServiceImpl implements AgreementService {

	@Autowired
	private AgreementRepository agRepo;

	@Override
	public Agreement insertAgreement(Agreement agreement) {

		if (agreement != null) {
			return agRepo.save(agreement);
		}

		return null;
	}

}
