package cz.polacek.sportcourt.mappers;

import cz.polacek.sportcourt.api.UserDto;
import cz.polacek.sportcourt.data.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
    List<UserDto> mapToUserList(List<User> userDtoList);
}
