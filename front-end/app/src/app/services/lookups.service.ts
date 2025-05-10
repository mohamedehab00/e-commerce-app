import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LookupsService {
  private baseUrl = environment.apiUrl + '/lookup';

  constructor(private http:HttpClient) { }

  getPaymentMethods() {
    return this.http.get<any[]>(`${this.baseUrl}/payment-methods`);
  }
  getCountries() {
    return this.http.get<any[]>(`${this.baseUrl}/countries`);
  }
}
