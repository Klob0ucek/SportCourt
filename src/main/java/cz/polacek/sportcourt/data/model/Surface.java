package cz.polacek.sportcourt.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "surface")

public class Surface {
    @Id
    @Enumerated(EnumType.STRING)
    private SurfaceType type;
    private double price;

    public Surface(SurfaceType type, double price) {
        this.type = type;
        this.price = price;
    }

    public Surface() {
    }
}
