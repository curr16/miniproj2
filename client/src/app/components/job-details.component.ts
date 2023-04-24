import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  defaultImage: string = "assets/images/placeholder.png"


  constructor(
    private activatedRoute: ActivatedRoute,
    private jobSvc: JobListingService,
    private userSvc: UserService,
    private router: Router,
    

  ) {}

  ngOnInit(): void {
    
    // get job id from current route
    this.routeSub$ = this.activatedRoute.params.subscribe((params) => {
      this.job_id = params['job_id'];
      this.email = this.userSvc.email
      this.jobSvc
      .getJobById(this.job_id)
      .then((res) => {
        this.details = res as JobDetails[];
        if(res[0].employer_logo == null || res[0].employer_logo == "ul") {
          res[0].employer_logo = this.defaultImage
        }
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

  applyJob(i: number) {
    this.router.navigate(['/applyJob', this.details[i].job_id])
  }

  ngOnDestroy(): void {
    this.routeSub$.unsubscribe();
  }
}


