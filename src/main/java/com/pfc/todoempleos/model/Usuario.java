package com.pfc.todoempleos.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = -1917432343497569811L;

	@Id
	@Column(name="id_usuario", unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Column(name="username",unique=true, nullable=false)
	@Getter @Setter
	private String userName;
	
	@Column(name="password", nullable=false)
	@Getter @Setter
	private String password;
	
	@Column(name="email", unique=true,nullable=false)
	@Getter @Setter
	private String email;
	
	@Column(name="firstname", nullable=false)
	@Getter @Setter
	private String firstName;
	
	@Column(name="lastname", nullable=false)
	@Getter @Setter
	private String lastName;
	
	@Column(name="role", nullable=false)
	@Getter @Setter
	private String role;
	
	@Column(name="activo", nullable=false,columnDefinition="BOOLEAN")
	@Getter @Setter
	private boolean activo;
	
	@Column(name="birthdate", nullable=false)
	@Getter @Setter
	private Date birthDate;

}
