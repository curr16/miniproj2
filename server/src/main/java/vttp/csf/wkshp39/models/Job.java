package vttp.csf.wkshp39.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Job {

    private String job_id;
    private String employer_name;
    private String employer_logo = "";
    private String job_title;
    private String job_country;
    private String job_employment_type;
    private String job_description;
    private String qualifications;
    private String responsibilities;

    public String getJob_id() {return job_id;}
    public void setJob_id(String job_id) {this.job_id = job_id;}

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

    public String getJob_description() {return job_description;}
    public void setJob_description(String job_description) {this.job_description = job_description;}
    
    public String getQualifications() {return qualifications;}
    public void setQualifications(String qualifications) {this.qualifications = qualifications;}

    public String getResponsibilities() {return responsibilities;}
    public void setResponsibilities(String responsibilities) {this.responsibilities = responsibilities;}

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("job_id", job_id)
            .add("employer_name", employer_name)
            .add("employer_logo", employer_logo)
            .add("job_title", job_title)
            .add("job_country", job_country)
            .add("job_employment_type", job_employment_type)
            .add("job_description", job_description)
            .build();
    }

    public static Job create(JsonObject jo) {
        Job job = new Job();
        job.setJob_id(jo.getString("job_id"));
        job.setEmployer_name(jo.getString("employer_name"));

        String logo = jo.get("employer_logo").toString();
        job.setEmployer_logo(logo.substring(1, logo.length()-1));

        job.setJob_title(jo.getString("job_title"));
        job.setJob_country(jo.getString("job_country"));
        job.setJob_employment_type(jo.getString("job_employment_type"));

        job.setJob_description(jo.getString("job_description"));

        return job;
    }
}