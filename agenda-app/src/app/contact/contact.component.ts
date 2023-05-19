import { ContactService } from './../contact.service';
import { Component, OnInit } from '@angular/core';
import { Contact } from './contact';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog'

import { ContactDetailComponent } from '../contact-detail/contact-detail.component';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  form: FormGroup;
  contacts: Contact[] = [];
  columns = ['photo', 'id', 'name', 'email', 'favorite'];

  totElements = 0;
  page = 0;
  size = 10;
  pageSizeOptions : number [] = [10];

  constructor(
    private service: ContactService,
    private fb: FormBuilder,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.assembleForm();
    this.listContacts(this.page, this.size);
  }

  assembleForm() {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email] ]
    })
  }

  listContacts( page = 0, size = 10 ) {
    this.service.list(page, size).subscribe(response => {
      this.contacts = response.content;
      this.totElements = response.totElements;
      this.page = response.number;
    })
  }

  favorite(contact: Contact) {
    this.service.favourite(contact).subscribe( response => {
      contact.favorite = !contact.favorite;
    });

  }

  submit() {
    const formValues = this.form.value
    const contact : Contact = new Contact(formValues.name, formValues.email);
    this.service.save(contact).subscribe( response => {
      this.listContacts();
      this.snackBar.open('O Contato foi adiocionado!', 'Sucesso!', {
        duration: 2000
      })
      this.form.reset();
    })
  }

  uploadPhoto(event: any, contact: Contact) {
    const files = event.target.files;
    if (files) {
      const photo = files[0];
      const formData: FormData = new FormData();
      formData.append("photo", photo);
      this.service
              .upload(contact, formData)
              .subscribe(response => this.listContacts());
    }
  }

  viewContact( contact: Contact ) {
    this.dialog.open( ContactDetailComponent, {
      width: '400px',
      height: '450px',
      data: contact
    } )
  }

  pagenable(event: PageEvent) {
    this.page = event.pageIndex;
    this.listContacts(this.page, this.size);
  }

}
