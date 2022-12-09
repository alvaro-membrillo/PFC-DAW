package com.pfc.todoempleos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "ad")
public class Ad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_ad", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_ad")
	// @SequenceGenerator(name = "seq_ad", allocationSize = 5)
	private Long idAd;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "title", unique = true, nullable = false)
	private String title;
	@Column(name = "description", unique = true, nullable = false)
	private String description;
	@Column(name = "price", unique = false, nullable = false)
	private double price;
	@Column(name = "tipo", unique = false, nullable = false)
	private String tipo;

	@ManyToOne()
	@JoinColumn(name = "id_vendedor")
	private Usuario usuario;

	public Ad() {
		super();
	}

	public Long getIdAd() {
		return idAd;
	}

	public void setIdAd(Long idAd) {
		this.idAd = idAd;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
