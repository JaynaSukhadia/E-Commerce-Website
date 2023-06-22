package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.Contact;
import com.aurosoft.ecommerce.repository.ContactRepository;
import com.aurosoft.ecommerce.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
@Autowired
	ContactRepository contactRepository;
	
	@Override
	public List<Contact> listAllContacts() {
		
		return contactRepository.findAll();
	}

	@Override
	public Contact getContactById(int id) {
		
		return contactRepository.findById(id).orElseThrow();
	}

	@Override
	public Contact insertContact(Contact contact) {
		
		return contactRepository.save(contact);
	}

	@Override
	public Contact updateContact(Contact contact) {
		
		return contactRepository.save(contact);
	}

	@Override
	public int deleteContact(int id) {
		contactRepository.deleteById(id);
		return id;
	}

}
