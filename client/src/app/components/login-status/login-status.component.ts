import { Component } from '@angular/core';

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent {
  userFullName = '';

  // storage: Storage = localStorage
  storage: Storage = sessionStorage

  constructor() {
    // Read data from storage
    let data = JSON.parse(this.storage.getItem('useraccount')!);
    if (data) {
      this.userFullName = data?.firstName + " " + data?.lastName
    }
  }
}
