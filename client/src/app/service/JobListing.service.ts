import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom, firstValueFrom } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ApplyJob, JobDetails, JobListing } from '../models/JobListing.model';

@Injectable({
  providedIn: 'root',
})
export class JobListingService {

  private jobListingUrl = environment.apiUrl + '/search';
  private jobDetailsUrl = environment.apiUrl + '/job-details';
  private applyJobUrl = environment.apiUrl + '/apply-job';

  constructor(private httpClient: HttpClient) {}

  getjobListing(query: string, pages: number) {
    const params = new HttpParams()
      .set('query', query)
      .set('pages', pages);

    return lastValueFrom(
      this.httpClient.get<JobListing[]>(
        this.jobListingUrl,
        {
          params: params,
        }
      )
    );
  }

  getJobById(job_id: string): Promise<JobDetails[]> {
    return lastValueFrom(
      this.httpClient.get<JobDetails[]>(
        `${this.jobDetailsUrl}/${job_id}`
      )
    );
  }

  uploadResume(jobApp: ApplyJob) {

    const content = new FormData()
    content.set("job_id", jobApp.job_id)
    content.set("firstName", jobApp.firstName)
    content.set("lastName", jobApp.lastName)
    content.set("email", jobApp.email)
    content.set("phone", jobApp.phone)
    content.set("resume", jobApp.resume)


    return firstValueFrom(this.httpClient.post(this.applyJobUrl, content))
  }

  // postComment(jobId: number, comment: string) {
  //   const body = {
  //     comment: comment,
  //   };

  //   return lastValueFrom(
  //     this.httpClient.post(
  //       `https://ripe-cup-production.up.railway.app/api/character/${characterId}`,
  //       body
  //     )
  //   );
  // }
}
