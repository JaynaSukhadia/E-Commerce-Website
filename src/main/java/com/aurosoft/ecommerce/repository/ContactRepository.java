package com.aurosoft.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.ecommerce.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
