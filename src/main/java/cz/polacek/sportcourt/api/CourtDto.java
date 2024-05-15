package cz.polacek.sportcourt.api;

import lombok.Data;

@Data
public class CourtDto {
    private String id;
    private SurfaceDto surface;
}
