package food_delivery.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
public class DriverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverApplication.class, args);
	}

	@Bean
	public AsyncReporter<Span> zipkinAsyncReporter(Sender sender) {
		return AsyncReporter.builder(sender)
				.queuedMaxSpans(100)
				.messageTimeout(10, TimeUnit.MICROSECONDS)
				.build();

	}



}
