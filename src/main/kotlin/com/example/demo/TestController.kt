package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @GetMapping("/api")
    fun getTest(): String {
        kafkaTemplate.send("topic", "payload")
        return "Test okay"
    }

}