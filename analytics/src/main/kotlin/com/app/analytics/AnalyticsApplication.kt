package com.app.analytics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.app.analytics"])
class AnalyticsApplication

fun main(args: Array<String>) {
	runApplication<AnalyticsApplication>(*args)
}
