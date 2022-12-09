package com.pfc.todoempleos.dto;

public class AgreementDTO {

	private Long anuncio;
	private Long usuario;
	private boolean status;

	public Long getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Long anuncio) {
		this.anuncio = anuncio;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
