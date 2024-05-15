package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.data.model.Court;
import cz.polacek.sportcourt.data.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourtService {
    private final CourtRepository courtRepository;

    @Autowired
    public CourtService(CourtRepository courtRepository){
        this.courtRepository = courtRepository;
    }

    public Page<Court> getAllCourts(Pageable pageable){
        return courtRepository.findAll(pageable);
    }
}
