import { Component, Input } from '@angular/core';
import { ControlContainer, FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
  selector: 'app-add-invoice',
  templateUrl: './add-invoice.component.html',
  styleUrls: ['./add-invoice.component.css'],
  viewProviders: [{ provide: ControlContainer, useExisting: FormGroupDirective}]
})
export class AddInvoiceComponent {
  invoiceForm: FormGroup;
  title= "Default title";
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];

  constructor(private fb: FormBuilder) {
    this.invoiceForm = this.fb.group({
      invoiceType: ['', Validators.required],
      invoiceNumber: ['', Validators.required],
      dateOfIssue: ['', Validators.required],
      dateOfSale: ['', Validators.required],

      currency: ['', Validators.required],
      paymentDeadline: [null, Validators.required],
      paymentMethod: ['', Validators.required],
      accountNumber: [null, Validators.required],
      bank: ['', Validators.required],
      transferComment: ['', Validators.required],
      amountPaid: ['', Validators.required],

      buyerName: ['', Validators.required],
      buyerNip: ['', Validators.required],
      buyerStreet: ['', Validators.required],
      buyerNumber: ['', Validators.required],
      buyerPostalCode: ['', Validators.required],
      buyerCity: ['', Validators.required],
      sellerName: ['', Validators.required],
      sellerNip: ['', Validators.required],
      sellerStreet: ['', Validators.required],
      sellerNumber: ['', Validators.required],
      sellerPostalCode: ['', Validators.required],
      sellerCity: ['', Validators.required]
    });
  }

  onSave() {
    if (this.invoiceForm.valid) {
      console.log('Zapisano fakturę:', this.invoiceForm.value);
    }
  }
}
