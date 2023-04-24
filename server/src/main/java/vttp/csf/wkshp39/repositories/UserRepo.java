package vttp.csf.wkshp39.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.csf.wkshp39.models.Job;
import vttp.csf.wkshp39.models.JobListings;
import vttp.csf.wkshp39.models.User;
import static vttp.csf.wkshp39.repositories.Queries.*;

@Repository
public class UserRepo {

    @Autowired
	private JdbcTemplate template;

    public void saveUserDetail(User u) {
        template.update(SQL_INSERT_USER, u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword());
    }

    public Optional<String> getUserDuplicate(String email) {
        return null;
    }

    public boolean checkUser(String email) {
        return false;
    }

    public Optional<User> login(String email, String password) {

        SqlRowSet login = template.queryForRowSet(SQL_CHECK_LOGIN, email, password);
        if (! login.next()) {
            return Optional.empty();
        }

        return Optional.of(User.create(login));
    }


    public void saveJob(String email, Job job) {

        template.update(SQL_USER_SAVEJOB, job.getJob_id(), job.getEmployer_name(), job.getJob_title(), job.getJob_country()
        ,job.getJob_employment_type(), email);
   
    }

    public List<JobListings> retrieveUserFavouriteJobs(String email) {
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_FAVOURITE_JOBS, email);

        List<JobListings> jobList = new LinkedList<>();
		
		while (rs.next()) {
            jobList.add(JobListings.create(rs));
        }

		return jobList;
    }
    
}
