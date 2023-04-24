import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from 'rxjs';
import { environment } from "src/environments/environment";
import { JobDetails } from "../models/JobListing.model";
import { User } from "../models/User.model";

@Injectable()

export class UserService {

    email!: string

    private registerUrl = environment.apiUrl + "/register";
    private loginUrl = environment.apiUrl + "/login";
    private saveJobUrl = environment.apiUrl + "/saved-job";

    // storage: Storage = localStorage
    storage: Storage = sessionStorage

    constructor(private httpClient: HttpClient) {
       // Read data from storage
    let data = JSON.parse(this.storage.getItem('useraccount')!);
    }

    getuserDetails(user:User) {
        console.info(user)
        return lastValueFrom(
          this.httpClient.post<User>(
            this.registerUrl,
            user
          )
        );
      }

      getLogin(email: string, password: string): Promise<User> {
        this.email = email
        console.info(this.email)
        console.info(password)
        const result = lastValueFrom(
          this.httpClient.post<User>(
            this.loginUrl,
           {email, password}
          )
        );
        result.then((user) => {
          this.storage.setItem('useraccount', JSON.stringify(user))
        }
        )
        return result
      }


      saveJob(email: string, job: JobDetails) {
        return lastValueFrom(
          this.httpClient.post(
            this.saveJobUrl,
            {email, job}
          )
        )
      }

  }
