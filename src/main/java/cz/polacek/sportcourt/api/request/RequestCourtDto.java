package cz.polacek.sportcourt.api.request;

import cz.polacek.sportcourt.data.model.SurfaceType;
import lombok.Data;

@Data
public class RequestCourtDto {
    private int size;
    private SurfaceType type;
}
