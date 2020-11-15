package com.app.admin.controller;


import com.app.admin.api.TestApi;
import com.app.admin.api.model.TestItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class TestController implements TestApi {
    @Override
    public ResponseEntity<List<TestItem>> getTest(Long accountId) {
        TestItem testItem1 = new TestItem();
        testItem1.id(UUID.randomUUID()).setValues(Arrays.asList(1L, 2L, 3L));
        return ResponseEntity.ok(Collections.singletonList(testItem1));
    }
}
