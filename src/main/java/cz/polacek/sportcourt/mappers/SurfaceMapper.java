package cz.polacek.sportcourt.mappers;

import cz.polacek.sportcourt.api.SurfaceDto;
import cz.polacek.sportcourt.data.model.Surface;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurfaceMapper {
    SurfaceDto mapToSurfaceDto(Surface surface);
    default List<SurfaceDto> mapToCourtDtoList(List<Surface> surfaceList) {
        return surfaceList.stream().map(this::mapToSurfaceDto).toList();
    }
    Surface mapToSurface(SurfaceDto surfaceDto);
}
