package com.smsapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;


@Data
@Entity(name = "users")
public class UserModel  extends  User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int status;
	
	
	@NotNull
	@ColumnDefault(value="0.00")
	private double profitpercentage;
	
	
	@NotNull
	@ColumnDefault(value="user")
	private String type;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int entityid;
	
	@NotNull	
	private String username;

	@NotNull
	private String password;
	
	@NotNull
	private String admin;
	
	private LocalDateTime createdtime;
	
	private LocalDateTime modifiedtime;

	
	@Transient
	private String token;
	
	
	public UserModel() {
		
		super(System.getProperty("smsapi.username"),System.getProperty("smsapi.password"),new ArrayList<GrantedAuthority>());
	}
	public UserModel(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username,password,authorities);
	}


	
	@PrePersist
	public void setCreationDateTime() {
		this.createdtime = LocalDateTime.now();
		 double scale = Math.pow(10, 2);
		 this.profitpercentage = Math.round(this.profitpercentage * scale) / scale;
	

	}

	@PreUpdate
	public void setModifiedDateTime() {
		this.modifiedtime = LocalDateTime.now();
		double scale = Math.pow(10, 2);
		this.profitpercentage = Math.round(this.profitpercentage * scale) / scale;
	}
	

	public String getUsername() {
		
		return this.username.toLowerCase();
	}
}
