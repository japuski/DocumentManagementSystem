import { Person } from "./Person";
import { Segment } from "./Segment";

export class Invoice {

  invoiceNumber: number;
  seller: Person;
  buyer: Person;
  dateOfIssue: String;
  dateOfSale: String;
  segments: Array<Segment>;


  constructor(invoiceNumber: number, seller: Person, buyer: Person, dateOfIssue: String, dateOfSale: String, segments: Array<Segment>) {
    this.invoiceNumber = invoiceNumber;
    this.seller = seller;
    this.buyer = buyer;
    this.dateOfIssue = dateOfIssue;
    this.dateOfSale = dateOfSale;
    this.segments = segments;
  }

}
