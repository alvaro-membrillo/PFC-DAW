package com.pfc.todoempleos.model;

import java.io.Serializable;

public class AgreementId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long comprador;
	private Long ad;

	public Long getUsuario() {
		return comprador;
	}

	public void setUsuario(Long usuario) {
		this.comprador = usuario;
	}

	public Long getAd() {
		return ad;
	}

	public void setAd(Long ad) {
		this.ad = ad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
