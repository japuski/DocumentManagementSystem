import { Component } from '@angular/core';
import { FormBuilder,FormControl,FormGroup, Validators  } from '@angular/forms';
import { Address } from './Models/Address';
import { Invoice } from './Models/Invoice';
import { MyFormModel } from './Models/MyFormModel';
import { Person } from './Models/Person';
import { Product } from './Models/Product';
import { Segment } from './Models/Segment'
import { InvoiceService } from './Service/invoice.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

 
  private segmentIterator: number = 0;
  segmentList: Segment[] = [];
  invoiceForm: FormGroup;


  constructor(
    private invoiceService: InvoiceService,
  ) {
    this.invoiceForm = new FormGroup({
      invoiceNumber: new FormControl(''),
      dateOfIssue: new FormControl(''),
      dateOfSale: new FormControl(''),
      buyerName: new FormControl(''),
      buyerNip: new FormControl(''),
      buyerStreet: new FormControl(''),
      buyerNumber: new FormControl(''),
      buyerPostalCode: new FormControl(''),
      buyerCity: new FormControl(''),
      sellerName: new FormControl(''),
      sellerNip: new FormControl(''),
      sellerStreet: new FormControl(''),
      sellerNumber: new FormControl(''),
      sellerPostalCode: new FormControl(''),
      sellerCity: new FormControl('')
    });

    console.log(JSON.stringify(this.segmentList))
  };


  addProduct(): void {
    this.segmentList.push(new Segment(0, new Product("", "", "", 0, 0)));
    this.invoiceForm.addControl('quantity_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('productName_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('pkwiu_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('unit_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('nettoPrice_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('taxRate_' + this.segmentIterator, new FormControl(''));
    this.segmentIterator = this.segmentIterator + 1;
    console.log(JSON.stringify(this.segmentList))
  };

  removeSegment(index: number): void {
    /*reindeksowanie */
    console.log('INDEX: ' + index);
  };

  onSubmit(): void {

    let invoiceNumber = this.invoiceForm.get('invoiceNumber')?.value;
    let dateOfIssue = this.invoiceForm.get('dateOfIssue')?.value;
    let dateOfSale = this.invoiceForm.get('dateOfSale')?.value;
    let buyerName = this.invoiceForm.get('buyerName')?.value;
    let buyerNip = this.invoiceForm.get('buyerNip')?.value;
    let buyerStreet = this.invoiceForm.get('buyerStreet')?.value;
    let buyerNumber = this.invoiceForm.get('buyerNumber')?.value;
    let buyerPostalCode = this.invoiceForm.get('buyerPostalCode')?.value;
    let buyerCity = this.invoiceForm.get('buyerCity')?.value;
    let sellerName = this.invoiceForm.get('sellerName')?.value;
    let sellerNip = this.invoiceForm.get('sellerNip')?.value;
    let sellerStreet = this.invoiceForm.get('sellerStreet')?.value;
    let sellerNumber = this.invoiceForm.get('sellerNumber')?.value;
    let sellerPostalCode = this.invoiceForm.get('sellerPostalCode')?.value;
    let sellerCity = this.invoiceForm.get('sellerCity')?.value;

    let buyerAddress: Address = new Address(buyerStreet, buyerNumber, buyerPostalCode, buyerCity);
    let sellerAddress: Address = new Address(sellerStreet, sellerNumber, sellerPostalCode, sellerCity);
    let buyer: Person = new Person(buyerName, buyerNip, buyerAddress);
    let seller: Person = new Person(sellerName, sellerNip, sellerAddress);
    let segments: Array<Segment> = [];

    for (let i = 0; i < this.segmentIterator; i++) {
      segments.push(
        new Segment(this.invoiceForm.get('quantity_' + i)?.value,
          new Product(
            this.invoiceForm.get('productName_' + i)?.value,
            this.invoiceForm.get('pkwiu_' + i)?.value,
            this.invoiceForm.get('unit_' + i)?.value,
            this.invoiceForm.get('nettoPrice_' + i)?.value,
            this.invoiceForm.get('taxRate_' + i)?.value
          )))
    };

    let invoice: Invoice = new Invoice(invoiceNumber, seller, buyer, dateOfIssue, dateOfSale, segments);




    this.invoiceService.addInvoice(invoice).subscribe(value => {
      console.log('OK')
    }, error => {
      console.log('ERROR')
    }
    )

    this.invoiceForm.reset;
    this.segmentIterator = 0;
    this.segmentList = [];
    window.location.reload;

  };

}
