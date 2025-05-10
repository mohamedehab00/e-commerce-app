import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { OrderConfirmationComponent } from './components/order-confirmation/order-confirmation.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login.component';
import { OktaAuthGuard, OktaCallbackComponent } from '@okta/okta-angular';
import { ProtectedSectionComponent } from './components/protected-section/protected-section.component';

const routes: Routes = [
  { path: 'protected', component: ProtectedSectionComponent, canActivate: [OktaAuthGuard] },
  {path: 'login/callback', pathMatch: 'full', component: OktaCallbackComponent},
  {path: 'login', pathMatch: 'full', component: LoginComponent},
  {path: 'checkout/confirmation', pathMatch: 'full', component: OrderConfirmationComponent},
  {path: 'checkout', pathMatch: 'full', component: CheckoutComponent},
  {path: 'cart', pathMatch: 'full', component: CartDetailsComponent},
  {path: 'products/product/:id', pathMatch: 'full', component: ProductDetailComponent},
  {path: 'products/search', component: ProductListComponent},
  {path: 'products', pathMatch: 'full', component: ProductListComponent},
  {path: 'products/category/:id', pathMatch: 'full', component: ProductListComponent},
  {path: '', redirectTo: '/products', pathMatch: 'full'},
  {path: '**', pathMatch: 'full', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
