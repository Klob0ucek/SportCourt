package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.facade.UserFacade;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {
    @Mock
    private UserFacade userFacade;
    
    @InjectMocks
    private UserRestController userRestController;
    
    @Test
    void getAllUsers_noUsersFound_returnsEmptyList() {
        when(userFacade.getAllUsers()).thenReturn(Collections.emptyList());
        
        var result = userRestController.getAllUsers();
        
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(Collections.emptyList());
    }

    @Test
    void getAllUsers_usersFound_returnsUsers() {
        when(userFacade.getAllUsers()).thenReturn(List.of(TestDataFactory.getUserDto()));

        var result = userRestController.getAllUsers();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isNotEqualTo(Collections.emptyList());
    }
}
