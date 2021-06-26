import { CartService, LocalStorageKeys, Product, ProductService } from '@amcart/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { OrderService } from 'src/app/core/services/order.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-search-product',
  templateUrl: './search-product.component.html',
  styleUrls: ['./search-product.component.scss']
})
export class SearchProductComponent implements OnInit {

  products: Product[];

  searchText: string;
  subscription: Subscription;

  cartId: string;

  constructor(private readonly router: Router,
    private readonly cartService: CartService,
    private readonly productService: ProductService,
    private readonly orderService: OrderService
  ) { }

  ngOnInit() {
    this.cartId = localStorage.getItem(LocalStorageKeys.CART_ID);
    this.getProducts();
  }

  private getProducts() {
    const text = this.searchText ? this.searchText : null;
    this.subscription = this.productService.getProducts(text).subscribe(products => {
      this.products = products;
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
    this.subscription = null;
  }

  viewProduct(productId: number): void {
    this.router.navigateByUrl('/home/products/' + productId);
  }

  searchProducts(): void {
    const text = this.searchText ? this.searchText : null;

    this.productService.getProducts(text).subscribe(products => {
      this.products = products;
    });
  }

  addProductToCart(productId: string, productName: string): void {
    this.cartService.addProductToCart(productId, this.cartId)
      .toPromise()
      .then(() => {
        Swal.fire({
          title: 'Success',
          text: `${productName} has been added successfully.`,
          icon: 'success',
          showCancelButton: false,
          showCloseButton: true,
          showConfirmButton: false
        })
      });
  }

}
