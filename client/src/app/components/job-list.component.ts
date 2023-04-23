import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JobListing } from '../models/JobListing.model';
import { JobListingService } from '../service/JobListing.service';
import { UserService } from '../service/User.service';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent {

  query!: string; // passed from search component
  routeSub$!: Subscription;
  pages: number = 2;
  // currLimit: number = 10;
  // currOffset: number = 0;
  jobs: JobListing[] = [];
  email!: string

  defaultImage: string = "assets/images/placeholder.png"
  constructor(
    private activateRoute: ActivatedRoute,
    private jobListingSvc: JobListingService,
    private userSvc: UserService
  ) {}

  ngOnInit(): void {
    // subscribe to changes in current route
    this.routeSub$ = this.activateRoute.queryParams.subscribe((params) => {
      // set nameStartsWith to current route query parameter
      this.query = params['jobStartsWith'];
      // console.log('>>> job query: ' + this.query)
      this.getjobListing();
      this.email = this.userSvc.email
      console.info("email", this.email)
    });
  }

  getjobListing() {
    this.jobListingSvc
      .getjobListing(this.query, this.pages)
      .then((res) => {
        console.info(res)
        this.jobs = res;
        res.forEach(job => {
          if(job.employer_logo == null || job.employer_logo == "ul") {
            job.employer_logo = this.defaultImage
          }
        })
      })
      .catch((err) => {
        console.log(err);
      });
  }

  ngOnDestroy(): void {
    this.routeSub$.unsubscribe();
  }

  // onChangePage(changeBy: number): void {
  //   // change offset and make new request to server
  //   this.currOffset = this.currOffset + this.currLimit * changeBy;
  //   this.getjobListing();
  // }

}
