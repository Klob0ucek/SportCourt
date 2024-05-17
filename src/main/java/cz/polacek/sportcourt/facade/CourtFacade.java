package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.request.RequestCourtDto;
import cz.polacek.sportcourt.data.model.Court;
import cz.polacek.sportcourt.mappers.CourtMapper;
import cz.polacek.sportcourt.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CourtFacade {
    private final CourtService courtService;
    private final CourtMapper courtMapper;

    @Autowired
    public CourtFacade(CourtService courtService,
                       CourtMapper courtMapper) {
        this.courtService = courtService;
        this.courtMapper = courtMapper;
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
        return courtMapper.mapToCourtDto(courtService.createNewCourt(newCourt));
    }

    public CourtDto updateCourt(Long id, RequestCourtDto courtDto){
        Court updatedCourt = courtService.updateCourt(id, courtMapper.mapToCourt(courtDto));
        return courtMapper.mapToCourtDto(updatedCourt);
    }

    public void deleteCourt(Long id){
        courtService.deleteCourt(id);
    }
}