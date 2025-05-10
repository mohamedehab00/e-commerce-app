import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private http:HttpClient) { }

  submitOrder(order: any) {
    return this.http.post<any>(`http://localhost:8080/api/checkout/purchase`, order);
  }
}
