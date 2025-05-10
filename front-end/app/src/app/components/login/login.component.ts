import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { OKTA_AUTH } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';
import OktaSignIn from '@okta/okta-signin-widget';
import myAppConfig from 'src/app/config/my-app-config';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  oktaSignIn: any;

  constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth) {
    this.oktaSignIn = new OktaSignIn({
      logo: 'assets/images/logo.png',
      baseUrl: myAppConfig.oidc.issuer.split('/oauth2')[0],
      clientId: myAppConfig.oidc.clientId,
      redirectUri: myAppConfig.oidc.redirectUri,
      features: {
        registration: true // If you want to allow user registration
      },
      authParams: {
        pkce: true, // Enable PKCE
        issuer: myAppConfig.oidc.issuer,
        scopes: myAppConfig.oidc.scopes
      }
    });
  }

  ngOnInit(): void {
    console.log('Initializing Okta Sign-In Widget...');
    const signInWidgetElement = document.getElementById('okta-sign-in-widget');
    
    if (!signInWidgetElement) {
      console.error('The element #okta-sign-in-widget is not found!');
      return;
    }
    
    console.log('Element found:', signInWidgetElement);
  
    this.oktaSignIn.remove(); // Clean up any previous widget instance
  
    this.oktaSignIn.renderEl(
      { el: signInWidgetElement }, // Use the found element directly
      (response: any) => {
        console.log('Okta Sign-In Widget initialized successfully');
        console.log('Response:', response);
        if (response.status === 'SUCCESS') {
          this.oktaAuth.signInWithRedirect({
            sessionToken: response.session.token
          });
        }
      },
      (error: any) => {
        console.error('Error during Okta Sign-In Widget initialization:', error);
        // Show user-friendly error message here
      }
    );
  }
  

  ngOnDestroy(): void {
    // Cleanup when the component is destroyed
    if (this.oktaSignIn) {
      this.oktaSignIn.remove();
    }
  }
}
