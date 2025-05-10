import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../commons/product';
import { Observable } from 'rxjs';
import { ProductCategory } from '../commons/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/api/products';

  constructor(private http:HttpClient) { }

  getProducts(page: number, size:number):Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}?page=${page}&size=${size}`);
  }

  getProductsCategories():Observable<ProductCategory[]> {
    return this.http.get<ProductCategory[]>(`${this.baseUrl}/categories`);
  }

  getProductCategory(currentCategoryId: number, page:number, size:number):Observable<ProductCategory> {
    return this.http.get<ProductCategory>(`${this.baseUrl}/categories/${currentCategoryId}?page=${page}&size=${size}`);
  }

  searchProducts(keyword: string, page:number, size:number) : Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search?keyword=${keyword}&page=${page}&size=${size}`;
    return this.http.get<Product[]>(searchUrl);
  }

  getProductById(productId: number) : Observable<Product> {
    const productUrl = `${this.baseUrl}/product/${productId}`;
    return this.http.get<Product>(productUrl);
  }
}
