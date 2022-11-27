package com.pfc.todoempleos.model;

import java.io.Serializable;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

@Entity
@Table(name = "agreement")
public class Agreement implements Serializable {

	private int idUsuario;
	private int idAd;
	private boolean status;

	public Agreement() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdAd() {
		return idAd;
	}

	public void setIdAd(int idAd) {
		this.idAd = idAd;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
