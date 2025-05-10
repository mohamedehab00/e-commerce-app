import { state } from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { zip } from 'rxjs';
import { City } from 'src/app/commons/city';
import { CartService } from 'src/app/services/cart.service';
import { CheckoutService } from 'src/app/services/checkout.service';
import { LookupsService } from 'src/app/services/lookups.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  checkoutForm: FormGroup;

  totalCost: number = 0;
  totalItems: number = 0;

  PAYMENT_METHOD : any[] = [];
  COUNTRIES : any[] = [];
  ALL_CITIES : Map<number, Object[]> = new Map<number, any[]>();
  SHIPPING_CITIES : any[] = [];
  BILLING_CITIES : any[] = [];

  isCardPaymentType: boolean = false;
  isBillingIsNotSameAsShipping: boolean = false;

  constructor(public formBuilder: FormBuilder, 
    public cartService: CartService, 
    private http:HttpClient, 
    private lookupsService: LookupsService, 
    private checkoutService: CheckoutService,
    private router: Router) {   
    this.checkoutForm = this.formBuilder.group({}); 
    this.initLookups();
  }

  ngOnInit(): void {
    this.initForm();
    this.cartService.totalPrice.subscribe((data) => {
      this.totalCost = data;
    });
    this.cartService.totalQuantity.subscribe((data) => {
      this.totalItems = data;
    }
    );
  }

  submitPurchase() {
    console.log('Form Submitted', this.checkoutForm.value);
    
    this.checkoutService.submitOrder(this.checkoutForm.value).subscribe((data) => {
      this.cartService.cartItems = [];
      this.cartService.updateTotalPrice();
      this.cartService.updateTotalQuantity();
      this.checkoutForm.reset();
      this.router.navigate(['/checkout/confirmation'], {queryParams: {trackingNo: data.trackingNo}});
    });
  }

  initForm() {
    this.checkoutForm = this.formBuilder.group({
      customerDetails: this.formBuilder.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        phone: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
      }),
      shippingDetails: this.formBuilder.group({
        street: ['', Validators.required],
        country: ['', Validators.required],
        city: ['', Validators.required],
        zip: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
      }),
      billingDetails: this.formBuilder.group({
        street: [''],
        country: [''],
        city: [''],
        zip: [''],
      }),
      sameAsShipping: [true, Validators.required],
      paymentType: ['1', Validators.required],
      cardDetails: this.formBuilder.group({
        cardNumber: [''],
        cardName: [''],
        cardExpiration: [''],
        cardCvv: [''],
      }),
      cart:[this.cartService.cartItems, Validators.required],
    });
    this.handlePaymentType();
    this.handleSameAsShipping();
  }

  handlePaymentType() {
    let paymentType = Number(this.checkoutForm.get('paymentType')?.value);

    console.log('Payment Type:', paymentType);
    console.log('Card ID:', this.getCardIdFromPaymentMethod());

    if (this.getCardIdFromPaymentMethod() === paymentType) {
      this.isCardPaymentType = true;
      this.checkoutForm.get('cardDetails')?.get('cardNumber')?.setValidators([Validators.required, Validators.minLength(16), Validators.maxLength(16)]);
      this.checkoutForm.get('cardDetails')?.get('cardName')?.setValidators([Validators.required]);
      this.checkoutForm.get('cardDetails')?.get('cardExpiration')?.setValidators([Validators.required]);
      this.checkoutForm.get('cardDetails')?.get('cardCvv')?.setValidators([Validators.required, Validators.minLength(3), Validators.maxLength(3), Validators.pattern('^[0-9]*$')]);
    }
    else{
      this.isCardPaymentType = false;
      this.checkoutForm.get('cardDetails')?.clearValidators();
      this.checkoutForm.get('cardDetails')?.reset();
    }

    this.checkoutForm.get('cardDetails')?.get('cardNumber')?.updateValueAndValidity();
    this.checkoutForm.get('cardDetails')?.get('cardName')?.updateValueAndValidity();
    this.checkoutForm.get('cardDetails')?.get('cardExpiration')?.updateValueAndValidity();
    this.checkoutForm.get('cardDetails')?.get('cardCvv')?.updateValueAndValidity();
  }

  handleSameAsShipping() {
    if (this.checkoutForm.get('sameAsShipping')?.value) {
      this.isBillingIsNotSameAsShipping = false;
      this.checkoutForm.get('billingDetails')?.get('street')?.clearValidators();
      this.checkoutForm.get('billingDetails')?.get('country')?.clearValidators();
      this.checkoutForm.get('billingDetails')?.get('city')?.clearValidators();
      this.checkoutForm.get('billingDetails')?.get('zip')?.clearValidators();
      this.checkoutForm.get('billingDetails')?.get('street')?.reset();
      this.checkoutForm.get('billingDetails')?.get('country')?.reset();
      this.checkoutForm.get('billingDetails')?.get('city')?.reset();
      this.checkoutForm.get('billingDetails')?.get('zip')?.reset();
    } else {
      this.isBillingIsNotSameAsShipping = true;
      this.checkoutForm.get('billingDetails')?.get('street')?.setValidators([Validators.required]);
      this.checkoutForm.get('billingDetails')?.get('country')?.setValidators([Validators.required]);
      this.checkoutForm.get('billingDetails')?.get('city')?.setValidators([Validators.required]);
      this.checkoutForm.get('billingDetails')?.get('zip')?.setValidators([Validators.required, Validators.pattern('^[0-9]*$')]);
    }
    this.checkoutForm.get('billingDetails')?.get('street')?.updateValueAndValidity();
    this.checkoutForm.get('billingDetails')?.get('country')?.updateValueAndValidity();
    this.checkoutForm.get('billingDetails')?.get('city')?.updateValueAndValidity();
    this.checkoutForm.get('billingDetails')?.get('zip')?.updateValueAndValidity();
  }

  getCardIdFromPaymentMethod() : number {
    let cardId: number = -1;
    this.PAYMENT_METHOD.forEach((item) => {
      if (item.label.toLocaleLowerCase().includes('card')) {
        cardId = item.value;
        return;
      }
    });
    return cardId;
  }

  initLookups(){
    this.lookupsService.getPaymentMethods().subscribe((data) => {
      data.forEach((item) => {
        this.PAYMENT_METHOD.push({label: item.name, value: item.id});
      })
    });

    this.lookupsService.getCountries().subscribe((data) => {
      data.forEach((item) => {
        this.COUNTRIES.push({label: item.name, value: item.id, cities: item.cities});
      });
    });
  }

  handleShippingCountry(){
    let countryId = Number(this.checkoutForm.get('shippingDetails')?.get('country')?.value);
    this.SHIPPING_CITIES = [];
    this.COUNTRIES.forEach((item) => {
      if (item.value === countryId) {
        let cities = item.cities as City[];
        cities.forEach((city) => {
          this.SHIPPING_CITIES.push({label: city.name, value: city.id});
        });
      }
    });
  }

  handleBillingCountry(){
    let countryId = Number(this.checkoutForm.get('billingDetails')?.get('country')?.value);
    this.BILLING_CITIES = [];
    this.COUNTRIES.forEach((item) => {
      if (item.value === countryId) {
        let cities = item.cities as City[];
        cities.forEach((city) => {
          this.BILLING_CITIES.push({label: city.name, value: city.id});
        });
      }
    });
  }
}
