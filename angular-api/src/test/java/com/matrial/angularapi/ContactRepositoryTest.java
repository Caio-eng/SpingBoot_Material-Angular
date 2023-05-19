package com.matrial.angularapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.matrial.angularapi.entity.Contact;
import com.matrial.angularapi.repository.ContactRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ContactRepositoryTest {

	@Autowired
	private ContactRepository contactRepository;
	
	@Test
	public void save() {
		Contact contact = new Contact();
		contact.setName("Fulano da Solva");
		contact.setEmail("fulano@email.com");
		contact.setFavorite(false);
		
		contactRepository.save(contact);
		
		Integer countInsertContact = contactRepository.findAll().size();
		assertEquals(1, countInsertContact);
	}

}
