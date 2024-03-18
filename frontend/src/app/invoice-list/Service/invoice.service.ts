import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Invoice } from '../Models/Invoice';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private url: string = "http://localhost:8088/invoice";

  constructor(private httpClient: HttpClient) { }



  public getInvoicesList(): Observable<Invoice[]> {
    return this.httpClient.get<Invoice[]>(this.url + "/getInvoicesList");
  }

   

}
