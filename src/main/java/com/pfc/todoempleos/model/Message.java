package com.pfc.todoempleos.model;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

@Entity
@Table(name = "message")
public class Message implements Serializable {

	private int id_message;
	private Date date;
	private String content;
	private boolean read;
	private int id_usuario;
	private int id_ad;

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

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_ad() {
		return id_ad;
	}

	public void setId_ad(int id_ad) {
		this.id_ad = id_ad;
	}

}
