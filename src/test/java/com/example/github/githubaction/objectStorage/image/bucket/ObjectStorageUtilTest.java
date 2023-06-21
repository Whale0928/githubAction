package com.example.github.githubaction.objectStorage.image.bucket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectStorageUtilTest {

    @Autowired
    ObjectStorageUtil objectStorageUtil;

    @Test
    @DisplayName("findList() 테스트")
    void findList() throws Exception {
        objectStorageUtil.init();
        objectStorageUtil.findList();
    }

    @Test
    @DisplayName("findByKey() 테스트")
    void findByKey() throws Exception {
        objectStorageUtil.init();
        objectStorageUtil.findByKey("test.jpg");
    }

    @Test
    @DisplayName("upload() 테스트")
    void upload() throws Exception {
        objectStorageUtil.init();
        objectStorageUtil.upload("2020-05/TEST1.jpg");
    }
}