import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { LoginComponent } from './components/login/login.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { SearchProductComponent } from './components/search-product/search-product.component';
import { ViewCartComponent } from './components/view-cart/view-cart.component';
import { EmptyPageComponent } from './pages/empty-page/empty-page.component';
import { MainPageComponent } from './pages/main-page/main-page.component';

// Holds the route of the module.
const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'home',
    component: MainPageComponent,
    children: [
      { path: '', component: SearchProductComponent },
      { path: 'products/:productId', component: ProductDetailComponent },
      { path: 'viewCart', component: ViewCartComponent },
      { path: 'search', component: SearchProductComponent },
      { path: 'checkout', component: CheckoutComponent }
    ],
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  { path: '**', component: EmptyPageComponent, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LayoutRoutingModule {}
