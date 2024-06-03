package cz.polacek.sportcourt.api;

import lombok.Data;

@Data
public class CourtDto {
    private Long id;
    private int size;
    private SurfaceDto surface;
}
