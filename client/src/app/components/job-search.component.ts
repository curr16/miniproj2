import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../service/User.service';

@Component({
  selector: 'app-job-search',
  templateUrl: './job-search.component.html',
  styleUrls: ['./job-search.component.css']
})
export class JobSearchComponent implements OnInit {

  form!: FormGroup;
  email!: string

  // storage: Storage = localStorage
  storage: Storage = sessionStorage

  constructor(private fb: FormBuilder, private router: Router, private userSvc: UserService) {

  }

  ngOnInit(): void {
    this.form = this.createForm();
    this.userSvc.currentUser.subscribe((user) => {
      this.email = user
    })
      console.info("email", this.email)
  }

  createForm(): FormGroup {
    return this.fb.group({
      jobStartsWith: this.fb.control('', [Validators.required]),
    });
  }


  submitForm(): void {
    // on submit, route to jobs?jobStartsWith=designer
    const jobStartsWith = this.form.get('jobStartsWith')?.value;
    this.router.navigate(['jobs'], {
      queryParams: {
        jobStartsWith: jobStartsWith,
      },
    });
  }
}

