import { Component, NgZone } from '@angular/core';

import { Router } from '@angular/router';

import { Hub } from '@aws-amplify/core'
import { Auth, CognitoHostedUIIdentityProvider } from '@aws-amplify/auth'

import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  // constructor(private router: Router,
  //   private zone: NgZone,
  //   private spinner: NgxSpinnerService) {


  // }

  // onLoginClick() {
  //   this.spinner.show();
  //   Auth.federatedSignIn();
  // }

  // onLoginClickGoogle() {
  //   this.spinner.show();
  //   Auth.federatedSignIn({
  //     provider: CognitoHostedUIIdentityProvider.Google
  //   });
  // }

  // onLoginClickFacebook() {
  //   this.spinner.show();
  //   Auth.federatedSignIn({
  //     provider: CognitoHostedUIIdentityProvider.Facebook
  //   });
  // }

}
