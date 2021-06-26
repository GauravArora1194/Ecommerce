import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageKeys } from '../enums';
import { Cart } from '../models/cart-detail.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private CART_SERVICE_BASE_URL = "http://35.223.97.142:8080/cart";

  constructor(private readonly http: HttpClient) { }

  public getUserCart(cartId: string): Observable<Cart> {
    const url = `${this.CART_SERVICE_BASE_URL}/${cartId}`;
    return this.http.get<Cart>(url, {
      headers: this.createHttpHeaders()
    });
  }

  public createUserCart(): Observable<Cart> {
    return this.http.post<Cart>(this.CART_SERVICE_BASE_URL, {}, {
      headers: this.createHttpHeaders()
    });
  }

  public addProductToCart(productId: string, cartId: string): Observable<Cart> {
    const url = `${this.CART_SERVICE_BASE_URL}/add?code=${productId}&quantity=1&cartId=${cartId}`;
    return this.http.post<Cart>(url, {}, {
      headers: this.createHttpHeaders()
    });
  }

  public decProductToCart(productId: string, cartId: string, quantity?: number): Observable<Cart> {
    if (quantity == 1) {
      this.removeProductFromCart(productId, cartId, quantity);
    }

    const url = `${this.CART_SERVICE_BASE_URL}?code=${productId}&quantity=1&cartId=${cartId}`;
    return this.http.delete<Cart>(url, {
      headers: this.createHttpHeaders()
    });
  }

  public removeProductFromCart(productId: string, cartId: string, quantity?: number): Observable<Cart> {
    const url = `${this.CART_SERVICE_BASE_URL}?code=${productId}&quantity=${quantity}&cartId=${cartId}`;
    return this.http.delete<Cart>(url, {
      headers: this.createHttpHeaders()
    });
  }

  private createHttpHeaders(): HttpHeaders {
    const userId = LocalStorageKeys.USER_ID + localStorage.getItem(LocalStorageKeys.USERNAME) +
      '.' + 'idToken';
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem(userId)
    });
  }

}
