import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart-status',
  templateUrl: './cart-status.component.html',
  styleUrls: ['./cart-status.component.css']
})
export class CartStatusComponent implements OnInit {
  
  totalCost: number = 0;
  totalItems: number = 0;

  constructor(public cartService:CartService, public route:Router) { }

  ngOnInit(): void {
    this.updateCartStatus();
  }

  updateCartStatus() {
    this.cartService.totalPrice.subscribe((data) => {
      this.totalCost = data;
    });
    this.cartService.totalQuantity.subscribe((data) => {
      this.totalItems = data;
    });
  }

  routeToCart() {
    this.route.navigate(['/cart']);
  }
}
