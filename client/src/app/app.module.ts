import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { JobSearchComponent } from './components/job-search.component';
import { JobListComponent } from './components/job-list.component';
import { JobDetailsComponent } from './components/job-details.component';
import { JobListingService } from './service/JobListing.service';
import { RegisterComponent } from './components/register.component';
import { LoginComponent } from './components/login.component';
import { UserService } from './service/User.service';
import { ApplyJobComponent } from './components/apply-job.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { LoginStatusComponent } from './components/login-status/login-status.component';
import { ToastrModule } from 'ngx-toastr';
import { FavouriteJobsComponent } from './components/favourite-jobs/favourite-jobs.component';

@NgModule({
  declarations: [
    AppComponent,
    JobSearchComponent,
    JobListComponent,
    JobDetailsComponent,
    RegisterComponent,
    LoginComponent,
    ApplyJobComponent,
    LoginStatusComponent,
    FavouriteJobsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    })

  ],
  providers: [
    JobListingService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
