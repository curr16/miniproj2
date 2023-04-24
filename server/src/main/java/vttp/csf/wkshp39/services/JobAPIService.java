package vttp.csf.wkshp39.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import jakarta.json.JsonArray;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.csf.wkshp39.models.Job;
import vttp.csf.wkshp39.models.JobListings;

@Service
public class JobAPIService {

    @Value("${X-RapidAPI-Key}")
    private String key;

    @Value("${X-RapidAPI-Host}")
    private String host;

    private static final String JOB_URL = "https://jsearch.p.rapidapi.com/search";
    private static final String JOB_LISTING = "https://jsearch.p.rapidapi.com/job-details";

    public List<JobListings> getJobs(String query, Integer pages) {
  
      //set the headers
      final HttpHeaders headers = new HttpHeaders();
      headers.set("X-RapidAPI-Key", key);
      headers.set("X-RapidAPI-Host", host);

      final HttpEntity<String> entity = new HttpEntity<>(headers);

      // build URL with auth params and query params
      final String url = UriComponentsBuilder
      .fromUriString(JOB_URL)
      .queryParam("query", query)
      .queryParam("num_pages",pages)
      .toUriString();

      // Make GET request and receive response
      RestTemplate template = new RestTemplate();
      ResponseEntity<String> resp = template.exchange(url, HttpMethod.GET, entity, String.class);
      System.out.println(resp.getBody());

      List<JobListings> jobs = new LinkedList<>();
      
      // String -> JSONobject
      JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
      JsonObject json = reader.readObject();

      // get JsonArray from Json
      JsonArray results = json.getJsonArray("data");

      //map each JsonValue in result array to Job listing
      jobs = results
        .stream()
        .map(jv -> jv.asJsonObject()) // Jsonvalue - JsonObject
        .map(jo ->JobListings.createJobListings(jo)) //JsonObject -> Job Listing
        .toList();

      return jobs;

    }

    public List<Job> getJobById(String jobId) {

      //set the headers
      final HttpHeaders headers = new HttpHeaders();
      headers.set("X-RapidAPI-Key", key);
      headers.set("X-RapidAPI-Host", host);

      final HttpEntity<String> entity = new HttpEntity<>(headers);

      // build URL with auth params and query params
      final String url = JOB_LISTING.concat("?job_id=%s".formatted(jobId));

      System.out.println(url);

      // Make GET request and receive response
      RestTemplate template = new RestTemplate();
      ResponseEntity<String> resp = template.exchange(url, HttpMethod.GET, entity, String.class);
      
      List<Job> jobs = new LinkedList<>();
      // String -> JSONobject
      JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
      // System.out.println(resp.getBody());
      JsonObject json = reader.readObject();

      // get JsonArray from Json
      JsonArray results = json.getJsonArray("data");
      System.out.println(results.toString());

      //map each JsonValue in result array to Job listing
      jobs = results
        .stream()
        .map(jv -> jv.asJsonObject()) // Jsonvalue - JsonObject
        .map(jo ->Job.create(jo)) //JsonObject -> Job Listing
        .toList();

      return jobs;
      
    }


  }



