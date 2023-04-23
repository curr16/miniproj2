import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/User.model';
import { UserService } from '../service/User.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  user!: User
  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userSvc: UserService
    
    ) {}

  ngOnInit(): void {
  // initialize form
  this.form = this.createForm()

  }

    createForm(): FormGroup{
    return this.fb.group({
    firstName: this.fb.control('',[Validators.required]),
    lastName: this.fb.control('',[Validators.required]),
    email: this.fb.control('',[Validators.required]),
    password: this.fb.control('',[Validators.required]),
    
  });}

  submitForm() {
    this.user = this.form.value as User
    console.info(this.user)
    this.userSvc.getuserDetails(this.form.value)
    this.clearForm()  
  }

  clearForm(){
    this.form.reset()

  }

}