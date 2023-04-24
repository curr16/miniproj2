package vttp.csf.wkshp39.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


import vttp.csf.wkshp39.models.ApplyJob;
import vttp.csf.wkshp39.repositories.JobRepository;

@Service
public class S3Service {
    private Logger logger = Logger.getLogger(S3Service.class.getName());

    @Value("${spaces.bucket}")
    private String spacesBucket;

    @Value("${spaces.endpoint.url}")
    private String spacesEndpointURL;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private JobRepository jobRepo;


    public ApplyJob upload(ApplyJob applyJob, MultipartFile file) throws Exception{
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        System.out.println("bucket");
        System.out.println(spacesBucket);
        try {
            PutObjectRequest putReq = new PutObjectRequest(spacesBucket, applyJob.getFirstName(), file.getInputStream(), metadata);
            putReq.withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putReq);
        }
        catch (Exception ex) {
            logger.log(Level.WARNING, "Put S3", ex);
            return null;
        }
        String resumeURL = "https://%s.%s/%s".formatted(spacesBucket, spacesEndpointURL, applyJob.getFirstName());
        System.out.println(resumeURL);
        applyJob.setResumeURL(resumeURL);
        jobRepo.saveResume(file, applyJob.getJob_id(), applyJob.getFirstName(), applyJob.getLastName(), applyJob.getEmail(), applyJob.getPhone(),resumeURL);
        return applyJob;
    }

    
}
