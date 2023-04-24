import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApplyJobComponent } from './components/apply-job.component';
import { JobDetailsComponent } from './components/job-details.component';
import { JobListComponent } from './components/job-list.component';
import { JobSearchComponent } from './components/job-search.component';
import { LoginComponent } from './components/login.component';
import { RegisterComponent } from './components/register.component';

const routes: Routes = [
  // {path: '', component: JobSearchComponent},
  {path: 'search', component: JobSearchComponent},
  {path: 'jobs', component: JobListComponent},
  {path: 'jobs/:job_id', component: JobDetailsComponent},
  {path: 'applyJob/:job_id', component: ApplyJobComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', component: LoginComponent},
  {path: 'applyJob', component: ApplyJobComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
