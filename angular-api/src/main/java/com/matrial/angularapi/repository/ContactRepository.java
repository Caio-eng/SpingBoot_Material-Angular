package com.matrial.angularapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrial.angularapi.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
