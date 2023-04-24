import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom, firstValueFrom } from 'rxjs';
import { ApplyJob, JobDetails, JobListing } from '../models/JobListing.model';

@Injectable({
  providedIn: 'root',
})
export class JobListingService {
  constructor(private httpClient: HttpClient) {}

  getjobListing(query: string, pages: number) {
    const params = new HttpParams()
      .set('query', query)
      .set('pages', pages);

    return lastValueFrom(
      this.httpClient.get<JobListing[]>(
        'http://localhost:8080/api/search',
        {
          params: params,
        }
      )
    );
  }

  getJobById(job_id: string): Promise<JobDetails[]> {
    return lastValueFrom(
      this.httpClient.get<JobDetails[]>(
        `http://localhost:8080/api/job-details/${job_id}`
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
  

    return firstValueFrom(this.httpClient.post('http://localhost:8080/api/ApplyJob', content))



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