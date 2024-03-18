export class Product {
  productName: String;
  pkwiu: String;
  unit: String;
  netPrice: number;
  vatRate: number;

  constructor(productName: String, pkwiu: String, unit: String, netPrice: number, vatRate: number) {
    this.productName = productName;
    this.pkwiu = pkwiu;
    this.unit = unit;
    this.netPrice = netPrice;
    this.vatRate = vatRate;
  }
    
}
