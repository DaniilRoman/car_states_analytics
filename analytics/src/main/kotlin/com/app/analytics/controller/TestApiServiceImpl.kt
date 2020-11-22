package com.app.analytics.controller

import com.app.analytics.api.TestApiService
import com.app.analytics.api.model.TestItem
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestApiServiceImpl: TestApiService {
    override fun getTest(accountId: Long): List<TestItem> {
        return listOf(TestItem(UUID.randomUUID(), listOf(7, 7, 7)))
    }

}