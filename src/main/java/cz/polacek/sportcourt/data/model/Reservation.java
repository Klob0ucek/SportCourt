package cz.polacek.sportcourt.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Court court;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    private GameType gameType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
