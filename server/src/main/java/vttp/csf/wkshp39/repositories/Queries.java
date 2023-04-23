package vttp.csf.wkshp39.repositories;

public class Queries {

    public static final String SQL_INSERT_USER = "insert into User(firstName, lastName, email, password)" +
                                                            "values(?, ?, ?, sha1(?))";

    public static final String SQL_CHECK_LOGIN = "select * from User where email = ? and password = sha1(?)";

    public static final String SQL_USER_SAVEJOB = "insert into UserJob(job_id, employer_name,job_title, job_country, job_employment_type, email)" +
                                                    "values(?, ?, ?, ?, ?, ?)";



     
}
