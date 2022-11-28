package com.pfc.todoempleos.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

@Entity
@Table(name = "ad")
public class Ad implements Serializable {

	private int idAd;
	private Date date;
	private String title;
	private String description;
	private double price;
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

	public int getIdUsuario() {
		return idVendedor;
	}

	public void setIdUsuario(int idVendedor) {
		this.idVendedor = idVendedor;
	}

}
