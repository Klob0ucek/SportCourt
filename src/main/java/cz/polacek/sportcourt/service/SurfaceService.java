package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.data.model.Surface;
import cz.polacek.sportcourt.data.repository.SurfaceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurfaceService {

    private final SurfaceRepository surfaceRepository;

    @Autowired
    public SurfaceService(SurfaceRepository surfaceRepository) {
        this.surfaceRepository = surfaceRepository;
    }

    public List<Surface> getAllSurfaces() {
        return surfaceRepository.findAll();
    }

    public Surface updateSurface(Surface surface) {
        return surfaceRepository.save(surface);
    }
}
