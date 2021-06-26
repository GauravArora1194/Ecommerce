import { Component, NgZone, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Hub } from '@aws-amplify/core'
import { Auth, CognitoHostedUIIdentityProvider } from '@aws-amplify/auth'

import { NgxSpinnerService } from 'ngx-spinner';
import { LocalStorageKeys } from '@amcart/core';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  username: string;
  email: string;
  admin: boolean;

  constructor(private router: Router,
    private spinner: NgxSpinnerService, private zone: NgZone) {

    this.email = "";

    Hub.listen("auth", ({ payload: { event, data } }) => {
      if (event === "cognitoHostedUI" || event === "signedIn") {
        this.zone.run(() => this.router.navigate(['/home']));
      } else {
        this.spinner.hide();
      }
    });

    Auth.currentAuthenticatedUser()
      .then(user => {
        this.email = user.attributes.email;
        this.username = user.username;
        localStorage.setItem(LocalStorageKeys.USERNAME, this.username);
        if (user.attributes['custom:isAdmin'] === 'Yes') {
          this.admin = true;
        }
        this.router.navigate(['/home'], { replaceUrl: true });
      })
      .catch(() => {
        this.spinner.hide();
      });
  }

  ngOnInit() {
    this.spinner.hide();
  }

  onLogoutClick() {
    this.spinner.show();
    Auth.signOut({ global: true })
      .then(data => console.log(data))
      .catch(err => console.log(err));
  }

  onLoginClick() {
    this.spinner.show();
    Auth.federatedSignIn();
  }

  onLoginClickGoogle() {
    this.spinner.show();
    Auth.federatedSignIn({
      provider: CognitoHostedUIIdentityProvider.Google
    });
  }

  onLoginClickFacebook() {
    this.spinner.show();
    Auth.federatedSignIn({
      provider: CognitoHostedUIIdentityProvider.Facebook
    });
  }

}
