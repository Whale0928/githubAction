package com.example.github.githubaction.objectStorage.image.bucket;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class FindList {
    private final String endPoint = "https://kr.object.ncloudstorage.com";
    private final String regionName = "kr-standard";

    @Value("${object.storage.accessKey}")
    private String accessKey;

    @Value("${object.storage.secretKey}")
    private String secretKey;


    // S3 client
    private final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
            .build();

    /**
     * 버킷의 모든 객체를 나열합니다.
     */
    public void findList() throws Exception {
        // 버킷 이름을 가지고 객체를 300개까지 가져오는 요청을 생성합니다.
        String bucketName = "petlink-buket";
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(bucketName)
                .withMaxKeys(300);
        // 생성한 요청을 S3 서비스에 전달하여 객체 목록을 받아옵니다.
        ObjectListing objectListing = s3.listObjects(listObjectsRequest);

        // 객체 목록이 완전히 소진될 때까지 반복합니다.
        while (true) {
            // 받아온 객체 목록에서 각 객체의 정보를 로그로 출력합니다.
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                log.info(" name={}, size={}, owner={}", objectSummary.getKey(), objectSummary.getSize(), objectSummary.getOwner().getId());
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
        // 마지막으로 받아온 객체 목록을 로그로 출력합니다.
        log.info(objectListing.toString());
    }
}
