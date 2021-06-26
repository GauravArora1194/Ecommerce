import { CartStatus, Currency } from "../enums";

export interface Cart {
  number: string;
  userId: string;
  currency: string;
  date: Date;
  totalPrice: number;
  totalTax: number;
  discount: number;
  deliveryCost: number;
  subTotal: number;
  status: CartStatus;
  entries: Array<CartEntry>;
}

export interface CartEntry {
  entryNumber: number;
  code: string;
  quantity: number;
  basePrice: number;
  totalPrice: number;
  currency: Currency;
  discount: number;
  tax: number;
  name: string;
}
