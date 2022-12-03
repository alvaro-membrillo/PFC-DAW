package com.pfc.todoempleos.model;

 import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_message", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_message;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name="content", unique=false, nullable=false)
	private String content;
	@Column(name="read", nullable=false, columnDefinition = "BOOLEAN")
	private boolean read;
	
	@Column(name="id_usuario", nullable=false)
	private Usuario usuario;
	
	@Column(name="id_ad", nullable=false)
	private Ad ad;

	public Message() {
		super();
	}

	public int getId_message() {
		return id_message;
	}

	public void setId_message(int id_message) {
		this.id_message = id_message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
