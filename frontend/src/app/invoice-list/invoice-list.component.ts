import { Component } from '@angular/core';
import { Invoice } from './Models/Invoice';
import { InvoiceService } from './Service/invoice.service';


@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css']
})
export class InvoiceListComponent {

  invoices: Invoice[] = [];

  constructor(private invoiceService: InvoiceService) { }

  ngOnInit(): void {
    this.loadInvoices();
    console.log(this.invoices);
  }

  loadInvoices(): void {
    this.invoiceService.getInvoicesList()
      .subscribe(invoices => {
        this.invoices = invoices;
      });

    
  }

 

}
