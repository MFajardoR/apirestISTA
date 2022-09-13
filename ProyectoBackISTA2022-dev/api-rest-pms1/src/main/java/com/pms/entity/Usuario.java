package com.pms.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idusuario;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idpersona",nullable = false)
	private Persona persona;
	
	
	private String username;

	private String password;
	
	private Date fecha_creacion;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_rol",joinColumns = @JoinColumn(name= "idusuario",referencedColumnName = "idusuario")
	, inverseJoinColumns = @JoinColumn(name= "idrol", referencedColumnName = "idrol"))
	private Set<Rol> roles = new HashSet<>();
	
	@PrePersist 
	public void prePersist() {
		//para adicionar la fecha de creacion
		fecha_creacion=new Date(System.currentTimeMillis());
	}

}
