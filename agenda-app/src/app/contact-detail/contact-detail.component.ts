import { Contact } from './../contact/contact';
import { Component, OnInit, Inject } from '@angular/core';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'

@Component({
  selector: 'app-contact-detail',
  templateUrl: './contact-detail.component.html',
  styleUrls: ['./contact-detail.component.css']
})
export class ContactDetailComponent implements OnInit {

  constructor(
    public dialigRef: MatDialogRef<ContactDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public contact: Contact
  ) { }

  ngOnInit(): void {
  }

  close() {
    this.dialigRef.close();
  }

}
