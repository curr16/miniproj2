import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/User.service';

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent implements OnInit{
  userFullName!: string;
  userEmail! : string;

  // storage: Storage = localStorage
  storage: Storage = sessionStorage

  ngOnInit(): void {
      this.userSvc.currentUser.subscribe((user: string) => {
        this.userEmail = user;
      })
  }

  constructor(private userSvc : UserService) {
    // Read data from storage
    let data = JSON.parse(this.storage.getItem('useraccount')!);
    if (data) {
      this.userFullName = data?.firstName + " " + data?.lastName;
      this.userEmail = data?.email;
    }
  }
}
