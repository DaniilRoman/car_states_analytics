package com.app.admin.controller;


import com.app.admin.api.TestApi;
import com.app.admin.config.AnalyticsConfig;
import com.app.analytics.api.ApiClient;
import com.app.analytics.api.client.AnalyticsTestApi;
import com.app.admin.api.model.TestItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class TestController implements TestApi {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    AnalyticsConfig analyticsConfig;
    private AnalyticsTestApi testApiClient;


    @PostConstruct
    public void setUp() {
        ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(analyticsConfig.getUrl());
        testApiClient = new AnalyticsTestApi(apiClient);
    }

    @Override
    public ResponseEntity<List<TestItem>> getTest(UUID accountId) {
        return ResponseEntity.ok(
                testApiClient.getTest(1L).stream()
                        .map(it -> new TestItem().id(it.getId()).values(it.getValues()))
                        .collect(Collectors.toList())
        );
    }
}
