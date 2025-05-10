import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LookupsService {
  host = 'http://localhost:8080';

  constructor(private http:HttpClient) { }

  getPaymentMethods() {
    return this.http.get<any[]>(`${this.host}/api/lookup/payment-methods`);
  }
  getCountries() {
    return this.http.get<any[]>(`${this.host}/api/lookup/countries`);
  }
}
