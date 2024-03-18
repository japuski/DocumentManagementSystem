import { Product } from "./Product";

export class Segment {
  quantity: number;
  product: Product;

  constructor(quantity: number, product: Product) {
    this.quantity = quantity;
    this.product = product;
  }

  public getQuantity(): number {
    return this.quantity;
  }

  public getProduct(): Product {
    return this.product;
  }
}
