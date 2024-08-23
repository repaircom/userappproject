package service;

import model.User;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to create or update a user
    public User saveOrUpdateUser(String phoneNumber, String name, String location) {
        Optional<User> userOpt = userRepository.findByPhoneNumber(phoneNumber);
        User user;

        if (userOpt.isPresent()) {
            user = userOpt.get();
            if (name != null) {
                user.setName(name);
            }
            if (location != null) {
                user.setLocation(location);
            }
        } else {
            user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setName(name);
            user.setLocation(location);
        }

        return userRepository.save(user);
    }

    // Method to get user details by phone number
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
