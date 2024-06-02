package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.SurfaceDto;
import cz.polacek.sportcourt.data.model.SurfaceType;
import cz.polacek.sportcourt.mappers.SurfaceMapper;
import cz.polacek.sportcourt.service.SurfaceService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurfaceFacade {

    private final SurfaceService surfaceService;

    private final SurfaceMapper surfaceMapper;

    @Autowired
    public SurfaceFacade(SurfaceService surfaceService, SurfaceMapper surfaceMapper) {
        this.surfaceService = surfaceService;
        this.surfaceMapper = surfaceMapper;
    }
    public List<SurfaceDto> getAllSurfaces() {
        return surfaceMapper.mapToCourtDtoList(surfaceService.getAllSurfaces());
    }

    public SurfaceDto updateSurface(SurfaceDto surfaceDto) {
        return surfaceMapper.mapToSurfaceDto(surfaceService.updateSurface(surfaceMapper.mapToSurface(surfaceDto)));
    }
}
