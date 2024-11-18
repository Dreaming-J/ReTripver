package com.retripver.global.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.retripver.global.util.FileManageUtil;

@Service
public class FileManageService {
	
	private final AmazonS3 amazonS3;
	private final FileManageUtil fileUploadUtil;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	@Value("${cloud.aws.s3.bucket.url}")
	private String defaultUrl;
	
	@Autowired
	public FileManageService(AmazonS3 amazonS3, FileManageUtil fileUploadUtil) {
		this.amazonS3 = amazonS3;
		this.fileUploadUtil = fileUploadUtil;
	}

    public String uploadFile(MultipartFile file) {
        try {
        	// 파일 이름 생성
        	String fileName = fileUploadUtil.generateFileName(file.getOriginalFilename());
        	
            // 메타데이터 생성
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            // S3 업로드
            amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);

            // 업로드된 파일의 URL 반환
            return amazonS3.getUrl(bucket, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
    
    public List<String> uploadMultipleFiles(List<MultipartFile> files) {
    	return files.stream()
    			.map(this::uploadFile)
    			.toList();
    }
    
    public boolean deleteFile(String fileName) {
    	try {
    		boolean isObjectExist = amazonS3.doesObjectExist(bucket, fileName);
    		
    		if (!isObjectExist)
    			return false;
    		
    		DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
    		amazonS3.deleteObject(request);
    		
    		isObjectExist = amazonS3.doesObjectExist(bucket, fileName);
    		
    		return !isObjectExist;
    	} catch (AmazonServiceException e) {
    		throw new RuntimeException("Failed to delete file to S3", e);
    	}
    }
}
