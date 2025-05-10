import { Component, Inject, OnInit } from '@angular/core';
import { OKTA_CONFIG, OktaAuthStateService } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent implements OnInit {
  isAuthenticated = false;

  userFullName: string | undefined = '';

  constructor(
    @Inject(OKTA_CONFIG) public oktaAuth: OktaAuth, // Use OktaAuth instance directly
    private oktaAuthStateService: OktaAuthStateService
  ) {}

  ngOnInit(): void {
    this.oktaAuthStateService.authState$.subscribe((result) => {
      this.isAuthenticated = result.isAuthenticated || false;
      this.userFullName = result.isAuthenticated ? result.idToken?.claims.name : '';
    });
  }

  login() {
    this.oktaAuth.signInWithRedirect();
  }

  logout() {
    this.oktaAuth.signOut();
  }
}
