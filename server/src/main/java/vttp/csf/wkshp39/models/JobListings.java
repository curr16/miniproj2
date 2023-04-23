package vttp.csf.wkshp39.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class JobListings {
    
    private String jobId;
    private String employer_name;
    private String employer_logo = "";
    private String job_title;
    private String job_country;
    private String job_employment_type;

    public String getJobId() {return jobId;}
    public void setJobId(String jobId) {this.jobId = jobId;}

    public String getEmployer_name() {return employer_name;}
    public void setEmployer_name(String employer_name) {this.employer_name = employer_name;}

    public String getEmployer_logo() {return employer_logo;}
    public void setEmployer_logo(String employer_logo) {this.employer_logo = employer_logo;}

    public String getJob_title() {return job_title;}
    public void setJob_title(String job_title) {this.job_title = job_title;}

    public String getJob_country() {return job_country;}
    public void setJob_country(String job_country) {this.job_country = job_country;}

    public String getJob_employment_type() {return job_employment_type;}
    public void setJob_employment_type(String job_employment_type) {this.job_employment_type = job_employment_type;}

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("job_id", jobId)
            .add("employer_name", employer_name)
            .add("employer_logo", employer_logo)
            .add("job_title", job_title)
            .add("job_country", job_country)
            .add("job_employment_type", job_employment_type)
            .build();
    }

    public static JobListings createJobListings(JsonObject jo) {
        JobListings listings = new JobListings();
        listings.setJobId(jo.getString("job_id"));
        listings.setEmployer_name(jo.getString("employer_name"));

        String logo = jo.get("employer_logo").toString();
        listings.setEmployer_logo(logo.substring(1, logo.length()-1));
        
        listings.setJob_title(jo.getString("job_title"));
        listings.setJob_country(jo.getString("job_country"));
        listings.setJob_employment_type(jo.getString("job_employment_type"));

        return listings;
    }


    
}
