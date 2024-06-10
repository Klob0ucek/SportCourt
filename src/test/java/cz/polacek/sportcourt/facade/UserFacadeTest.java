package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.mappers.UserMapper;
import cz.polacek.sportcourt.service.UserService;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserFacadeTest {
    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserFacade userFacade;

    @Test
    void getAllUsers_noUsersFound_returnsEmptyList() {
        Mockito.when(userService.getAllUsers()).thenReturn(List.of());
        Mockito.when(userMapper.mapToUserList(List.of())).thenReturn(List.of());

        var found = userFacade.getAllUsers();

        assertThat(found).isEmpty();
    }

    @Test
    void getAllUsers_usersFound_returnsUsers() {
        var found = List.of(TestDataFactory.getUser());
        var mapped = List.of(TestDataFactory.getUserDto());

        Mockito.when(userService.getAllUsers()).thenReturn(found);
        Mockito.when(userMapper.mapToUserList(found)).thenReturn(mapped);

        var result = userFacade.getAllUsers();

        assertThat(result).containsExactly(TestDataFactory.getUserDto());
    }

}
