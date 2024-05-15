package cz.polacek.sportcourt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("cz.polacek.sportcourt.data.model")
@EnableJpaRepositories("cz.polacek.sportcourt.data.repository")
public class SportCourtApplication {
    public static void main(String[] args) {
        SpringApplication.run(SportCourtApplication.class, args);
    }
}
