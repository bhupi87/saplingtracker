package com.salah.projectmanager.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class FileInfo {
	
	
/*	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)*/
	private int id; 
	
	private String filename;		
	
	public String getFilename() {
		return this.filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	

}
