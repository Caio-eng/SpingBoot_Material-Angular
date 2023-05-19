package com.matrial.angularapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matrial.angularapi.entity.Contact;
import com.matrial.angularapi.service.ContactService;

import jakarta.servlet.http.Part;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody Page<Contact> listAll(
					@RequestParam(value = "page", defaultValue = "0") Integer page,
					@RequestParam(value = "size", defaultValue = "10") Integer size
			) {
		return contactService.list(page, size);
	}
	
	@GetMapping(value = "/{id}")
	public Contact findId(@PathVariable Integer id) {
		return contactService.findId(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Contact save( @RequestBody @Valid Contact contact ) {
		return contactService.save(contact);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody @Valid Contact updateContact) {
		contactService.update(id, updateContact);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		contactService.delete(id);
	}
	
	@PatchMapping("/{id}/favorite")
	public void favorite(@PathVariable Integer id) {
		contactService.favorite(id);
	}
	
	@PutMapping("/{id}/photo")
	public byte[] addPhoto(
			@PathVariable Integer id, 
			@RequestParam("photo") Part arquive) {
		return contactService.addPhoto(id, arquive);	
	}
	
}
