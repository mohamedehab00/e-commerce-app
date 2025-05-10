import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbPagination, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { combineLatest } from 'rxjs';
import { CartItem } from 'src/app/commons/cart-item';
import { Product } from 'src/app/commons/product';
import { ProductCategory } from 'src/app/commons/product-category';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  currentCategoryId :any = 1;
  currentCategory: ProductCategory = new ProductCategory(0, '', []);

  currentPage: number = 1;
  pageSize: number = 10;

  constructor(private productService:ProductService, private route:ActivatedRoute, private cartService:CartService) { }

  ngOnInit(): void {
    combineLatest([
      this.route.paramMap,
      this.route.queryParamMap
    ]).subscribe(() => {
      this.listProducts(); // Reacts to any route param or query param change
    });
  }

  listProducts() {
    const hasCategoryId = this.route.snapshot.paramMap.has('id');
    const hasKeyword = this.route.snapshot.queryParamMap.has('keyword');

    console.log(`hasCategoryId=${hasCategoryId}`);
    console.log(`hasKeyword=${hasKeyword}`);

    if (hasCategoryId) {
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
      this.getProductCategory();
    }else if (hasKeyword) {
      const keyword = this.route.snapshot.queryParamMap.get('keyword');
      this.searchProducts(keyword!);
    } 
    else {
      this.getProducts();
    }
  }

  getProducts() {
    console.log(`currentPage=${this.currentPage}`);
    console.log(`pageSize=${this.pageSize}`);
    this.productService.getProducts(this.currentPage, this.pageSize).subscribe((data) => {
      this.products = data;
    });
  }

  getProductCategory() {
    this.productService.getProductCategory(this.currentCategoryId, this.currentPage, this.pageSize).subscribe((data) => {
      this.currentCategory = data;
      this.products = this.currentCategory.products;
    });
  }

  searchProducts(keyword: string) {
    console.log(`keyword=${keyword}`);
    this.productService.searchProducts(keyword, this.currentPage, this.pageSize).subscribe((data) => {
      this.products = data;
    });
  }

  onItemsPerPageChange(event: any) {
    this.pageSize = event.target.value;
    this.currentPage = 0; // Reset to the first page
    this.listProducts();
  }

  onPageChange(page: number) {
    this.currentPage = page;
    this.listProducts();
  }

  addToCart(product: Product) {
    this.cartService.addToCart(product);
  }
}
