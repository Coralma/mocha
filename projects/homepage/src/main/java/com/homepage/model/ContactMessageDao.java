package com.homepage.model;

public class ContactMessageDao extends abstractDao {

	private ContactMessage contactMessage;

	public ContactMessageDao(ContactMessage contactMessage) {
		super();
		this.contactMessage = contactMessage;
	}

	public void saveContactMessage(ContactMessage contactMessage) {
		entityMfg.getTransaction().begin();
		java.util.Date templateDate = new java.util.Date();
		java.sql.Date currentDate = new java.sql.Date(templateDate.getTime());
		contactMessage.setMessageCreateDate(currentDate);
		entityMfg.persist(contactMessage);
		entityMfg.getTransaction().commit();
	}

}
