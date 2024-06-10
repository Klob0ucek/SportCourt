package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.UserDto;
import cz.polacek.sportcourt.mappers.UserMapper;
import cz.polacek.sportcourt.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {
    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserFacade(UserService userService,
                      UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserList(userService.getAllUsers());
    }
}
