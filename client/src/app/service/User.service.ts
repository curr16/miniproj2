import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from 'rxjs';
import { JobDetails } from "../models/JobListing.model";
import { User } from "../models/User.model";

@Injectable()

export class UserService {

    email!: string

    constructor(private httpClient: HttpClient) {}

    getuserDetails(user:User) {
        console.info(user)
        return lastValueFrom(
          this.httpClient.post<User>(
            'http://localhost:8080/api/register',
            user
          )
        );
      }

      getLogin(email: string, password: string): Promise<User> {
        this.email = email
        console.info(this.email)
        console.info(password)
        return lastValueFrom(
          this.httpClient.post<User>(
            'http://localhost:8080/api/login',
           {email, password}
          )
        );
      }


      saveJob(email: string, job: JobDetails) {
        return lastValueFrom(
          this.httpClient.post(
            'http://localhost:8080/api/savedJob',
            {email, job}
          )
        )
      }

  }