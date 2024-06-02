package cz.polacek.sportcourt.data.repository;

import cz.polacek.sportcourt.data.model.Surface;
import cz.polacek.sportcourt.data.model.SurfaceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurfaceRepository extends JpaRepository<Surface, SurfaceType> {
}
