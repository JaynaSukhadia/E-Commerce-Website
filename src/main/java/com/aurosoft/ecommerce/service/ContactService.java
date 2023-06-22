package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.Contact;

public interface ContactService {

	
	List<Contact> listAllContacts();
	Contact  getContactById(int id);
	Contact insertContact(Contact contact);
	Contact updateContact(Contact contact);
	int deleteContact(int id);
}
