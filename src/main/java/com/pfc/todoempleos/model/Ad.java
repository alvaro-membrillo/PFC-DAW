package com.pfc.todoempleos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="ad")
public class Ad implements Serializable {
	
	@Id
	@Column(name = "id_ad", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAd;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "title", unique = true, nullable = false)
	private String title;
	@Column(name = "description", unique = true, nullable = false)
	private String description;
	@Column(name = "price", unique = false, nullable = false)
	private double price;
	@Column(name = "idVendedor", unique = false, nullable = false)
	private int idVendedor;

	public Ad() {
		super();
	}

	public int getIdAd() {
		return idAd;
	}

	public void setIdAd(int idAd) {
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

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}

}
