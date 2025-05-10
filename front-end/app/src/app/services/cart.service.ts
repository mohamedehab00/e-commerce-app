import { Injectable } from '@angular/core';
import { CartItem } from '../commons/cart-item';
import { BehaviorSubject, ReplaySubject, Subject } from 'rxjs';
import { Product } from '../commons/product';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  cartItems: CartItem[] = [];

  storage: Storage = sessionStorage;

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  constructor() {
    this.cartItems = this.parseCartItems();

    // Calculate total price and quantity
    this.updateTotalPrice();
    this.updateTotalQuantity();
  }

  updateTotalPrice() {
    let totalPriceValue: number = 0;
    this.cartItems.forEach((item) => {
      totalPriceValue += item.price * item.quantity;
    });
    this.totalPrice.next(totalPriceValue);
  }
  updateTotalQuantity() {
    let totalQuantityValue: number = 0;
    this.cartItems.forEach((item) => {
      totalQuantityValue += item.quantity;
    });
    this.totalQuantity.next(totalQuantityValue);
  }

  addToCart(product: Product) {
    console.log(`Adding to cart: ${product.name}`);
    // Implement the logic to add the product to the cart

    let isAlreadyInCart = false;

    this.cartItems.forEach((item) => {
      if (item.id === product.id) {
        item.quantity++;
        isAlreadyInCart = true;
        return;
      }
    });

    if (!isAlreadyInCart) {
      const cartItem = new CartItem(product.id, product.name, product.price, product.imageUrl, 1);
      this.cartItems.push(cartItem);
    }
    this.updateTotalPrice();
    this.updateTotalQuantity();

    this.prsistCartItems();
  }

  removeCartItem(cartItem: CartItem){
    this.cartItems.forEach((item, index) => {
      if (item.id === cartItem.id) {
        this.cartItems.splice(index, 1);
        this.updateTotalPrice();
        this.updateTotalQuantity();
      }
    });

    this.updateTotalPrice();
    this.updateTotalQuantity();

    this.prsistCartItems();
  }

  parseCartItems() {
    return JSON.parse(this.storage.getItem('cartItems') || '[]');
  }

  prsistCartItems() {
    this.storage.setItem('cartItems', JSON.stringify(this.cartItems));
  }
}
