package com.app.analytics.controller

import com.app.analytics.api.AnalyticsTestApiService
import com.app.analytics.api.model.TestItem
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestApiServiceImpl: AnalyticsTestApiService {
    override fun getTest(accountId: Long): List<TestItem> {
        return listOf(TestItem(UUID.randomUUID(), listOf(7, 7, 7)))
    }
}