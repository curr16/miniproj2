import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserFavouriteJob } from 'src/app/models/user-favourite-job';
import { JobListingService } from 'src/app/service/JobListing.service';
import { UserService } from 'src/app/service/User.service';

@Component({
  selector: 'app-favourite-jobs',
  templateUrl: './favourite-jobs.component.html',
  styleUrls: ['./favourite-jobs.component.css'],
})
export class FavouriteJobsComponent implements OnInit {
  query!: string; // passed from search component
  routeSub$!: Subscription;
  pages: number = 2;
  // currLimit: number = 10;
  // currOffset: number = 0;
  favouriteJobs: UserFavouriteJob[] = [];
  userEmail!: string;
  gridColumns = 3;

  // storage: Storage = localStorage
  storage: Storage = sessionStorage;
  constructor(
    private activateRoute: ActivatedRoute,
    private jobListingSvc: JobListingService,
    private userSvc: UserService
  ) {
    // Read data from storage
    let data = JSON.parse(this.storage.getItem('useraccount')!);
    if (data) {
      this.userEmail = data?.email;
    }
  }

  ngOnInit(): void {
    this.getUserFavouriteJobs();
  }

    getUserFavouriteJobs() {
    this.userSvc.getUserFavouriteJobs(this.userEmail).subscribe(result => {
      console.info(`getUserFavouriteJobs: ${JSON.stringify(result)}`)
      this.favouriteJobs = result;
      console.log(`favouriteJobs: ${JSON.stringify(this.favouriteJobs)}`)
    })

  }
}
