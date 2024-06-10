package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.data.repository.UserRepository;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsers_noUsersFound_returnsEmptyList() {
        when(userRepository.findAll()).thenReturn(List.of());

        var found = userService.getAllUsers();

        assertThat(found).isEmpty();
    }

    @Test
    void getAllUsers_usersFound_returnsUsers() {
        var found = List.of(TestDataFactory.getUser());

        when(userRepository.findAll()).thenReturn(found);

        var result = userService.getAllUsers();

        assertThat(result).containsExactly(TestDataFactory.getUser());
    }

    @Test
    void findOrCreate_userFound_returnsUser() {
        var user = TestDataFactory.getUser();

        when(userRepository.findByPhone(user.getPhoneNumber())).thenReturn(Optional.of(user));

        var result = userService.findOrCreate(user.getPhoneNumber(), user.getName());

        assertThat(result).isEqualTo(user);
    }

    @Test
    void findOrCreate_userNotFound_createsUser() {
        var user = TestDataFactory.getUser();

        when(userRepository.findByPhone(user.getPhoneNumber())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        var result = userService.findOrCreate(user.getPhoneNumber(), user.getName());

        assertThat(result).isEqualTo(user);
    }
}
