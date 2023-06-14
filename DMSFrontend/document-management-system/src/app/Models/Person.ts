import { Address } from "./Address";

export class Person {
  name: String;
  nip: String;
  address: Address;

  constructor(name: String, nip: String, address: Address) {
    this.name = name;
    this.nip = nip;
    this.address = address;
  }


}
