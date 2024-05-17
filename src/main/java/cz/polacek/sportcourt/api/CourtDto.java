package cz.polacek.sportcourt.api;

import lombok.Data;

@Data
public class CourtDto {
    private Long id;
    private SurfaceDto surface;
}
