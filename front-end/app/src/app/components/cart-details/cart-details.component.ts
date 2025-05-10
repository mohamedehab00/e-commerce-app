import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/commons/cart-item';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {
  totalCost: number = 0;
  totalItems: number = 0;

  constructor(public cartService:CartService, private route:Router) {}

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
    this.cartService.updateTotalPrice();
    this.cartService.updateTotalQuantity();
  }

  removeCartItem(cartItem: CartItem){
    this.cartService.removeCartItem(cartItem);
  }

  changeItemQuantityValue(event: any, itemId: number) {
    this.cartService.cartItems.forEach((item) => {
      if (item.id === itemId) {
        item.quantity = Number(event.target.value);
        this.cartService.updateTotalPrice();
        this.cartService.updateTotalQuantity();
      }
    });
  }

  redirectToCheckout(){
    this.route.navigate(['/checkout']);
  }
}
