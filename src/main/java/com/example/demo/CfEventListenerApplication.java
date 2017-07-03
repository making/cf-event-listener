package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

@SpringBootApplication
public class CfEventListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfEventListenerApplication.class, args);
	}

	/**
	 * Enables async event listener
	 */
	@Bean
	ApplicationEventMulticaster applicationEventMulticaster(
			@Value("${event.consumer-threads:8}") int consumerThreads) {
		SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
		ExecutorService executorService = Executors.newFixedThreadPool(consumerThreads);
		eventMulticaster.setTaskExecutor(executorService);
		return eventMulticaster;
	}
}
