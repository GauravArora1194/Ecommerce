import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CalendarModule } from 'primeng/calendar';
import { InputMaskModule } from 'primeng/inputmask';

import { LayoutRoutingModule } from './layout.routing.module';
import { EmptyPageComponent } from './pages/empty-page/empty-page.component';
import { SharedModule } from '../shared/shared.module';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { PrimeComponentsModule } from './prime-components.module';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { ViewCartComponent } from './components/view-cart/view-cart.component';
import { SearchProductComponent } from './components/search-product/search-product.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { LoginComponent } from './components/login/login.component';

const components = [
  ProductDetailComponent,
  ViewCartComponent,
  SearchProductComponent,
  CheckoutComponent,
  LoginComponent,
  MainPageComponent,
  EmptyPageComponent
];
@NgModule({
  declarations: [components],
  imports: [
    CommonModule,
    PrimeComponentsModule,
    LayoutRoutingModule,
    SharedModule,
    CalendarModule,
    InputMaskModule
  ]
})
export class LayoutModule { }
