package com.matrial.angularapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O campo Nome é obrigatório")
	@Column
	private String name;
	
	@Column
	@NotBlank(message = "O campo Email é obrigatório")
	@Email(message = "Email invalido")
	private String email;
	
	@Column
	private Boolean favorite;
	
	@Column
	@Lob
	private byte[] photo;
	
	public Contact() {
		
	}

	public Contact(Integer id, String name, String email, Boolean favorite, byte[] photo) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.favorite = favorite;
		this.photo = photo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}	
	
}
