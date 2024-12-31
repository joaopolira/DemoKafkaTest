package com.example.demo

import com.example.demo.IntegrationTestBased.Companion.KAFKA_CONTAINER
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@Configuration
class KafkaConsumerConfig {
	@Bean
	fun kafkaConsumer(): KafkaConsumer<String, String> {
		val props = mapOf(
			ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to KAFKA_CONTAINER.bootstrapServers,
			ConsumerConfig.GROUP_ID_CONFIG to "my-group-id",
			ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
			ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
			ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest"
		)
		return KafkaConsumer<String, String>(props).apply {
			subscribe(listOf("topic"))
		}
	}
}
