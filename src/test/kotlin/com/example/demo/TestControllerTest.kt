package com.example.demo

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.time.Duration
import java.time.Duration.ofMillis


@SpringBootTest
@ActiveProfiles("test")
@Import(value = [KafkaConsumerConfig::class])
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class TestControllerTest : IntegrationTestBased() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var consumer: KafkaConsumer<String, String>

    @Test
    fun t1() {
        val req = MockMvcRequestBuilders.get("/api")
        mockMvc.perform(req).andDo {
            println(it.response.contentAsString)
        }

        val first = consumer.poll(ofMillis(1000)).records("topic").first().value()
        println("Valor dentro do topico: $first")
    }

}