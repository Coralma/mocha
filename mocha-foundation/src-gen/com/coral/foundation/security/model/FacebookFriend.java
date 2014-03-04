package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.FacebookFriend + "</p>
  * <p>Description: The FacebookFriend entity </p>
  */
@Entity(name = "FacebookFriend")
@Table(name = "T_FACEBOOK_FRIEND")
public class FacebookFriend extends JPABaseEntity {
	
	@Id()
	@Column (name = "FACEBOOK_FRIEND_ID")
	@GeneratedValue(generator="FACEBOOKFRIENDID_SEQ")
	@TableGenerator(name="FACEBOOKFRIENDID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long facebookFriendId;
	
	@Column(name = "UID" )
	private String uid;
	
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "RELATIONSHIP_STATUS" )
	private String relationship_status;
	
	
	@Column(name = "PROFILE_URL" )
	private String profile_url;
	
	
	@OneToMany(targetEntity=FbFriendEduction.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="FACEBOOK_FRIEND_ID")
	private List<FbFriendEduction> educations = new ArrayList<FbFriendEduction>();
	
	@Column(name = "EMAIL" )
	private String email;
	
	
	@Column(name = "BIRTHDAY" )
	private String birthday;
	
	
	@Column(name = "ABOUT_ME" )
	private String about_me;
	
	
	@Column(name = "ACTIVITIES" )
	private String activities;
	
	
	@Column(name = "ALLOWED_RESTRICTIONS" )
	private String allowed_restrictions;
	
	
	@Column(name = "BOOKS" )
	private String books;
	
	
	@Column(name = "CONTACT_EMAIL" )
	private String contact_email;
	
	
	@Column(name = "CURRENCY" )
	private String currency;
	
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = FbFriendCurrentAddress.class)
	private FbFriendCurrentAddress currentAddress;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = FbFriendCurrentLocation.class)
	private FbFriendCurrentLocation currentLocation;
	
	@Column(name = "FIRST_NAME" )
	private String first_name;
	
	
	@Column(name = "LAST_NAME" )
	private String last_name;
	
	
	@Column(name = "FRIEND_COUNT" )
	private String friend_count;
	
	
	@Column(name = "HAS_TIMELINE" )
	private String has_timeline;
	
	
	@Column(name = "HOMETOWN_LOCATION" )
	private String hometown_location;
	
	
	@Column(name = "INTERESTS" )
	private String interests;
	
	
	@Column(name = "LIKES_COUNT" )
	private String likes_count;
	
	
	@OneToMany(targetEntity=FbFriendLanguage.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="FACEBOOK_FRIEND_ID")
	private List<FbFriendLanguage> languages = new ArrayList<FbFriendLanguage>();
	
	@Column(name = "LOCALE" )
	private String locale;
	
	
	@Column(name = "MEETING_FOR" )
	private String meeting_for;
	
	
	@Column(name = "MEETING_SEX" )
	private String meeting_sex;
	
	
	@Column(name = "MIDDLE_NAME" )
	private String middle_name;
	
	
	@Column(name = "MOVIES" )
	private String movies;
	
	
	@Column(name = "MUSIC" )
	private String music;
	
	
	@Column(name = "MUTUAL_FRIEND_COUNT" )
	private String mutual_friend_count;
	
	
	@Column(name = "NOTES_COUNT" )
	private String notes_count;
	
	
	@Column(name = "ONLINE_PRESENCE" )
	private String online_presence;
	
	
	@Column(name = "PAYMENT_PRICEPOINTS" )
	private String payment_pricepoints;
	
	
	@Column(name = "PIC" )
	private String pic;
	
	
	@Column(name = "PIC_SMALL" )
	private String pic_small;
	
	
	@Column(name = "POLITICAL" )
	private String political;
	
	
	@Column(name = "PROFILE_BLURB" )
	private String profile_blurb;
	
	
	@Column(name = "PROFILE_UPDATE_TIME" )
	private String profile_update_time;
	
	
	@Column(name = "QUOTES" )
	private String quotes;
	
	
	@Column(name = "RELIGION" )
	private String religion;
	
	
	@Column(name = "SECURITY_SETTINGS" )
	private String security_settings;
	
	
	@Column(name = "SEX" )
	private String sex;
	
	
	@OneToMany(targetEntity=FbFriendSport.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="FACEBOOK_FRIEND_ID")
	private List<FbFriendSport> sports = new ArrayList<FbFriendSport>();
	
	@Column(name = "SUBSCRIBER_COUNT" )
	private String subscriber_count;
	
	
	@Column(name = "THIRD_PARTY_ID" )
	private String third_party_id;
	
	
	@Column(name = "TIMEZONE" )
	private String timezone;
	
	
	@Column(name = "TV" )
	private String tv;
	
	
	@Column(name = "VIEWER_CAN_SEND_GIFT" )
	private String viewer_can_send_gift;
	
	
	@Column(name = "WEBSITE" )
	private String website;
	
	
	@Column(name = "GAMES" )
	private String games;
	
	
	@OneToMany(targetEntity=FbFriendWork.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name="FACEBOOK_FRIEND_ID")
	private List<FbFriendWork> works = new ArrayList<FbFriendWork>();
	

	public void setFacebookFriendId (Long facebookFriendId) {
		this.facebookFriendId = facebookFriendId;
	} 
	public Long getFacebookFriendId () {
		return facebookFriendId;
	}
	public void setUid (String uid) {
		this.uid = uid;
	} 
	public String getUid () {
		return uid;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setRelationship_status (String relationship_status) {
		this.relationship_status = relationship_status;
	} 
	public String getRelationship_status () {
		return relationship_status;
	}
	public void setProfile_url (String profile_url) {
		this.profile_url = profile_url;
	} 
	public String getProfile_url () {
		return profile_url;
	}
	public void setEducations (List<FbFriendEduction> educations) {
		this.educations = educations;
	} 
	public List<FbFriendEduction> getEducations () {
		return educations;
	}
	public void setEmail (String email) {
		this.email = email;
	} 
	public String getEmail () {
		return email;
	}
	public void setBirthday (String birthday) {
		this.birthday = birthday;
	} 
	public String getBirthday () {
		return birthday;
	}
	public void setAbout_me (String about_me) {
		this.about_me = about_me;
	} 
	public String getAbout_me () {
		return about_me;
	}
	public void setActivities (String activities) {
		this.activities = activities;
	} 
	public String getActivities () {
		return activities;
	}
	public void setAllowed_restrictions (String allowed_restrictions) {
		this.allowed_restrictions = allowed_restrictions;
	} 
	public String getAllowed_restrictions () {
		return allowed_restrictions;
	}
	public void setBooks (String books) {
		this.books = books;
	} 
	public String getBooks () {
		return books;
	}
	public void setContact_email (String contact_email) {
		this.contact_email = contact_email;
	} 
	public String getContact_email () {
		return contact_email;
	}
	public void setCurrency (String currency) {
		this.currency = currency;
	} 
	public String getCurrency () {
		return currency;
	}
	public void setCurrentAddress (FbFriendCurrentAddress currentAddress) {
		this.currentAddress = currentAddress;
	} 
	public FbFriendCurrentAddress getCurrentAddress () {
		return currentAddress;
	}
	public void setCurrentLocation (FbFriendCurrentLocation currentLocation) {
		this.currentLocation = currentLocation;
	} 
	public FbFriendCurrentLocation getCurrentLocation () {
		return currentLocation;
	}
	public void setFirst_name (String first_name) {
		this.first_name = first_name;
	} 
	public String getFirst_name () {
		return first_name;
	}
	public void setLast_name (String last_name) {
		this.last_name = last_name;
	} 
	public String getLast_name () {
		return last_name;
	}
	public void setFriend_count (String friend_count) {
		this.friend_count = friend_count;
	} 
	public String getFriend_count () {
		return friend_count;
	}
	public void setHas_timeline (String has_timeline) {
		this.has_timeline = has_timeline;
	} 
	public String getHas_timeline () {
		return has_timeline;
	}
	public void setHometown_location (String hometown_location) {
		this.hometown_location = hometown_location;
	} 
	public String getHometown_location () {
		return hometown_location;
	}
	public void setInterests (String interests) {
		this.interests = interests;
	} 
	public String getInterests () {
		return interests;
	}
	public void setLikes_count (String likes_count) {
		this.likes_count = likes_count;
	} 
	public String getLikes_count () {
		return likes_count;
	}
	public void setLanguages (List<FbFriendLanguage> languages) {
		this.languages = languages;
	} 
	public List<FbFriendLanguage> getLanguages () {
		return languages;
	}
	public void setLocale (String locale) {
		this.locale = locale;
	} 
	public String getLocale () {
		return locale;
	}
	public void setMeeting_for (String meeting_for) {
		this.meeting_for = meeting_for;
	} 
	public String getMeeting_for () {
		return meeting_for;
	}
	public void setMeeting_sex (String meeting_sex) {
		this.meeting_sex = meeting_sex;
	} 
	public String getMeeting_sex () {
		return meeting_sex;
	}
	public void setMiddle_name (String middle_name) {
		this.middle_name = middle_name;
	} 
	public String getMiddle_name () {
		return middle_name;
	}
	public void setMovies (String movies) {
		this.movies = movies;
	} 
	public String getMovies () {
		return movies;
	}
	public void setMusic (String music) {
		this.music = music;
	} 
	public String getMusic () {
		return music;
	}
	public void setMutual_friend_count (String mutual_friend_count) {
		this.mutual_friend_count = mutual_friend_count;
	} 
	public String getMutual_friend_count () {
		return mutual_friend_count;
	}
	public void setNotes_count (String notes_count) {
		this.notes_count = notes_count;
	} 
	public String getNotes_count () {
		return notes_count;
	}
	public void setOnline_presence (String online_presence) {
		this.online_presence = online_presence;
	} 
	public String getOnline_presence () {
		return online_presence;
	}
	public void setPayment_pricepoints (String payment_pricepoints) {
		this.payment_pricepoints = payment_pricepoints;
	} 
	public String getPayment_pricepoints () {
		return payment_pricepoints;
	}
	public void setPic (String pic) {
		this.pic = pic;
	} 
	public String getPic () {
		return pic;
	}
	public void setPic_small (String pic_small) {
		this.pic_small = pic_small;
	} 
	public String getPic_small () {
		return pic_small;
	}
	public void setPolitical (String political) {
		this.political = political;
	} 
	public String getPolitical () {
		return political;
	}
	public void setProfile_blurb (String profile_blurb) {
		this.profile_blurb = profile_blurb;
	} 
	public String getProfile_blurb () {
		return profile_blurb;
	}
	public void setProfile_update_time (String profile_update_time) {
		this.profile_update_time = profile_update_time;
	} 
	public String getProfile_update_time () {
		return profile_update_time;
	}
	public void setQuotes (String quotes) {
		this.quotes = quotes;
	} 
	public String getQuotes () {
		return quotes;
	}
	public void setReligion (String religion) {
		this.religion = religion;
	} 
	public String getReligion () {
		return religion;
	}
	public void setSecurity_settings (String security_settings) {
		this.security_settings = security_settings;
	} 
	public String getSecurity_settings () {
		return security_settings;
	}
	public void setSex (String sex) {
		this.sex = sex;
	} 
	public String getSex () {
		return sex;
	}
	public void setSports (List<FbFriendSport> sports) {
		this.sports = sports;
	} 
	public List<FbFriendSport> getSports () {
		return sports;
	}
	public void setSubscriber_count (String subscriber_count) {
		this.subscriber_count = subscriber_count;
	} 
	public String getSubscriber_count () {
		return subscriber_count;
	}
	public void setThird_party_id (String third_party_id) {
		this.third_party_id = third_party_id;
	} 
	public String getThird_party_id () {
		return third_party_id;
	}
	public void setTimezone (String timezone) {
		this.timezone = timezone;
	} 
	public String getTimezone () {
		return timezone;
	}
	public void setTv (String tv) {
		this.tv = tv;
	} 
	public String getTv () {
		return tv;
	}
	public void setViewer_can_send_gift (String viewer_can_send_gift) {
		this.viewer_can_send_gift = viewer_can_send_gift;
	} 
	public String getViewer_can_send_gift () {
		return viewer_can_send_gift;
	}
	public void setWebsite (String website) {
		this.website = website;
	} 
	public String getWebsite () {
		return website;
	}
	public void setGames (String games) {
		this.games = games;
	} 
	public String getGames () {
		return games;
	}
	public void setWorks (List<FbFriendWork> works) {
		this.works = works;
	} 
	public List<FbFriendWork> getWorks () {
		return works;
	}

	public Long getID() {
		return getFacebookFriendId();
	}
}

