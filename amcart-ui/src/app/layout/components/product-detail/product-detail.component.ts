import { LocalStorageKeys, Product, ProductService } from '@amcart/core';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { CartService } from 'src/app/core/services/cart.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent implements OnInit, OnDestroy {

  productId: number;
  product: Product;
  subscription: Subscription;

  cartId: string;

  constructor(
    private readonly productService: ProductService,
    private readonly cartService: CartService,
    private readonly route: ActivatedRoute) { }

  ngOnInit() {
    this.cartId = localStorage.getItem(LocalStorageKeys.CART_ID);
    this.subscription = this.productService.getProductDetails(this.route.snapshot.paramMap.get('productId'))
      .subscribe(product => {
        this.product = product;
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

  isProductInStock(stock) {
    return stock > 0 ? 'In Stock' : 'Not In Stock';
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
    this.subscription = null;
  }

}
