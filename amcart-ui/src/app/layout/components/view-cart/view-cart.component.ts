import { CartService, LocalStorageKeys } from '@amcart/core';
import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Cart } from 'src/app/core/models/cart-detail.model';

@Component({
  selector: 'app-view-cart',
  templateUrl: './view-cart.component.html',
  styleUrls: ['./view-cart.component.scss']
})
export class ViewCartComponent implements OnInit, OnDestroy {

  cart: Cart;
  cartId: string;

  private subscription: Subscription = new Subscription();

  constructor(private readonly http: HttpClient,
    private readonly cartService: CartService,
    private readonly router: Router) { }

  ngOnInit() {
    this.cartId = localStorage.getItem(LocalStorageKeys.CART_ID);
    this.subscription = this.cartService.getUserCart(this.cartId)
      .subscribe(cart => {
        this.cart = cart;
      });
  }

  increaseProduct(productId: string): void {
    this.cartService.addProductToCart(productId, this.cartId)
      .subscribe((cart: Cart) => this.cart = cart);
  }

  decreaseProduct(productId: string, quantity: number): void {
    this.cartService.decProductToCart(productId, this.cartId, quantity)
      .subscribe((cart: Cart) => this.cart = cart);
  }

  deleteProduct(productId: string, quantity: number): void {
    this.cartService.removeProductFromCart(productId, this.cartId, quantity)
      .subscribe((cart: Cart) => this.cart = cart);
  }

  proceedToCheckout() {
    this.router.navigateByUrl('/home/checkout');
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
    this.subscription = null;
  }
}
