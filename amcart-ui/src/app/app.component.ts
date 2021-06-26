import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { LocalStorageKeys } from './core/enums';
import { Cart } from './core/models/cart-detail.model';
import { CartService } from './core/services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  cartId: string;
  subscription: Subscription;

  constructor(private readonly cartService: CartService) {
  }

  ngOnInit() {
    this.cartId = localStorage.getItem(LocalStorageKeys.CART_ID);
    if (this.cartId == null || this.cartId == undefined) {
      this.subscription = this.cartService.createUserCart()
        .subscribe((cart: Cart) => {
          localStorage.setItem(LocalStorageKeys.CART_ID, cart.number);
        });
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
    this.subscription = null;
  }

}
