package vttp.csf.wkshp39.repositories;

public class Queries {

    public static final String SQL_INSERT_USER = "insert into user(firstName, lastName, email, password)" +
                                                            "values(?, ?, ?, sha1(?))";

    public static final String SQL_CHECK_LOGIN = "select * from user where email = ? and password = sha1(?)";

    public static final String SQL_USER_SAVEJOB = "insert into user_job(job_id, employer_name,job_title, job_country, job_employment_type, email)" +
                                                    "values(?, ?, ?, ?, ?, ?)";

    public static final String SQL_CREATE_RESUME = "insert into apply_job(job_id, firstName, lastName, email, phone, resume, resume_url)" +
                                                    "values(?, ?, ?, ?, ?, ?, ?)";

     
}
