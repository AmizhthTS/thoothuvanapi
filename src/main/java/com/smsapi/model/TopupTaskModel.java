package com.smsapi.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity(name = "topup_log")

public class TopupTaskModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	@OneToOne(cascade=CascadeType.MERGE)
	TopupModel topupmodel;
	
	@OneToOne(cascade=CascadeType.MERGE)
	CreditModel creditmodel;
	
	private LocalDateTime createdtime;
	
	private LocalDateTime modifiedtime;

	@PrePersist
	public void setCreationDateTime() {
		this.createdtime = LocalDateTime.now();
		this.modifiedtime = LocalDateTime.now();

	}


}
