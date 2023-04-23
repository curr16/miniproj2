import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../models/User.model';
import { UserService } from '../service/User.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  
  form!: FormGroup;
  email!: string
  password!: string
  user!: User

  constructor(
    private fb: FormBuilder,
    private userSvc: UserService,
    private router: Router
    
    ) {}

  ngOnInit(): void {
  // initialize form
  this.form = this.createForm()

  }

    createForm(): FormGroup{
    return this.fb.group({
    email: this.fb.control('',[Validators.required]),
    password: this.fb.control('',[Validators.required]),
    
  });}

  async submitForm() {
    this.email = this.form.value.email
    this.password = this.form.value.password
    this.user = await this.userSvc.getLogin(this.email, this.password)
    if (this.user.email!=null) {
      console.info(this.user)
      this.router.navigate(['/search'])
    }
  }

}
