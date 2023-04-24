package vttp.csf.wkshp39.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

import java.io.StringReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.csf.wkshp39.models.Job;
import vttp.csf.wkshp39.models.User;
import vttp.csf.wkshp39.services.UserService;
import vttp.csf.wkshp39.services.googleAPIservice;

@Controller
@RequestMapping (path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userSvc;

    @Autowired
    private googleAPIservice gSvc;

    @PostMapping (path = "/register")
    public ResponseEntity<String> getUser(@RequestBody String payload) throws Exception {
        System.out.println(payload);

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();
        
        if (userSvc.checkUserExist(json.getString("email"))) {
            return ResponseEntity.ok("login");
            
        }
        User newUser = User.create(json);
        userSvc.saveUserDetail(newUser);
        gSvc.sendMail(newUser);
        return ResponseEntity.ok(payload);
    }


    @PostMapping (path = "/login")
    public ResponseEntity<User> getLogin (@RequestBody String payload) {
        System.out.println(payload);

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();

        String email = json.getString("email");
        String password = json.getString("password");

        Optional<User> login = userSvc.login(email, password);

        return ResponseEntity.ok(login.get());

    }
    
    @PostMapping (path = "/saved-job")
    public ResponseEntity<String> saveJob(@RequestBody String payload) {
        System.out.println(payload);

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();

        JsonObject jobResult = json.getJsonObject("job");
        Job job = Job.create(jobResult);
        String email = json.getString("email");

        userSvc.saveJob(email, job);
        
        return ResponseEntity.ok("success");

    }

}
