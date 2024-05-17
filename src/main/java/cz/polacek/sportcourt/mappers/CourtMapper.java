package cz.polacek.sportcourt.mappers;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.request.RequestCourtDto;
import cz.polacek.sportcourt.data.model.Court;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CourtMapper {

    CourtDto mapToCourtDto(Court court);

    default Page<CourtDto> mapToCourtDtoPage(Page<Court> courtPage) {
        return courtPage.map(this::mapToCourtDto);
    }

    Court mapToCourt(RequestCourtDto courtDto);
}
