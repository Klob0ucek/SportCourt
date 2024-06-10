package cz.polacek.sportcourt.data.seeding;

import cz.polacek.sportcourt.data.model.Surface;
import cz.polacek.sportcourt.data.model.SurfaceType;
import cz.polacek.sportcourt.data.repository.CourtRepository;
import cz.polacek.sportcourt.data.repository.SurfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OptionalSeeding {

    @Value("${app.seedData:false}")
    private boolean seedData;

    @Bean
    CommandLineRunner initDatabase(SurfaceRepository repository) {
        return args -> {
            if (!seedData) {
                return;
            }
            System.out.println("Seeding database");

            // TODO seed data

        };
    }

    @Autowired
    public void seedCourts(CourtRepository courtRepository) {
        // TODO seed courts
    }
}
