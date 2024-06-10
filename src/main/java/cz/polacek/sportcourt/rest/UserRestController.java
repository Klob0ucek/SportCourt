package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.api.UserDto;
import cz.polacek.sportcourt.facade.UserFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        info = @Info(title = "Tennis club user service",
                description = """
                        Simple service for user management. The API has operations for:
                        - getting all users
                        """
        )
)
@Tag(name = "User", description = "service for user management")
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    private final UserFacade userFacade;

    @Autowired
    public UserRestController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers();
    }
}
