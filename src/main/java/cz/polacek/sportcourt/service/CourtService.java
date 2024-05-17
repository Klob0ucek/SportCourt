package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.data.model.Court;
import cz.polacek.sportcourt.data.repository.CourtRepository;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import java.util.Optional;
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

    public Court getCourtById(Long id){
        Optional<Court> found = courtRepository.findById(id);
        if (found.isEmpty()){
            throw new EntityNotFoundException("Did not find court with id: " + id);
        }
        return found.get();
    }

    public Court createNewCourt(Court court){
        return courtRepository.save(court);
    }

    public Court updateCourt(Long id, Court court){
        Court old = getCourtById(id);
        System.out.println("Updating" + old + " to " + court);
        return old;
    }

    public void deleteCourt(Long id){
        courtRepository.deleteById(id);
    }

}