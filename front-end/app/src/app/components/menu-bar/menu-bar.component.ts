import { Component, OnInit } from '@angular/core';
import { ProductCategory } from 'src/app/commons/product-category';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {
  categories:ProductCategory[] = [];
  
  constructor(private productService:ProductService) {}

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(){
    this.productService.getProductsCategories().subscribe((data) => {
      this.categories = data;
    });
  }
}
