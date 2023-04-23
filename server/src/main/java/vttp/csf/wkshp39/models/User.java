package vttp.csf.wkshp39.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
// import org.springframework.security.crypto.bcrypt.BCrypt;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getFirstName() {return firstName;} 
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    //convert from Json to model objects
    public User create(String userName, String email, String password, String password2) {
        User u  = new User();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        // String encrpytedPW = BCrypt.hashpw(password, BCrypt.gensalt());
        u.setPassword(password);
        return u;
    }
    
    //convert model to Json object
    public JsonObject toJson(User u) {
        return Json.createObjectBuilder()
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("email", email)
            .add("password", password)
            .build();
    }
    
    public static User create(JsonObject existingUser) {
        User ue = new User();
        ue.setFirstName(existingUser.getString("firstName"));
        ue.setLastName(existingUser.getString("lastName"));
        ue.setEmail(existingUser.getString("email"));
        ue.setPassword(existingUser.getString("password"));
        return ue;
    }

    public static User create(SqlRowSet rs) {
		User u = new User();
		u.setFirstName(rs.getString("firstName"));
        u.setLastName(rs.getString("lastName"));
		u.setPassword(rs.getString("password"));
		u.setEmail(rs.getString("email"));;

		return u;
	} 



}
