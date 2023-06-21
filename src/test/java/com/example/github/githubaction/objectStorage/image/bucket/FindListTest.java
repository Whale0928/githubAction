package com.example.github.githubaction.objectStorage.image.bucket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class FindListTest {

    @Test
    @DisplayName("목록 조회")
    void findList()throws Exception {
        FindList findList = new FindList();
        findList.findList();
    }

}