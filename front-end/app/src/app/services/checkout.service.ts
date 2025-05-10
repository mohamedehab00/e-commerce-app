import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  private baseUrl = environment.apiUrl+'/checkout';

  constructor(private http:HttpClient) { }

  submitOrder(order: any) {
    return this.http.post<any>(`${this.baseUrl}/purchase`, order);
  }
}
