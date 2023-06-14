export class Product {
  productName: String;
  pkwiu: String;
  unit: String;
  nettoPrice: number;
  taxRate: number;

  constructor(productName: String, pkwiu: String, unit: String, nettoPrice: number, taxRate: number) {
    this.productName = productName;
    this.pkwiu = pkwiu;
    this.unit = unit;
    this.nettoPrice = nettoPrice;
    this.taxRate = taxRate;
  }
    
}
