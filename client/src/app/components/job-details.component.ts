import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JobDetails } from '../models/JobListing.model';
import { JobListingService } from '../service/JobListing.service';
import { UserService } from '../service/User.service';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})

export class JobDetailsComponent implements OnInit, OnDestroy {

  job_id!: string;
  routeSub$!: Subscription;
  details: JobDetails[] = [];
  sub$!: Subscription
  email!: string

  constructor(
    private activatedRoute: ActivatedRoute,
    private jobSvc: JobListingService,
    private userSvc: UserService

  ) {}

  ngOnInit(): void {
    // get job id from current route
    this.routeSub$ = this.activatedRoute.params.subscribe((params) => {
      this.job_id = params['job_id'];
      this.email = this.userSvc.email
      console.info(this.email)
      console.info(this.job_id)
      this.jobSvc
      .getJobById(this.job_id)
      .then((res) => {
        this.details = res;
        console.info(this.details)
      })
      .catch((err) => {
        console.log(err);
      });
    });

    // retrieve job details from server
    
  }

  onSubmit(i: number) {
    this.userSvc.saveJob(this.email, this.details[i])
  }

  ngOnDestroy(): void {
    this.routeSub$.unsubscribe();
  }
}


