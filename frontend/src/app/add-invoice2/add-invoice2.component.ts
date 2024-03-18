import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Address } from './Models/Address';
import { Invoice } from './Models/Invoice';
import { MyFormModel } from './Models/MyFormModel';
import { Person } from './Models/Person';
import { Product } from './Models/Product';
import { Segment } from './Models/Segment'
import { InvoiceService } from './Service/invoice.service';

@Component({
  selector: 'app-add-invoice2',
  templateUrl: './add-invoice2.component.html',
  styleUrls: ['./add-invoice2.component.css']
})
export class AddInvoice2Component {

  private segmentIterator: number = 0;
  segmentList: Segment[] = [];
  invoiceForm: FormGroup;
  invoiceNumbers: number[] = [];
  invoice: Invoice | undefined;
  inputInvoiceValue1: number = 0;
  inputInvoiceValue2: number = 0;
  inputInvoiceValue3: number = 0;

  constructor(
    private invoiceService: InvoiceService,
    private route: ActivatedRoute
  ) {
    this.invoiceForm = new FormGroup({

      invoiceNumber: new FormControl(''),
      invoiceType: new FormControl(''),
      dateOfIssue: new FormControl(''),
      dateOfSale: new FormControl(''),
      currency: new FormControl(''),
      paymentDeadline: new FormControl(''),
      paymentMethod: new FormControl(''),
      accountNumber: new FormControl(''),
      bank: new FormControl(''),
      transferComment: new FormControl(''),
      amountPaid: new FormControl(''),


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

  ngOnInit(): void {
    this.invoiceService.getInvoicesByNumber().subscribe(
      numbers => {
        this.invoiceNumbers = numbers;
        this.inputInvoiceValue1 = this.invoiceNumbers[0];
        this.inputInvoiceValue2 = this.invoiceNumbers[0];
        this.inputInvoiceValue3 = this.invoiceNumbers[0];
      },
      error => {
        console.error('Error fetching invoice numbers:' + this.invoiceNumbers, error);
      }
    );


  }

  addProduct(): void {
    this.segmentList.push(new Segment(0, new Product("", "", "", 0, 0)));
    this.invoiceForm.addControl('quantity_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('productName_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('pkwiu_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('unit_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('netPrice_' + this.segmentIterator, new FormControl(''));
    this.invoiceForm.addControl('vatRate_' + this.segmentIterator, new FormControl(''));
    this.segmentIterator = this.segmentIterator + 1;
    console.log(JSON.stringify(this.segmentList))
  };

  removeSegment(index: number): void {
    /*reindeksowanie */
    console.log('INDEX: ' + index);
  };

  onSubmit(): void {

    let invoiceNumber = this.invoiceForm.get('invoiceNumber')?.value;
    let invoiceType = this.invoiceForm.get('invoiceType')?.value;
    let dateOfIssue = this.invoiceForm.get('dateOfIssue')?.value;
    let dateOfSale = this.invoiceForm.get('dateOfSale')?.value;
    let currency = this.invoiceForm.get('currency')?.value;
    let paymentDeadline = this.invoiceForm.get('paymentDeadline')?.value;
    let paymentMethod = this.invoiceForm.get('paymentMethod')?.value;
    let accountNumber = this.invoiceForm.get('accountNumber')?.value;
    let bank = this.invoiceForm.get('bank')?.value;
    let transferComment = this.invoiceForm.get('transferComment')?.value;
    let amountPaid = this.invoiceForm.get('amountPaid')?.value;


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
            this.invoiceForm.get('netPrice_' + i)?.value,
            this.invoiceForm.get('vatRate_' + i)?.value
          )))
    };

    let invoice: Invoice = new Invoice(invoiceNumber, invoiceType, seller, buyer, dateOfIssue, dateOfSale, segments, currency, paymentDeadline, paymentMethod, accountNumber, bank, transferComment, amountPaid);




    this.invoiceService.addInvoice(invoice).subscribe(value => {
      console.log('OK')
    }, error => {
      console.log('ERROR adInvoice()')
    }
    )

    this.invoiceForm.reset;
    this.segmentIterator = 0;
    this.segmentList = [];
    window.location.reload;

  };


  getInvoiceJSON(invoiceId: number): void {

    this.invoiceService.getInvoice(invoiceId).subscribe(invoice => {
      this.invoice = invoice;
    },

      error => {
        console.error('Error showInvoice:', error);
      }
    )

  }

  getInvoiceFile(invoiceId: number): void {

    this.invoiceService.getInvoiceFile(invoiceId).subscribe((data) => {
      const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'plik.pdf';
      document.body.appendChild(a);
      a.click();
      window.URL.revokeObjectURL(url);
    });

  }

  generateInvoice(invoiceId: number): void {

    this.invoiceService.generateInvoice(invoiceId).subscribe(value => {
      console.log('OK')
    },

      error => {
        console.error('Error showInvoice:', error);
      }
    )
  }

}
