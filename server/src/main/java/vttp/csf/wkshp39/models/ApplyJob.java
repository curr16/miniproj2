package vttp.csf.wkshp39.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class ApplyJob {

    private String job_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private byte[] resume;
    private String resumeURL;

    public String getJob_id() {
        return job_id;
    }
    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public byte[] getResume() {
        return resume;
    }
    public void setResume(byte[] resume) {
        this.resume = resume;
    }
    public String getResumeURL() {
        return resumeURL;
    }
    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL;
    }

    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("job_id", job_id)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("email", email)
            .add("phone", phone)
            .add("resume", resumeURL)
            .build();
    }

    public static ApplyJob create(ResultSet rs) throws SQLException {
        ApplyJob aj = new ApplyJob();
        aj.setJob_id(rs.getString("job_id"));
        aj.setFirstName(rs.getString("firstName"));
        aj.setLastName(rs.getString("lastName"));
        aj.setEmail(rs.getString("email"));
        aj.setPhone(rs.getString("phone"));
        aj.setResume(rs.getBytes("resume"));
        return aj;
    }

    
}
