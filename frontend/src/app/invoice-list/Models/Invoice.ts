import { Person } from "./Person";
import { Segment } from "./Segment";

export class Invoice {

  invoiceNumber: String;
  invoiceType: String;
  seller: Person;
  buyer: Person;
  dateOfIssue: Date;
  dateOfSale: Date;
  segments: Array<Segment>;
  currency: String;
  paymentDeadline: Date;
  paymentMethod: String;
  accountNumber: String;
  bank: String;
  transferComment: String;
  amountPaid: number;


  constructor(invoiceNumber: String, invoiceType: String, seller: Person, buyer: Person, dateOfIssue: Date, dateOfSale: Date, segments: Array<Segment>, currency: String, paymentDeadline: Date, paymentMethod: String, accountNumber: String, bank: String, transferComment: String, amountPaid: number) {
    this.invoiceNumber = invoiceNumber;
    this.invoiceType = invoiceType;
    this.seller = seller;
    this.buyer = buyer;
    this.dateOfIssue = dateOfIssue;
    this.dateOfSale = dateOfSale;
    this.segments = segments;
    this.currency = currency;
    this.paymentDeadline = paymentDeadline;
    this.paymentMethod = paymentMethod;
    this.accountNumber = accountNumber;
    this.bank = bank;
    this.transferComment = transferComment;
    this.amountPaid = amountPaid;
  }

}
