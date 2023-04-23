package vttp.csf.wkshp39.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.csf.wkshp39.models.Job;
import vttp.csf.wkshp39.models.User;
import vttp.csf.wkshp39.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUserDetail(User u) {
        userRepo.saveUserDetail(u);
    }

    public User getUserDetail(String email) {

        Optional<String> userResult = userRepo.getUserDuplicate(email);
        if (userResult.isEmpty()) {
            return null;
        }

        String valueResult = userResult.get();
        Reader read = new StringReader(valueResult);
        JsonReader jreader = Json.createReader(read);

        JsonObject jObject = jreader.readObject();
            return User.create(jObject);
            
    }

    public boolean checkUserExist(String email) {
        return userRepo.checkUser(email);
    }

    public Optional<User> login(String Username, String Password) {
        return userRepo.login(Username, Password);
    }

    public void saveJob(String email, Job job) {
        userRepo.saveJob(email, job);
 
    }
  
}
