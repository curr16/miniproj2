package vttp.csf.wkshp39.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.wkshp39.models.Job;
import vttp.csf.wkshp39.models.JobListings;

@Service
public class JobService {

    @Autowired
    private JobAPIService jobAPISvc;

    public List<JobListings> getJobs(String query, Integer pages) {
       return jobAPISvc.getJobs(query, pages);

    }

   public List<Job> getJobById(String jobId) {
      return jobAPISvc.getJobById(jobId);
      
   }

   public void uploadResume() {
      
   }
 
 

 
 
  }

