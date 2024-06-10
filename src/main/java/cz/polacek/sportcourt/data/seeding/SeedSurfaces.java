package cz.polacek.sportcourt.data.seeding;

import cz.polacek.sportcourt.data.model.Surface;
import cz.polacek.sportcourt.data.model.SurfaceType;
import cz.polacek.sportcourt.data.repository.SurfaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedSurfaces {
    @Bean
    CommandLineRunner initSurfaces(SurfaceRepository repository) {
        return args -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }
            System.out.println("Seeding surfaces");
            repository.save(new Surface(SurfaceType.CLAY, 160.0));
            repository.save(new Surface(SurfaceType.HARD, 100.0));
            repository.save(new Surface(SurfaceType.GRASS, 300.0));
            repository.save(new Surface(SurfaceType.ARTIFICIAL, 100.0));

        };
    }
}