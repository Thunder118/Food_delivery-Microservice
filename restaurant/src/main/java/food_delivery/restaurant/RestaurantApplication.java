package food_delivery.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.brave.ZipkinSpanHandler;
import zipkin2.reporter.urlconnection.URLConnectionSender;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	public Sender zipkinSender() {
		return  URLConnectionSender.create("http:localhost:9411/api/v2/spans");
	}

	@Bean
	public AsyncReporter<zipkin2.Span> zipkinAsyncReporter(Sender sender) {
		return AsyncReporter.builder(sender)
				.queuedMaxSpans(1000)
				.messageTimeout(10, java.util.concurrent.TimeUnit.MILLISECONDS)
				.build();
	}

}
