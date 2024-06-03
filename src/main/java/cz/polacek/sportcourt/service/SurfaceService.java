package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.data.model.Surface;
import cz.polacek.sportcourt.data.model.SurfaceType;
import cz.polacek.sportcourt.data.repository.SurfaceRepository;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
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

    public Surface getSurfaceByType(SurfaceType type){
        var surface = surfaceRepository.findById(type);
        if (surface.isPresent()) {
            return surface.get();
        }
        throw new EntityNotFoundException("Did not find surface with type: " + type);
    }
}
