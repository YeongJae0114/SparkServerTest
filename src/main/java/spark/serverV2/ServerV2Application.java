package spark.serverV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("spark.serverV2.domain")
@EnableJpaRepositories("spark.serverV2.repository")
public class ServerV2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServerV2Application.class, args);
	}

}
