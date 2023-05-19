import { PageContact } from './contact/pageContact';
import { Contact } from './contact/contact';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  apiURL: string = environment.apiURLBase + '/contact';

  constructor(
    private http: HttpClient
  ) {}

  save(contact: Contact) : Observable<Contact> {
    return this.http.post<Contact>(`${this.apiURL}`, contact);
  }

  list(page: any, size: any) : Observable<PageContact>{
    const params = new HttpParams()
    .set('page', page)
    .set('size', size)
    return this.http.get<any>(`${this.apiURL}?${params.toString()}`)
  }

  favourite(contact: Contact) : Observable<any> {
    return this.http.patch( `${this.apiURL}/${contact.id}/favorite`, null );
  }

  upload(contact: Contact, formData: FormData) : Observable<any> {
    return this.http.put(`${this.apiURL}/${contact.id}/photo`, formData, { responseType: 'blob' });
  }

}
