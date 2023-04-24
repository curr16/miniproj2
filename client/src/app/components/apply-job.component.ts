import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApplyJob, JobDetails } from '../models/JobListing.model';
import { JobListingService } from '../service/JobListing.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-apply-job',
  templateUrl: './apply-job.component.html',
  styleUrls: ['./apply-job.component.css']
})
export class ApplyJobComponent implements OnInit {

@ViewChild('image')
image!: ElementRef

uploadForm!: FormGroup
firstName!: string
id!: string
param$!: Subscription
jobDetail: JobDetails[] = []
companyName!: string

constructor(private fb: FormBuilder, private jobListingSvc: JobListingService, private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.uploadForm = this.createForm()
    this.param$ = this.activatedRoute.params.subscribe((p) => {
      this.id = p['job_id'] as string
    })
    console.info(this.id)
    this.getJobDetail()
  }

  async getJobDetail() {
    this.jobDetail = await this.jobListingSvc.getJobById(this.id)
    this.companyName = this.jobDetail[0].employer_name
    console.info(this.companyName)
  }

submitForm() {

  const jobApp = this.uploadForm.value as ApplyJob
  jobApp.job_id = this.id as string
  jobApp.resume = this.image.nativeElement.files[0]

  this.jobListingSvc.uploadResume(jobApp)

}

private createForm(): FormGroup {
  return this.fb.group ({
    firstName: this.fb.control('', [Validators.required]),
    lastName: this.fb.control('', [Validators.required]),
    email: this.fb.control('', [Validators.required]),
    phone: this.fb.control('', [Validators.required]),
    resume: this.fb.control('', [Validators.required]),
  })
}

}
