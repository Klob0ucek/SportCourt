package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.data.model.User;
import cz.polacek.sportcourt.data.repository.UserRepository;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import java.util.List;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User findOrCreate(Long phoneNumber, String name) {
        var found = userRepository.findByPhone(phoneNumber);
        if (found.isEmpty()) {
            var newUser = new User();
            newUser.setPhoneNumber(phoneNumber);
            newUser.setName(name);
            return userRepository.save(newUser);
        } else {
            return found.get();
        }
    }
}
