package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.CourtDto;
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
}
