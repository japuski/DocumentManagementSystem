import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AddInvoice2Component } from './add-invoice2/add-invoice2.component';
import { AddInvoiceComponent } from './add-invoice/add-invoice.component';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';
import { AuthComponent } from './auth/auth.component';


const routes: Routes = [
  { path: 'add-invoice2', component: AddInvoice2Component },
  { path: 'add-invoice', component: AddInvoiceComponent },
  { path: 'invoice-list', component: InvoiceListComponent },
  { path: 'auth', component: AuthComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
