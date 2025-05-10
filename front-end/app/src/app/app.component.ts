import { Component } from '@angular/core';
import { ProductCategory } from './commons/product-category';
import { ProductService } from './services/product.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  
  constructor() {}

  ngOnInit(): void {
  }
}
