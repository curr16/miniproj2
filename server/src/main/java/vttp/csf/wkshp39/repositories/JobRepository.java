package vttp.csf.wkshp39.repositories;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.jdbc.core.JdbcTemplate;
import static vttp.csf.wkshp39.repositories.Queries.*;

@Repository
public class JobRepository {

    @Autowired
	private JdbcTemplate template;

    @Autowired
    private DataSource ds;


    public void saveResume(MultipartFile resume, String job_id, String firstName,
    String lastName, String email, String phone, String resumeURL) throws Exception {
        try (Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_CREATE_RESUME)) {
                InputStream is = resume.getInputStream();
                ps.setString(1, job_id);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, email);
                ps.setString(5, phone);
                ps.setBinaryStream(6, is, resume.getSize());
                ps.setString(7, resumeURL);
                ps.executeUpdate(); 

            }


    }
    

    

    
}
