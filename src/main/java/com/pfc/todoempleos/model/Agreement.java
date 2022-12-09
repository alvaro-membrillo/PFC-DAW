package com.pfc.todoempleos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "agreement")
@IdClass(AgreementId.class)
public class Agreement implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * private int idComprador; private int idAd;
	 */
	@Id
	@Column(name="id_usuario", unique=true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Usuario comprador;
	@Id
	@Column(name="id_ad", unique=true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Ad ad;
	@Column(name="status", nullable=false, columnDefinition = "BOOLEAN")
	private boolean status;

	public Agreement() {
		super();
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
