export interface Product {
  code: string;
  name: string;
  price: number;
  tax: number;
  discount: number
  description: string;
  stock: number;
  brand: string;
  color: string;
  imageUrl: string;
  categories: Array<Object>;
}
