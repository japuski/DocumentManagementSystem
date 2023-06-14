import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Invoice } from '../Models/Invoice';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private url: string = "http://localhost:2000/invoice";

  constructor(private httpClient: HttpClient) { }

  public getInvoices() {
    return this.httpClient.get(this.url + "/getAll")
  }

  public addInvoice(invoice: Invoice) {
    console.log(invoice)
    return this.httpClient.post(this.url + "/addInvoice", invoice)

  }



}
