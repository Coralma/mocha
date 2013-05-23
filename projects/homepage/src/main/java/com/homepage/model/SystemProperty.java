package com.homepage.model;

import java.beans.Beans;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "t_systemproperty")
@NamedQueries({@NamedQuery(name = "findSystemPropertyByKey", query = "SELECT sy.value FROM SystemProperty sy where sy.key= :key")})
public class SystemProperty extends Beans implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemProperty(){
		
	}
	
	@Id()
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Basic(optional = false)
	@Column(name = "systemKey")
	private String key;
	
	@Basic(optional = false)
	@Column(name = "systemValue")
	private String value;
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
