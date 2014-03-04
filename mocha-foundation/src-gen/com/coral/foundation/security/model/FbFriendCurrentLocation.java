package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.FbFriendCurrentLocation + "</p>
  * <p>Description: The FbFriendCurrentLocation entity </p>
  */
@Entity(name = "FbFriendCurrentLocation")
@Table(name = "T_FB_FRIEND_CURRENT_LOCATION")
public class FbFriendCurrentLocation extends JPABaseEntity {
	
	@Id()
	@Column (name = "FB_FRIEND_CURRENT_LOCATION_ID")
	@GeneratedValue(generator="FBFRIENDCURRENTLOCATIONID_SEQ")
	@TableGenerator(name="FBFRIENDCURRENTLOCATIONID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long fbFriendCurrentLocationId;
	
	@Column(name = "STREET" )
	private String street;
	
	
	@Column(name = "CITY" )
	private String city;
	
	
	@Column(name = "STATE" )
	private String state;
	
	
	@Column(name = "COUNTRY" )
	private String country;
	
	
	@Column(name = "ZIP" )
	private String zip;
	
	
	@Column(name = "ADDRESS" )
	private String address;
	
	
	@Column(name = "LATITUDE" )
	private String latitude;
	
	
	@Column(name = "LONGITUDE" )
	private String longitude;
	
	
	@Column(name = "ID" )
	private String id;
	
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "LOCATED_IN" )
	private String located_in;
	
	
	@Column(name = "REGION" )
	private String region;
	
	

	public void setFbFriendCurrentLocationId (Long fbFriendCurrentLocationId) {
		this.fbFriendCurrentLocationId = fbFriendCurrentLocationId;
	} 
	public Long getFbFriendCurrentLocationId () {
		return fbFriendCurrentLocationId;
	}
	public void setStreet (String street) {
		this.street = street;
	} 
	public String getStreet () {
		return street;
	}
	public void setCity (String city) {
		this.city = city;
	} 
	public String getCity () {
		return city;
	}
	public void setState (String state) {
		this.state = state;
	} 
	public String getState () {
		return state;
	}
	public void setCountry (String country) {
		this.country = country;
	} 
	public String getCountry () {
		return country;
	}
	public void setZip (String zip) {
		this.zip = zip;
	} 
	public String getZip () {
		return zip;
	}
	public void setAddress (String address) {
		this.address = address;
	} 
	public String getAddress () {
		return address;
	}
	public void setLatitude (String latitude) {
		this.latitude = latitude;
	} 
	public String getLatitude () {
		return latitude;
	}
	public void setLongitude (String longitude) {
		this.longitude = longitude;
	} 
	public String getLongitude () {
		return longitude;
	}
	public void setId (String id) {
		this.id = id;
	} 
	public String getId () {
		return id;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setLocated_in (String located_in) {
		this.located_in = located_in;
	} 
	public String getLocated_in () {
		return located_in;
	}
	public void setRegion (String region) {
		this.region = region;
	} 
	public String getRegion () {
		return region;
	}

	public Long getID() {
		return getFbFriendCurrentLocationId();
	}
}

