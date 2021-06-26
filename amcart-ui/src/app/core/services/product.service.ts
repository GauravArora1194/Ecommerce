import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageKeys } from '../enums';
import { Product } from '../models';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private PRODUCT_SERVICE_BASE_URL = "http://35.223.97.142:8080/products";
  private SEARCH_SERVICE_BASE_URL = "http://35.223.97.142:8080/search";

  constructor(private readonly http: HttpClient) { }

  public getProductDetails(productId: string): Observable<Product> {
    return this.http.get<Product>(this.PRODUCT_SERVICE_BASE_URL + '/' + productId, {
      headers: this.createHttpHeaders()
    });
  }

  public getProducts(searchtText: string): Observable<Product[]> {
    const text = searchtText || 'shirt';
    return this.http.get<Product[]>(this.SEARCH_SERVICE_BASE_URL + '/' + text, {
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
