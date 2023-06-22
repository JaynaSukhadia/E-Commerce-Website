package com.aurosoft.ecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurosoft.ecommerce.entity.Contact;

import com.aurosoft.ecommerce.service.ContactService;

@Controller
@RequestMapping("/contact")

public class ContactController {

	
	private ContactService contactService;

	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}
	
	@GetMapping("/list")
	public String listContact(Model m)
	{
		List<Contact> list=contactService.listAllContacts();
		m.addAttribute("list", list);
		
		return "admin/contact-list";
	}
	
	@GetMapping("/new")
	public String showForm(Contact Contact,Model m)
	{
		
		
		return "admin/contact-add";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute("Contact")Contact Contact)
	{
		contactService.insertContact(Contact);
		
		return "redirect:/contact/list";
	}
	
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable int id,Model m)
	{
		Contact contact = contactService.getContactById(id);
		m.addAttribute("contact",contact);
		
		
		return "admin/contact-edit";
	}
	
	@PostMapping(value="/update")
	public String update(@ModelAttribute("Contact") Contact Contact)
	{
		contactService.updateContact(Contact);
		
		return "redirect:/contact/list";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable int id)
	{
		contactService.deleteContact(id);
		
		return "redirect:/contact/list";
	}
	
}
