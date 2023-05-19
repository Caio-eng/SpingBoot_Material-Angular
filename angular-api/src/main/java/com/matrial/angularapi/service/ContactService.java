package com.matrial.angularapi.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.matrial.angularapi.entity.Contact;
import com.matrial.angularapi.repository.ContactRepository;

import jakarta.servlet.http.Part;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public Page<Contact> list(Integer page, Integer size) {
		Sort sort = Sort.by(Sort.Direction.ASC, "id");
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		return contactRepository.findAll(pageRequest);
	}
	
	public Contact findId(Integer id) {
		return contactRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
	}
	
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
	
	public void update(Integer id, Contact updateContact) {
			contactRepository.findById(id).map(contact -> {
			contact.setName(updateContact.getName());
			contact.setEmail(updateContact.getEmail());
			contact.setFavorite(updateContact.getFavorite());			
			return contactRepository.save(contact);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
	}
	
	public void delete(Integer id) {
		contactRepository.findById(id).map(people -> {
			contactRepository.delete(people);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
	}
	
	public void favorite(Integer id) {
		Optional<Contact> contact =  contactRepository.findById(id);
		contact.ifPresent( c -> {
			boolean favorite = c.getFavorite() == Boolean.TRUE;
			c.setFavorite(!favorite);
			contactRepository.save(c);
		});
	}
	
	public byte[] addPhoto(Integer id, Part arquive) {
		Optional<Contact> contact = contactRepository.findById(id);
		return contact.map( c -> {
			try {
				InputStream is = arquive.getInputStream();
				byte[] bytes = new byte[(int) arquive.getSize()];
				IOUtils.readFully(is, bytes);
				c.setPhoto(bytes);
				contactRepository.save(c);
				is.close();
				return bytes;
			} catch (IOException e) {
				return null;
			}
		}).orElse(null);
	}
	
}
