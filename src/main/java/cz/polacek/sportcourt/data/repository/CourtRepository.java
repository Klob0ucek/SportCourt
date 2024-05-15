package cz.polacek.sportcourt.data.repository;

import cz.polacek.sportcourt.data.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {
}
