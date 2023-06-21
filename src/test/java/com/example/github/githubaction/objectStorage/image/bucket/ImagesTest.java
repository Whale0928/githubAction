package com.example.github.githubaction.objectStorage.image.bucket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


class ImagesTest {
    @Test
    @DisplayName("이미지 제대로 가져오는지 테스트")
    void getImageTest() throws IOException {
        Resource resource = new ClassPathResource("images/sample/img.png");
        byte[] imageBytes = resource.getInputStream().readAllBytes();
        MultipartFile multipartFile = new MockMultipartFile("img.png", imageBytes);
        System.out.println(Arrays.toString(multipartFile.getBytes()));
    }

}
