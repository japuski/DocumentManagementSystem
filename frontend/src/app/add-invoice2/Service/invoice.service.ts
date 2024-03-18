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


  public addInvoice(invoice: Invoice) {
    console.log(invoice)
    return this.httpClient.post(this.url + "/addInvoice", invoice)
  }

  public getInvoice(invoiceId: number): Observable<Invoice> {
    return this.httpClient.get<Invoice>(this.url + "/getInvoice?invoiceId=" + invoiceId);
  }

  public getInvoicesByNumber(): Observable<number[]> {
    return this.httpClient.get<number[]>(this.url + '/getInvoicesByNumber');
  }

  public getInvoiceFile(invoiceId: number) {

    return this.httpClient
      .get(this.url + "/getInvoiceFile?invoiceId=" + invoiceId, { responseType: 'blob' })
  }

  public generateInvoice(invoiceId: number) {
    console.log("wykonano krok 1 " + invoiceId)
    return this.httpClient.post(this.url + "/generateInvoice", invoiceId);
  }

    


}
