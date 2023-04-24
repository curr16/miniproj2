package vttp.csf.wkshp39.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp.csf.wkshp39.models.ApplyJob;
import vttp.csf.wkshp39.models.Job;
import vttp.csf.wkshp39.models.JobListings;
import vttp.csf.wkshp39.services.JobService;
import vttp.csf.wkshp39.services.S3Service;

@RestController
@RequestMapping (path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobService jobSvc;

    @Autowired
    private S3Service s3Svc;

    // calls for localhost:8080/api/search?query=designer&num_pages=1
    @GetMapping(path = "/search")
    public ResponseEntity<String> getJobs(
        @RequestParam String query,
        @RequestParam(defaultValue ="1") Integer pages
    ) {
        List<JobListings> listings = jobSvc.getJobs(query, pages);

          // List<JobListings> -> JsonArray
          JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

          // add each Job listing -> JsonObject
          listings.forEach(j -> arrBuilder.add(j.toJson()));
      
          JsonArray resp = arrBuilder.build();
          return ResponseEntity.ok(resp.toString());
    }

    // calls for localhost:8080/api/job-details/hAhRRxBaxTUAAAAAAAAAAA==
    @GetMapping(path = "/job-details/{jobId}")
    public ResponseEntity<String> getJobById(
        @PathVariable String jobId) {
          System.out.println(jobId);

          List <Job> individualJobListing = jobSvc.getJobById(jobId);
          JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

          // add each Job listing -> JsonObject
          individualJobListing.forEach(j -> arrBuilder.add(j.toJson()));
      
          JsonArray resp = arrBuilder.build();
          return ResponseEntity.ok(resp.toString());

        }

        @PostMapping(path = "/apply-job", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public void applyJob(@RequestPart MultipartFile resume, @RequestPart String job_id,@RequestPart String firstName,
        @RequestPart String lastName, @RequestPart String email, @RequestPart String phone) throws Exception {

          ApplyJob aj = new ApplyJob();
          aj.setJob_id(job_id);
          aj.setFirstName(firstName);
          aj.setLastName(lastName);
          aj.setEmail(email);
          aj.setPhone(phone);
          aj.setResume(resume.getBytes());

          s3Svc.upload(aj, resume);

        }



}