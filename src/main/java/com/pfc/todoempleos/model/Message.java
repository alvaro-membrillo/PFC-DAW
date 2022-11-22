package com.pfc.todoempleos.model;

import java.sql.Date;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

@Entity
@Table(name="MESSAGE")
public class Message {
	
	private int id_message;
	private Date date;
	private String content;
	private boolean read;
	private int id_usuario;
	private int id_ad;
	
}
