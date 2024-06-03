package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.request.RequestCourtDto;
import cz.polacek.sportcourt.data.model.Court;
import cz.polacek.sportcourt.mappers.CourtMapper;
import cz.polacek.sportcourt.service.CourtService;
import cz.polacek.sportcourt.service.SurfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CourtFacade {
    private final CourtService courtService;
    private final CourtMapper courtMapper;
    private final SurfaceService surfaceService;

    @Autowired
    public CourtFacade(CourtService courtService,
                       CourtMapper courtMapper,
                       SurfaceService surfaceService) {
        this.courtService = courtService;
        this.courtMapper = courtMapper;
        this.surfaceService = surfaceService;
    }

    public Page<CourtDto> getAllCourts(Pageable pageable){
        Page<Court> courts = courtService.getAllCourts(pageable);
        return courtMapper.mapToCourtDtoPage(courts);
    }
    public CourtDto getCourt(Long id){
        Court court = courtService.getCourtById(id);
        return courtMapper.mapToCourtDto(court);
    }

    public CourtDto makeNewCourt(RequestCourtDto newCourtDto){
        Court newCourt = courtMapper.mapToCourt(newCourtDto);
        newCourt.setSurface(surfaceService.getSurfaceByType(newCourtDto.getType()));
        return courtMapper.mapToCourtDto(courtService.createNewCourt(newCourt));
    }

    public CourtDto updateCourt(Long id, RequestCourtDto courtDto){
        Court court = courtMapper.mapToCourt(courtDto);
        court.setSurface(surfaceService.getSurfaceByType(courtDto.getType()));
        Court updatedCourt = courtService.updateCourt(id, court);
        return courtMapper.mapToCourtDto(updatedCourt);
    }

    public void deleteCourt(Long id){
        courtService.deleteCourt(id);
    }
}