import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { LocalStorageKeys } from '../enums';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private ORDER_SERVICE_BASE_URL = "http://35.223.97.142:8080/order";

  constructor(private readonly http: HttpClient) { }

  public testServiceCommunication(): Observable<any> {
    const url = environment.api.orderService + 'test'
    return this.http.get<any>(url, {
      headers: this.createHttpHeaders()
    });
  }


  public placeOrder(cart: any): Observable<any> {
    return this.http.post<any>(this.ORDER_SERVICE_BASE_URL, cart, {
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
