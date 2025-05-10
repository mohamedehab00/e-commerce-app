import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/commons/product';
import { ProductCategory } from 'src/app/commons/product-category';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  productId: number = 0;
  product: Product = new Product(0, '', '', '', 0, '', false, 0, new Date(), new Date(), new ProductCategory(0, '', []));

  constructor(public productService:ProductService, public cartService:CartService, public route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.productId = +params.get('id')!;
      console.log(this.productId);
      this.getProductDetail();
    });
  }

  getProductDetail() {
    this.productService.getProductById(this.productId).subscribe((data) => {
      this.product = data;
    });
  }

  addToCart(product: Product) {
    this.cartService.addToCart(product);
  }
}
