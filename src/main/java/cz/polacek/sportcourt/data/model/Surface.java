package cz.polacek.sportcourt.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "`surface`")
public class Surface {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "type", unique = true, nullable = false)
    private SurfaceType type;

    @Column(name = "price", nullable = false)
    private double price;

    public Surface(SurfaceType type, double price) {
        this.type = type;
        this.price = price;
    }

    public Surface() {
    }
}
