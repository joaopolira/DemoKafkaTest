package com.example.demo

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.kafka.ConfluentKafkaContainer

@Testcontainers
abstract class IntegrationTestBased {

    companion object {
        @Container
        val KAFKA_CONTAINER =  ConfluentKafkaContainer("confluentinc/cp-kafka:7.4.0")


        @DynamicPropertySource
        @JvmStatic
        fun overrideProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.kafka.bootstrap-servers", KAFKA_CONTAINER::getBootstrapServers)
        }
    }
}