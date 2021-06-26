import { CartService, LocalStorageKeys } from '@amcart/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Cart } from 'src/app/core/models/cart-detail.model';
import { OrderService } from 'src/app/core/services/order.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
})
export class CheckoutComponent implements OnInit {

  addressForm: FormGroup;

  showFormErrors = false;

  sameAddress = false;

  showShippingAddress = true;
  cartId: string;
  cart: Cart;

  constructor(private readonly formBuilder: FormBuilder,
    private readonly orderService: OrderService,
    private readonly cartService: CartService,
    private readonly router: Router) { }

  ngOnInit(): void {
    this.initializeAddressForm();
    this.subscribeToValueChanges();
    this.cartId = localStorage.getItem(LocalStorageKeys.CART_ID);
    this.cartService.getUserCart(this.cartId)
      .subscribe(cart => {
        this.cart = cart;
      });
  }

  public get billingAddressForm(): FormGroup {
    return this.addressForm.get('billingAddressForm') as FormGroup;
  }

  public get shippingAddressForm(): FormGroup {
    return this.addressForm.get('shippingAddressForm') as FormGroup;
  }

  public get paymentDetailsForm(): FormGroup {
    return this.addressForm.get('paymentDetails') as FormGroup;
  }

  initializeAddressForm() {
    this.addressForm = this.formBuilder.group({
      sameAddress: false,
      billingAddressForm: this.formBuilder.group({
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
        address: ['', [Validators.required]],
        address2: '',
        country: ['', [Validators.required]],
        state: ['', [Validators.required]],
        pincode: [
          '',
          [
            Validators.required,
            Validators.maxLength(6),
            Validators.minLength(6),
          ],
        ],
      }),
      shippingAddressForm: this.formBuilder.group({
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
        address: ['', [Validators.required]],
        address2: '',
        country: ['', [Validators.required]],
        state: ['', [Validators.required]],
        pincode: [
          '',
          [
            Validators.required,
            Validators.maxLength(6),
            Validators.minLength(6),
          ],
        ],
      }),
      paymentDetails: this.formBuilder.group({
        paymentType: ['CREDIT_CARD', [Validators.required]],
        name: ['', [Validators.required]],
        cardNumber: ['', [Validators.required]],
        cvv: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3)]],
        expiration: ['', [Validators.required]]
      })
    });

  }

  subscribeToValueChanges() {
    this.addressForm.get('sameAddress').valueChanges.subscribe(value => {
      this.showShippingAddress = !value;
    });
  }

  submitForm() {
    let validForm = this.billingAddressForm.valid && this.paymentDetailsForm.valid;
    const sameAddress = this.addressForm.get('sameAddress').value;
    if (!sameAddress) {
      validForm = validForm && this.shippingAddressForm.valid;
    }

    if (validForm) {
      this.showFormErrors = false;
      this.orderService.placeOrder(this.cart)
        .toPromise()
        .then((value) => {
          Swal.fire({
            title: 'Success',
            text: `Order with order id: ${value.orderNumber} has been placed successfully.`,
            icon: 'success',
            showCancelButton: false,
            showCloseButton: true,
            showConfirmButton: false
          })
          this.router.navigateByUrl('/home');
        });
    } else {
      this.showFormErrors = true;
    }
  }
}
