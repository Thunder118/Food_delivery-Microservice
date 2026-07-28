package food_delivery.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);

	}

	@Bean
	public AsyncReporter<zipkin2.Span> zipkinAsyncReporter(Sender sender) {
		return AsyncReporter.builder(sender)
				.queuedMaxSpans(1000)
				.messageTimeout(10, java.util.concurrent.TimeUnit.MILLISECONDS)
				.build();
	}

}
