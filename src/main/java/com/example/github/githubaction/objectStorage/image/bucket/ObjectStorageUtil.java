package com.example.github.githubaction.objectStorage.image.bucket;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Slf4j
@Component
public class ObjectStorageUtil {

    private final String accessKey = "";
    private final String secretKey = "";

    // S3 client
    private AmazonS3 s3;
    private String bucketName = "petlink-images-buket";

    @PostConstruct
    public void init() {
        String endPoint = "https://kr.object.ncloudstorage.com";
        String regionName = "kr-standard";
        s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    public void findList() throws Exception {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(bucketName)
                .withMaxKeys(300);
        ObjectListing objectListing = s3.listObjects(listObjectsRequest);

        while (true) {
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                log.info(" - " + objectSummary.getKey() + "  " +
                        "(size = " + objectSummary.getSize() +
                        ")");
            }
            // 객체 목록이 다음 페이지로 이어지는지 확인합니다.
            if (objectListing.isTruncated()) {
                // 이어진다면 다음 페이지의 객체 목록을 받아옵니다.
                objectListing = s3.listNextBatchOfObjects(objectListing);
            } else {
                // 이어지지 않는다면 반복을 종료합니다.
                break;
            }
        }
    }

    public void findByKey(String key) throws Exception {
        S3Object images = s3.getObject(bucketName, key);
        log.info("Link: " + images.getObjectContent().getHttpRequest().getURI());
    }

    public void createFolder(String folderName) throws Exception {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);

        s3.putObject(putObjectRequest);
        System.out.format("Folder %s has been created.\n", folderName);
    }

    public void upload(String objectName) throws Exception {
        //이미지 정보
        Resource resource = new ClassPathResource("images/sample/img.png");

        //이미지 정보
        InputStream inputStream = resource.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(resource.contentLength());
        metadata.setContentType("image/png");

        PutObjectRequest request = new PutObjectRequest(bucketName, objectName, inputStream, metadata);
        request.setCannedAcl(CannedAccessControlList.PublicRead); // 모든 사용자에게 읽기 액세스 권한 부여

        // S3에 업로드
        PutObjectResult result = s3.putObject(request);
        log.info("Etag: " + result.getETag() + " Link: " + result.getContentMd5());
    }
}
