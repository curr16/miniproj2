export interface JobListing {
    job_id: string;
    employer_name: string;
    employer_logo?: string;
    job_title: string;
    job_country: string;
    job_employment_type: string;

  }

  export interface JobDetails {
    job_id: string;
    employer_name: string;
    employer_logo?: string;
    job_title: string;
    job_country: string;
    job_employment_type: string;
    job_description: string;

  }

  export interface ApplyJob {
    job_id: string;
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    resume: File;

  }


